package egovframework.knia.foreign.exchange.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.knia.foreign.exchange.dao.mapper.UserMapper;
import egovframework.knia.foreign.exchange.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name="userMapper")
	UserMapper userMapper;
	
	@Override
	public int countUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
