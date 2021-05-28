package egovframework.knia.foreign.exchange.service;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.MenuVO;

public interface MenuService {

	List<?> selectMenuList(MenuVO memuVO) throws Exception;

}
