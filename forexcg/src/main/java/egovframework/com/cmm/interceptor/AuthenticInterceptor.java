package egovframework.com.cmm.interceptor;

import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.vo.LoginVO;

import egovframework.com.cmm.util.EgovUserDetailsHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
			if (loginVO.getLoginStep() > 0) {
				return true;
			} else {
				logger.debug("AuthenticInterceptor - OTP인증 필요.:{} 단계", loginVO.getLoginStep());
				request.getSession().setAttribute(ConstCode.loginVO.toString(), null);
				
				ModelAndView modelAndView = new ModelAndView("redirect:/login.do");
				throw new ModelAndViewDefiningException(modelAndView);
			}
		} else if(!isPermittedURL) {
			request.getSession().setAttribute(ConstCode.loginVO.toString(), null);
			
			ModelAndView modelAndView = new ModelAndView("redirect:/login.do");
			throw new ModelAndViewDefiningException(modelAndView);
		} else {
			return true;
		}
	}
}
