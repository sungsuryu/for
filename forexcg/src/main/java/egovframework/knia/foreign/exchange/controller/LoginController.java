package egovframework.knia.foreign.exchange.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.knia.foreign.exchange.cmm.ResponseResult;
import egovframework.knia.foreign.exchange.cmm.code.CommonConst;
import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.service.FindUserAccount;
import egovframework.knia.foreign.exchange.service.LoginService;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.knia.foreign.exchange.vo.FindIdVO;
import egovframework.knia.foreign.exchange.vo.FindPwdVO;
import egovframework.knia.foreign.exchange.vo.LoginAuthHistVO;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cmm.util.EgovFormBasedUUID;

import org.slf4j.Logger;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Resource(name = "findUserAccount")
	private FindUserAccount findUserAccount;
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
    @Resource(name="propertiesService")
    protected EgovPropertyService propertyService;
    
    /**
     * 로그인 화면
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/login.do")
	public String login(HttpServletRequest request, ModelMap model) throws Exception {
		
		model.addAttribute("authInterval", propertyService.getInt("authInterval"));
		
		String uuid = EgovFormBasedUUID.randomUUID().toString();
		model.addAttribute("UUID", uuid);
		
		return "usr/login";
	}
	
	/**
	 * 로그아웃
	 * @param loginVO 로그아웃 대상 아이디
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logout.do")
	public String login(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		request.getSession().setAttribute(ConstCode.loginVO.toString(), null);
		
		return "redirect:/login.do";
	}
	
	/**
	 * 로그인 처리
	 * @param loginVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loginAction.ajax")
	public String loginAction(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		UserVO userVO = loginService.selectUser(loginVO);
		
		if (userVO != null && userVO.getUserId() != null && !userVO.getUserId().equals("")) {
			// 1차 로그인 세션 생성
			loginVO.setLoginStep(CommonConst.LOGIN_STEP0);
			loginVO.setUserNm(userVO.getUserNm());
			loginVO.setRoleId(userVO.getRoleId());
			request.getSession().setAttribute(ConstCode.loginVO.toString(), loginVO);
			
			HashMap<String, Object> loginInfo = new HashMap<String, Object>();
			loginInfo.put("loginId", loginVO.getLoginId());

			model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(loginInfo));
		} else {
			logger.info("로그인 실패: 계정을 찾을 수 없습니다.");
			model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_403).toMap());
		}
		
		return "jsonView";
	}
	
	/**
	 * OTP - authNum 확인
	 * @param loginAuthHistVO OTP번호
	 * @param request
	 * @param model
	 * @return 확인결과
	 * @throws Exception
	 */
	@RequestMapping(value="/otpAction.ajax")
	public String otpAction(@ModelAttribute("loginAuthHistVO") LoginAuthHistVO loginAuthHistVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		HashMap<String, Object> authInfo = new HashMap<String, Object>();
		
		if (getLoginVO != null) {	
			if (getLoginVO.getLoginId().equals(loginAuthHistVO.getLoginId())) {
				if (loginService.loginAuthNum(loginAuthHistVO)) {
					authInfo.put("auth", "T");
					
					getLoginVO.setLoginStep(CommonConst.LOGIN_STEP1);
					request.getSession().setAttribute(ConstCode.loginVO.toString(), getLoginVO);
					
					// 사용완료된 인증번호 만료처리
					loginService.deleteAuthNum(getLoginVO);
				}
			} else {
				authInfo.put("auth", "F");
				request.getSession().setAttribute(ConstCode.loginVO.toString(), null);
			}
		} else {
			authInfo.put("auth", "F");
			request.getSession().setAttribute(ConstCode.loginVO.toString(), null);
		}

		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(authInfo));
		
		return "jsonView";
	}
	
	/**
	 * 유효시간 초과된 인증번호 만료처리
	 * @param loginVO 만료처리 대상 아이디
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/otpExpire.ajax")
	public String otpExpire(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO ssoLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		if (ssoLoginVO != null) {
			if (ssoLoginVO.getLoginId().equals(loginVO.getLoginId())) {
				loginService.deleteAuthNum(loginVO);
			}
		}
		
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap());
		
		return "jsonView";
	}
	
	@RequestMapping(value="/findId.ajax")
	public String findId(@ModelAttribute("findIdVO") FindIdVO findIdVO, BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception {
		
		beanValidator.validate(findIdVO, bindingResult);
        if (bindingResult.hasErrors()) {
        	logger.error("필수 파라메터 바인딩 오류");
        	throw new Exception("필수 파라메터 바인딩 오류");
        }
		
        HashMap<String, Object> findInfo = new HashMap<String, Object>();
        
        if (findUserAccount.findLoginId(findIdVO)) {
        	findInfo.put("findId", "T");
        } else {
        	findInfo.put("findId", "F");
        }
        
        model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(findInfo));
        
		return "jsonView";
	}

	@RequestMapping(value="/findPwd.ajax")
	public String findId(@ModelAttribute("findPwdVO") FindPwdVO findPwdVO, BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception {
		
		beanValidator.validate(findPwdVO, bindingResult);
        if (bindingResult.hasErrors()) {
        	logger.error("필수 파라메터 바인딩 오류");
        	throw new Exception("필수 파라메터 바인딩 오류");
        }
		
        HashMap<String, Object> findInfo = new HashMap<String, Object>();
        
        if (findUserAccount.findPassword(findPwdVO)) {
        	findInfo.put("findPwd", "T");
        } else {
        	findInfo.put("findPwd", "F");
        }
        
        model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(findInfo));
        
		return "jsonView";
	}
	
}
