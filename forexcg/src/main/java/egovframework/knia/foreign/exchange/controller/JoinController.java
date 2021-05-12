package egovframework.knia.foreign.exchange.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.knia.foreign.exchange.service.InsureService;
import egovframework.knia.foreign.exchange.service.JoinService;
import egovframework.knia.foreign.exchange.service.LoginService;
import egovframework.knia.foreign.exchange.vo.InsureVO;
import egovframework.knia.foreign.exchange.vo.UserVO;

@Controller
public class JoinController {

	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	@Resource(name = "joinService")
	private JoinService joinService;
	
	@Resource(name="insureService")
	private InsureService insureService;
	
	/**
	 * 회원가입 화면
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/join.do")
	public String join(HttpServletRequest request, ModelMap model) throws Exception {
		
		HashMap<String, Object> hMap = new HashMap<String, Object>();
		hMap.put("useYn", "Y");
		
		List<InsureVO> insureList = (List<InsureVO>) insureService.selectInsureList(hMap);
		model.addAttribute("insureList", insureList);
		
		logger.debug("보험사 목록 조회 - insureList:{}개", insureList.size());
		
		return "usr/join";
	}
	
	/**
	 * 회원가입
	 * @param userVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/joinAction.do")
	public String joinAction(@ModelAttribute("userVO") UserVO userVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		
		
		
		joinService.UserJoin(userVO);
		
		return "redirect:/index.do";
	}
}
