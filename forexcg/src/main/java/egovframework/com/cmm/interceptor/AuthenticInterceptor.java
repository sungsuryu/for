package egovframework.com.cmm.interceptor;

import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.service.MenuService;
import egovframework.knia.foreign.exchange.vo.ActiveHistVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.MenuVO;
import egovframework.knia.foreign.exchange.vo.UserRoleVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 인증여부 체크 인터셉터
 * @author 공통서비스 개발팀 서준식
 * @since 2011.07.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  서준식          최초 생성
 *  2011.09.07  서준식          인증이 필요없는 URL을 패스하는 로직 추가
 *  2021.05.08	류성수          loginVO 교체 - login 페이지 설정
 *  2021.05.11	류성수          OTP 2차인증 유무 체크
 *  </pre>
 */


public class AuthenticInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticInterceptor.class);
	
	@Resource(name="menuService")
	MenuService menuService;
	
	/**
	 * 세션에 계정정보(LoginVO)가 있는지 여부로 인증 여부를 체크한다.
	 * 계정정보(LoginVO)가 없다면, 로그인 페이지로 이동한다.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		boolean isPermittedURL = false;

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		logger.debug("AuthenticInterceptor - preHandle: {}", loginVO);
		
		if(loginVO != null) {
			if (loginVO.getLoginStep() > 0) {	// 2차 인증 완료
				
				// TODO 권한Role 체크
				String reqSvlPath = request.getServletPath();

				UserRoleVO setRoleVO = new UserRoleVO();
				setRoleVO.setRoleId(loginVO.getRoleId());
				setRoleVO.setUrl(reqSvlPath);
				setRoleVO.setInsrtId(loginVO.getLoginId());
				
				if (EgovUserDetailsHelper.isAuthorities(setRoleVO)) {
					return true;
				}
				
				logger.error("권한없음 으로 권한 등록이 필요합니다. -- ");
//				return false;
				return true;
			} else {	// 추가인증 필요
				logger.debug("AuthenticInterceptor - OTP인증 필요.:{} 단계", loginVO.getLoginStep());
				request.getSession().setAttribute(ConstCode.loginVO.toString(), null);
				
				ModelAndView modelAndView = new ModelAndView("redirect:/login.do");
				throw new ModelAndViewDefiningException(modelAndView);
			}
		} else if(!isPermittedURL) {	// 로그인하지 않음, 세션종료됨
			request.getSession().setAttribute(ConstCode.loginVO.toString(), null);
			
			ModelAndView modelAndView = new ModelAndView("redirect:/login.do");
			throw new ModelAndViewDefiningException(modelAndView);
		} else {
			return true;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model) throws Exception {
		
		logger.debug("postHandle");
		
		Map<String, Object> gnbMenu = menuService.selectMenuTree(new MenuVO());
		model.addObject("gnbMenu", gnbMenu);
		
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		model.addObject("loginVo", getLoginVO);
		
		ActiveHistVO histVo = new ActiveHistVO();
		histVo.setUserId(getLoginVO.getLoginId());
		
		List<?> getHist = EgovUserDetailsHelper.getActiveHistory(histVo);
		model.addObject("histVo", getHist);
		
		super.postHandle(request, response, handler, model);
	}
}
