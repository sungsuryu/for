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

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.knia.foreign.exchange.service.GuideService;
import egovframework.knia.foreign.exchange.vo.GuideVO;

@Controller
public class GuideController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name = "guideService")
	private GuideService guideService;
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	@RequestMapping(value = "/guide.ajax")
	public String selectGuide(@ModelAttribute("guideVO") GuideVO guideVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("도움말 불러오기");
		guideVO.setUiNum(1);
		List<?> guideList = guideService.selectGuide(guideVO);
		return null;
	}
}
