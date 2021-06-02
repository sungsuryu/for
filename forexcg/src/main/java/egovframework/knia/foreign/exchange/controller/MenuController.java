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

import egovframework.knia.foreign.exchange.cmm.code.CommonConst;
import egovframework.knia.foreign.exchange.service.CommonCodeService;
import egovframework.knia.foreign.exchange.service.MenuService;
import egovframework.knia.foreign.exchange.vo.CommonCodeVO;
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
	public String listMainMenu(HttpServletRequest request, ModelMap model) throws Exception {
		
		MenuVO menuVO = new MenuVO();
		menuVO.setIsDel("N");
		menuVO.setLvl(1);
//		menuVO.setMnuType("");
		
		List<?> getMenu = menuService.selectMenuList(menuVO);
		model.addAttribute("menuList", getMenu);

		List<?> mnuTypeLst = commonCodeService.selectCodeList(CommonConst.MENU_TYPE);
		model.addAttribute("mnuTypeLst", mnuTypeLst);
		
		return "setting/menu";
	}
	
	@RequestMapping(value="/setting/subMenu.ajax")
	public String listSubMenu(@ModelAttribute("menuVO") MenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		menuVO.setIsDel("N");
		
		List<?> getMenu = menuService.selectMenuList(menuVO);
		model.addAttribute("subMenuList", getMenu);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/setting/dtlMenu.ajax")
	public String listDtlMenu(@ModelAttribute("menuVO") MenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		menuVO.setIsDel("N");
		
		List<?> getMenu = menuService.selectMenuList(menuVO);
		model.addAttribute("dtlMenuList", getMenu);
		
		return "jsonView";
	}
}