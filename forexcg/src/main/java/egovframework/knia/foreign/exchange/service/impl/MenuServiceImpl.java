package egovframework.knia.foreign.exchange.service.impl;

import java.util.List;

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
	public List<?> selectMenuList(MenuVO memuVO) throws Exception {
		
		return menuMapper.selectMenuList(memuVO);
	}

}
