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
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.knia.foreign.exchange.cmm.ResponseResult;
import egovframework.knia.foreign.exchange.cmm.code.CommonConst;
import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
import egovframework.knia.foreign.exchange.service.CommonCodeService;
import egovframework.knia.foreign.exchange.service.MenuService;
import egovframework.knia.foreign.exchange.vo.CommonCodeVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.MenuVO;

@Controller
public class MenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Resource(name = "beanValidator")
	private DefaultBeanValidator beanValidator;
	
	@Resource(name="menuService")
	private MenuService menuService;
	
	@Resource(name="commonCodeService")
	private CommonCodeService commonCodeService;
	
	@RequestMapping(value="/setting/menu.do")
	public String listMainMenu(@ModelAttribute("menuVO") MenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		menuVO.setLvl(1);
		
		List<?> getMenu = menuService.selectMenuList(menuVO);
		model.addAttribute("fstList", getMenu);

		if (menuVO.getFirstMnuId() != null && !menuVO.getFirstMnuId().equals("")) {
			MenuVO sMenuVO = new MenuVO();
			sMenuVO.setLvl(2);
			sMenuVO.setPrtMnuId(menuVO.getFirstMnuId());
			List<?> sndMenu = menuService.selectMenuList(sMenuVO);
			model.addAttribute("sndList", sndMenu);
			
			menuVO.setMnuId(menuVO.getFirstMnuId());	// ????????? ??????
			
			if (menuVO.getSecondMnuId() != null && !menuVO.getSecondMnuId().equals("")) {
				MenuVO tMenuVO = new MenuVO();
				tMenuVO.setLvl(3);
				tMenuVO.setPrtMnuId(menuVO.getSecondMnuId());
				List<?> trdMenu = menuService.selectMenuList(tMenuVO);
				model.addAttribute("trdList", trdMenu);
				
				menuVO.setMnuId(menuVO.getSecondMnuId());	// ????????? ??????
				
				menuVO.setLvl(2);	// ???????????? ????????? ?????? ?????? ??????
				
				if (menuVO.getThirdMnuId() != null && !menuVO.getThirdMnuId().equals("")) {
					menuVO.setMnuId(menuVO.getThirdMnuId());	// ????????? ??????
					
					menuVO.setLvl(3);	// ???????????? ????????? ?????? ?????? ??????
				}
			}
		}

		if (menuVO.getMnuId() != null && !menuVO.getMnuId().equals("")) {	// ????????? ?????? ??????
			MenuVO selMenu = menuService.getMenu(menuVO);
			model.addAttribute("selMenu", selMenu);
		}
		
		List<?> mnuTypeLst = commonCodeService.selectCodeList(CommonConst.MENU_TYPE);	// ????????????
		model.addAttribute("mnuTypeLst", mnuTypeLst);
		
		List<?> sourceMenu = menuService.selectSourceMenu();	// ????????????
		model.addAttribute("srcMenu", sourceMenu);
		
		menuVO.setLvl(menuVO.getLvl()-1);	// ???????????? ????????? ?????? ?????? ??????
		
		List<?> getPrtMenu = menuService.selectParentMenu(menuVO);	// ????????????
		model.addAttribute("prtMenu", getPrtMenu);
		
		return "setting/menu";
	}
	
	@RequestMapping(value="/setting/prtMenuLst.ajax")
	public String listPrtMenu(@ModelAttribute("menuVO") MenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		menuVO.setLvl(menuVO.getLvl()-1);
		
		List<?> getPrtMenu = menuService.selectParentMenu(menuVO);
		model.addAttribute("prtMenu", getPrtMenu);
		
//		MenuVO getMenu = menuService.getMenuFromParentMnuId(menuVO);
//		model.addAttribute("cMenu", getMenu);
		
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap());
		
		return "jsonView";
	}
	
	@RequestMapping(value="/setting/subMenu.ajax")
	public String listSubMenu(@ModelAttribute("menuVO") MenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		menuVO.setIsDel("N");
		
		List<?> getMenuLst = menuService.selectMenuList(menuVO);
		model.addAttribute("subMenuList", getMenuLst);
		
		MenuVO getMenu = menuService.getMenuFromParentMnuId(menuVO);
		model.addAttribute("cMenu", getMenu);
		
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap());
		
		return "jsonView";
	}
	
	@RequestMapping(value="/setting/dtlMenu.ajax")
	public String listDtlMenu(@ModelAttribute("menuVO") MenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		menuVO.setIsDel("N");
		
		List<?> getMenu = menuService.selectMenuList(menuVO);
		model.addAttribute("dtlMenuList", getMenu);
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap());
		
		return "jsonView";
	}
	
	@RequestMapping(value="/setting/updateMenu.ajax")
	public String updateMenu(@ModelAttribute("menuVO") MenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		menuVO.setUpdtId(loginVO.getLoginId());
		
		menuService.updateMenu(menuVO);
		
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap());
		
		return "jsonView";
	}
	
	@RequestMapping(value="/setting/deleteMenu.ajax")
	public String deleteMenu(@ModelAttribute("menuVO") MenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		menuVO.setUpdtId(loginVO.getLoginId());
		
		menuService.deleteMenu(menuVO);
		
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap());
		
		return "jsonView";
	}
}
