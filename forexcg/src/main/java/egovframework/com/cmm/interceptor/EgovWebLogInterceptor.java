package egovframework.com.cmm.interceptor;

//import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
//import egovframework.com.sym.log.wlg.service.EgovWebLogService;
//import egovframework.com.sym.log.wlg.service.WebLog;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Class Name : EgovWebLogInterceptor.java
 * @Description : 웹로그 생성을 위한 인터셉터 클래스
 * @Modification Information
 *
 *    수정일        수정자         수정내용
 *    -------      -------     -------------------
 *    2009. 3. 9.   이삼섭         최초생성
 *    2011. 7. 1.   이기하         패키지 분리(sym.log -> sym.log.wlg)
 *    2021.	5.	7.	류성수	WeblogInterceptor 적용
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 9.
 * @version
 * @see
 *
 */
public class EgovWebLogInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(EgovWebLogInterceptor.class);
	
//	@Resource(name="EgovWebLogService")
//	private EgovWebLogService webLogService;

	/**
	 * 웹 로그정보를 생성한다.
	 * 
	 * @param HttpServletRequest request, HttpServletResponse response, Object handler 
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView modeAndView) throws Exception {
		
		logger.debug("EgovWebLogInterceptor - postHandle");
//		WebLog webLog = new WebLog();
//		String reqURL = request.getRequestURI();
//		String uniqId = "";
//		
//    	/* Authenticated  */
//        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
//    	if(isAuthenticated.booleanValue()) {
//			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//			uniqId = user.getUniqId();
//    	}
//
//		webLog.setUrl(reqURL);
//		webLog.setRqesterId(uniqId);
//		webLog.setRqesterIp(request.getRemoteAddr());
//		
//		webLogService.logInsertWebLog(webLog);
		
	}
}
