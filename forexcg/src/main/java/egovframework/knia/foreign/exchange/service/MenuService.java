package egovframework.knia.foreign.exchange.service;

import java.util.List;
import java.util.Map;

import egovframework.knia.foreign.exchange.vo.MenuVO;

public interface MenuService {

	List<?> selectMenuList(MenuVO memuVO) throws Exception;

	Map<String, Object> selectMenuTree(MenuVO memuVO) throws Exception;
}
