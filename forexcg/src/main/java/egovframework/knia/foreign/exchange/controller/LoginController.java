package egovframework.knia.foreign.exchange.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

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
import egovframework.knia.foreign.exchange.cmm.ResponseResult;
import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
import egovframework.knia.foreign.exchange.service.LoginService;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;

import egovframework.com.cmm.util.EgovDateUtil;

@Controller
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@RequestMapping(value="/login.do")
	public String login(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LOGGER.info("-------------------------");
		
		return "usr/login";
	}
	
	@RequestMapping(value="/loginAction.do")
	public String loginAction(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		UserVO userVO = loginService.selectUser(loginVO);
		
		if (userVO != null && userVO.getUserId() != null && !userVO.getUserId().equals("")) {
			
			HashMap<String, Object> loginInfo = new HashMap<String, Object>();
			loginInfo.put("loginId", loginVO.getLoginId());
			loginInfo.put("timestamp", EgovDateUtil.currentDateTime());
			
			// 1차 로그인 세션 생성
			request.getSession().setAttribute("loginInfo", loginInfo);
			
			model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(loginInfo));
		} else {
			
			model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_403).toMap());
		}
		
		return "jsonView";
	}
	
	@RequestMapping(value="/otpAction.do")
	public String otpAction(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		
		return "jsonView";
	}
	
	
	
//	public static void main(String[] args) {
//
//		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMddhhmmss", new Locale("ko", "KR"));
//
//		String dateString = simpleFormat.format(new Date());
//
//		System.out.println(dateString);
//		
//		Timestamp timestamp = Timestamp.valueOf(dateString);
//		
//		System.out.println(timestamp);
////		long time = System.currentTimeMillis();
////		System.out.println(time);
////		SimpleDateFormat simpl = new SimpleDateFormat("yyyy년 MM월 dd일 aa hh시 mm분 ss초");
////		String s = simpl.format(time); // String s = simpl.format(time); System.out.println(s);
////
////		System.out.println(date);
//	}
}
