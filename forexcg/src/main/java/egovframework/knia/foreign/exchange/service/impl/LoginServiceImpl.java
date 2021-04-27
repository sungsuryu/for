package egovframework.knia.foreign.exchange.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import egovframework.knia.foreign.exchange.dao.mapper.LoginMapper;
import egovframework.knia.foreign.exchange.service.LoginService;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("loginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Resource(name="loginMapper")
	private LoginMapper loginMapper;
	
	@Override
	public UserVO selectUser(LoginVO loginVO) throws Exception {
		
		return (UserVO)loginMapper.selectUser(loginVO);
	}
}
