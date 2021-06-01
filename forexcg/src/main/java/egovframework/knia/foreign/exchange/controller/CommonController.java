package egovframework.knia.foreign.exchange.controller;

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

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.knia.foreign.exchange.cmm.ResponseResult;
import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
import egovframework.knia.foreign.exchange.service.ActiveHistService;
import egovframework.knia.foreign.exchange.vo.ActiveHistVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;

@Controller
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@Resource(name="activeHistService")
	ActiveHistService activeHistService;
	
	@RequestMapping(value="/common/deleteActiveHist.ajax")
	public String listGroupCode(@ModelAttribute("activeHistVO") ActiveHistVO activeHistVO, BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception {
		
		beanValidator.validate(activeHistVO, bindingResult);
		if (bindingResult.hasErrors()) {
			logger.error("필수 파라메터 바인딩 오류");
			throw new Exception("필수 파라메터 바인딩 오류");
		}
		
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		activeHistVO.setUserId(getLoginVO.getLoginId());
		
		activeHistService.updateActiveHist(activeHistVO);
		
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap());
		
		return "jsonView";
	}
}
