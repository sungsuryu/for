package egovframework.knia.foreign.exchange.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.knia.foreign.exchange.service.JoinService;
import egovframework.knia.foreign.exchange.service.LoginService;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;

@Controller
public class JoinController {

	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	@Resource(name = "joinService")
	private JoinService joinService;
	
	@RequestMapping(value="/join.do")
	public String join(@ModelAttribute("userVO") UserVO userVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		return "usr/join";
	}
	
	@RequestMapping(value="/joinAction.do")
	public String joinAction(@ModelAttribute("userVO") UserVO userVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		
		
		
		joinService.UserJoin(userVO);
		
		return "redirect:/index.do";
	}
}
