package egovframework.knia.foreign.exchange.controller;

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
import egovframework.knia.foreign.exchange.service.LoginService;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;

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
		
//		model.addAttribute("userVO", userVO);
		
		return "jsonView";
//		if (userVO != null && userVO.getUserId() != null && !userVO.getUserId().equals("")) {
//			request.getSession().setAttribute("loginVO", loginVO);
//			
//			return "redirect:/for/index.do";
//		} else {
//			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
//			return "usr/login";
//		}
	}
}
