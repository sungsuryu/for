package egovframework.knia.foreign.exchange.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.knia.foreign.exchange.cmm.ResponseResult;
import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
import egovframework.knia.foreign.exchange.service.GuideService;
import egovframework.knia.foreign.exchange.vo.GuideVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;

@Controller
public class GuideController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name = "guideService")
	private GuideService guideService;
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	@RequestMapping(value = "/help/guide.ajax")
	public String selectGuide(@ModelAttribute("guideVO") GuideVO guideVO, HttpServletRequest request, ModelMap model) throws Exception {
		GuideVO resultGuideVO = new GuideVO();
		resultGuideVO = guideService.selectGuide(guideVO);
		
		HashMap<String, Object> rMap = new HashMap<String, Object>();
		
		if(resultGuideVO == null){
			rMap.put("isYn", "N");
			rMap.put("levelCheck", "Y");//개발용 권한체크
		}
		else{
			rMap.put("isYn", "Y");
			rMap.put("guideVO", resultGuideVO);
			rMap.put("levelCheck", "Y");//개발용 권한체크
		}
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(rMap));
		return "jsonView";
	}
	
	@RequestMapping(value = "/help/updateGuide.ajax")
	public String updateGuide(@ModelAttribute("guideVO") GuideVO guideVO, HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		guideVO.setUserNm(getLoginVO.getUserNm());
		guideVO.setUpdtId(getLoginVO.getLoginId());
		guideVO.setInsrtId(getLoginVO.getLoginId());
		guideService.mergeInsertGuide(guideVO);
		
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0));
		return "jsonView";
	}
	
	
	
}
