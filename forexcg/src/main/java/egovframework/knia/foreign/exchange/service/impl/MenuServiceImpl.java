package egovframework.knia.foreign.exchange.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.knia.foreign.exchange.dao.mapper.MenuMapper;
import egovframework.knia.foreign.exchange.service.MenuService;
import egovframework.knia.foreign.exchange.vo.MenuVO;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Resource(name="menuMapper")
	MenuMapper menuMapper;
	
	@Override
	public MenuVO getMenuFromParentMnuId(MenuVO menuVO) throws Exception {
		MenuVO setMenu = new MenuVO();
		setMenu.setMnuId(menuVO.getPrtMnuId());
		
		return this.getMenu(setMenu);
	}
	
	@Override
	public MenuVO getMenu(MenuVO menuVO) throws Exception {
		return menuMapper.selectMenu(menuVO);
	}
	
	@Override
	public List<?> selectMenuList(MenuVO menuVO) throws Exception {
		
		return menuMapper.selectMenuList(menuVO);
	}

	@Override
	public Map<String, Object> selectMenuTree(MenuVO menuVO) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<MenuVO> mnuLst = (List<MenuVO>) menuMapper.selectMenuTree(menuVO);

		List<MenuVO> tMnu = new ArrayList<MenuVO>();
		List<MenuVO> mMnu = new ArrayList<MenuVO>();
		List<MenuVO> lMnu = new ArrayList<MenuVO>();
		
		for (MenuVO itm : mnuLst) {
			int lvl = itm.getLvl();
			
			switch(lvl) {
				case 1:
					tMnu.add(itm);
				case 2:
					mMnu.add(itm);
				case 3:
					lMnu.add(itm);
			}
		}
		
		Map<String, Object> forMenu = new HashMap<String, Object>();
		forMenu.put("tMnu", tMnu);
		forMenu.put("mMnu", mMnu);
		forMenu.put("lMnu", lMnu);
		
		return forMenu;
	}
	
	@Override
	public List<?> selectSourceMenu() throws Exception {
		return menuMapper.selectSourceMenu();
	}
	
	@Override
	public List<?> selectParentMenu(MenuVO memuVO) throws Exception {
		return menuMapper.selectParentMenu(memuVO);
	}
}
