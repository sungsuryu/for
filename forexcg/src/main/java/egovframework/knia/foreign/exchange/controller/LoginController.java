package egovframework.knia.foreign.exchange.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;
import org.slf4j.Logger;

import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovNumberUtil;
import egovframework.knia.foreign.exchange.cmm.ResponseResult;
import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.service.LoginService;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;
import egovframework.knia.foreign.exchange.vo.LoginAuthHistVO;

import egovframework.com.cmm.util.EgovDateUtil;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@RequestMapping(value="/login.do")
	public String login(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		logger.debug("로그인 화면");
		
		return "usr/login";
	}
	
	@RequestMapping(value="/loginAction.do")
	public String loginAction(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		UserVO userVO = loginService.selectUser(loginVO);
		
		if (userVO != null && userVO.getUserId() != null && !userVO.getUserId().equals("")) {
			// 1차 로그인 세션 생성
			request.getSession().setAttribute(ConstCode.loginVO.toString(), loginVO);
			
			HashMap<String, Object> loginInfo = new HashMap<String, Object>();
			loginInfo.put("loginId", loginVO.getLoginId());
			loginInfo.put("timestamp", loginVO.getTimestamp());

			logger.debug("로그인 성공:{}", loginVO.getLoginId());
			model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(loginInfo));
		} else {
			logger.info("로그인 실패: 사용자를 찾을 수 없습니다.");
			model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_403).toMap());
		}
		
		return "jsonView";
	}
	
	@RequestMapping(value="/otpAction.do")
	public String otpAction(@ModelAttribute("loginAuthHistVO") LoginAuthHistVO loginAuthHistVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		// TODO 1차로그인 정보 비교
		// TODO timestamp 비교

		loginService.getAuthNum(loginAuthHistVO);
		
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap());
		
		return "jsonView";
	}
}
