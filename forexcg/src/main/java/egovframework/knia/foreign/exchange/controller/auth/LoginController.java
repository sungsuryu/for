package egovframework.knia.foreign.exchange.controller.auth;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.knia.foreign.exchange.vo.LoginVO;

@Controller
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@RequestMapping(value="/login.do")
	public String login(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LOGGER.info("-------------------------");
		
		return "usr/login";
	}
	
	@RequestMapping(value="/loginAction.do")
	public String loginAction(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, BindingResult bindingResult, ModelMap model) throws Exception {
		
		beanValidator.validate("loginVO", bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("loginVO", loginVO);
			return "forward:/login.do";
		}
		
		LoginVO resultVO = null;
		
		return "redirect:/for/index.do";
	}
}
