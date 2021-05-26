package egovframework.knia.foreign.exchange.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.knia.foreign.exchange.cmm.ResponseResult;
import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
import egovframework.knia.foreign.exchange.service.CommonCodeService;
import egovframework.knia.foreign.exchange.vo.CommonCodeVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;

@Controller
public class CommonCodeController {

	private static final Logger logger = LoggerFactory.getLogger(CommonCodeController.class);
	
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
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
	
	@RequestMapping(value="/setting/code/addCodeAction.ajax")
	public String addCode(@ModelAttribute("commonCodeVO") CommonCodeVO commonCodeVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		if (commonCodeVO.getUri() == null) {
			logger.error("필수 파라메터 누락 - uri");
			throw new Exception("필수 파라메터 누락");
		}
		
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		commonCodeVO.setInsrtId(getLoginVO.getLoginId());
		
		if (commonCodeVO.getUri().equals("addSubCode")) {
			commonCodeService.addSubCode(commonCodeVO);
		} else {
			commonCodeService.addGroupCode(commonCodeVO);
		}
		
		return "jsonView";
	}
	
	@RequestMapping(value="/setting/code/delCodeAction.ajax")
	public String deletCode(@ModelAttribute("commonCodeVO") CommonCodeVO commonCodeVO, BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception {
		
		beanValidator.validate(commonCodeVO, bindingResult);
		if (bindingResult.hasErrors()) {
			logger.error("필수 파라메터 바인딩 오류");
			throw new Exception("수 파라메터 바인딩 오류");
		}
		
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		commonCodeVO.setUpdtId(getLoginVO.getLoginId());
		commonCodeService.deleteCode(commonCodeVO);
		
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap());
		
		return "jsonView";
	}
}
