package egovframework.knia.foreign.exchange.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.knia.foreign.exchange.service.CommonCodeService;

@Controller
public class CommonCodeController {

	private static final Logger logger = LoggerFactory.getLogger(CommonCodeController.class);
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;
	
	@RequestMapping(value="/setting/ajx/group")
	public String listGroupCode(HttpServletRequest request, ModelMap model) throws Exception {
		
		
		return "jsonView";
	}
	
	@RequestMapping(value="/setting/code")
	public String groupCode(HttpServletRequest request, ModelMap model) throws Exception {
		
		List<?> groupCodeList = commonCodeService.selectGroupCodeList();
		model.addAttribute("groupCodeList", groupCodeList);
		
		return "setting/code";
	}
	
	@RequestMapping(value="/setting/ajx/{cmmCd}")
	public String subCode(HttpServletRequest request, ModelMap model) throws Exception {
		
		return "jsonView";
	}
}
