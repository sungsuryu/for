package egovframework.knia.foreign.exchange.service;

import java.util.List;
import java.util.Map;

import egovframework.knia.foreign.exchange.vo.MenuVO;

public interface MenuService {

	MenuVO getMenuFromParentMnuId(MenuVO menuVO) throws Exception;
	
	/**
	 * 메뉴정보 조회
	 * @param menuVO
	 * @return MenuVO 메뉴정보
	 * @throws Exception
	 */
	MenuVO getMenu(MenuVO menuVO) throws Exception;
	
	List<?> selectMenuList(MenuVO memuVO) throws Exception;

	Map<String, Object> selectMenuTree(MenuVO memuVO) throws Exception;
	
	List<?> selectSourceMenu() throws Exception;
	
	List<?> selectParentMenu(MenuVO memuVO) throws Exception;
	
	void updateMenu(MenuVO menuVO) throws Exception;
	
	void deleteMenu(MenuVO memuVO) throws Exception;
}
