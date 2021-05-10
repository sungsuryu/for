package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.MenuVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("menuMapper")
public interface MenuMapper {

	MenuVO selectMenu(MenuVO menuVO) throws Exception;
	
	List<?> selectMenuList(MenuVO menuVO) throws Exception;
}