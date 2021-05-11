package egovframework.knia.foreign.exchange.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.util.EgovFileScrty;
import egovframework.knia.foreign.exchange.dao.mapper.UserMapper;
import egovframework.knia.foreign.exchange.service.JoinService;
import egovframework.knia.foreign.exchange.vo.UserVO;

@Service("joinService")
public class JoinServiceImpl implements JoinService {

	@Resource(name = "userMapper")
	UserMapper userMapper;
	
	@Override
	public void UserJoin(UserVO userVO) throws Exception {
		
		String encPassword = EgovFileScrty.encryptPassword(userVO.getPassword(), userVO.getUserId());
		userVO.setPassword(encPassword);
		
		userMapper.insertNewUser(userVO);
	}
}
