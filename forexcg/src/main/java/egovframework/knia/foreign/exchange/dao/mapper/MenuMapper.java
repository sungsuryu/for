package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.MenuVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("menuMapper")
public interface MenuMapper {

	MenuVO selectMenu(MenuVO menuVO) throws Exception;
	
	MenuVO selectMenuFindByUrl(String sPath) throws Exception;
	
	List<?> selectMenuList(MenuVO menuVO) throws Exception;
	
	void insertMenu(MenuVO menuVO) throws Exception;
	
	void updateMenu(MenuVO menuVO) throws Exception;
	
	List<?> selectMenuTree(MenuVO menuVO) throws Exception;
	
	List<?> selectSourceMenu() throws Exception;
	
	List<?> selectParentMenu(MenuVO menuVO) throws Exception;
}
