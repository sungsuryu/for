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

import egovframework.com.cmm.util.EgovFileScrty;

@Service("loginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Resource(name="loginMapper")
	private LoginMapper loginMapper;
	
	@Override
	public UserVO selectUser(LoginVO loginVO) throws Exception {
		
		String encPassword = EgovFileScrty.encryptPassword(loginVO.getPassword(), loginVO.getLoginId());
		loginVO.setPassword(encPassword);

		UserVO userVO = loginMapper.selectUser(loginVO);
		
		if (userVO != null && !userVO.getUserId().equals("") && !userVO.getPassword().equals("")) {
			
			// TODO OTP 발생 필요함.
			
			return userVO;
		} else {
			userVO = new UserVO();
		}
		
		return userVO;
	}
}
