package egovframework.knia.foreign.exchange.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.knia.foreign.exchange.service.CommonCodeService;
import egovframework.knia.foreign.exchange.vo.CommonCodeVO;

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
	
	/**
	 * 그룹코드 목록 조회
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/setting/code.do")
	public String groupCode(HttpServletRequest request, ModelMap model) throws Exception {
		
		List<?> groupCodeList = commonCodeService.selectGroupCodeList();
		model.addAttribute("groupCodeList", groupCodeList);
		
		return "setting/code";
	}
	
	/**
	 * 하위코드 목록 조회
	 * @param commonCodeVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/setting/code/subCode.ajax")
	public String subCode(@ModelAttribute("commonCodeVO") CommonCodeVO commonCodeVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		List<CommonCodeVO> subCodeList = (List<CommonCodeVO>) commonCodeService.selectCodeList(commonCodeVO.getCmmCd());
		
		model.addAttribute("subCodeList", subCodeList);
		
		return "jsonView";
	}
}
