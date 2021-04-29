package egovframework.knia.foreign.exchange.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.knia.foreign.exchange.dao.mapper.LoginMapper;
import egovframework.knia.foreign.exchange.service.LoginService;
import egovframework.knia.foreign.exchange.vo.LoginAuthHistVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;
import egovframework.knia.foreign.exchange.vo.LoginAuthHistVO;
import egovframework.com.cmm.util.EgovDateUtil;
import egovframework.com.cmm.util.EgovFileScrty;
import egovframework.com.cmm.util.EgovNumberUtil;

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
			userVO.setTimestamp(EgovDateUtil.currentDateTimeString("yyyyMMddhhmmss"));
			
			this.insertAuthHist(userVO);
			
			return userVO;
		} else {
			userVO = new UserVO();
		}
		
		return userVO;
	}
	
	private void insertAuthHist(UserVO userVO) throws Exception {

		String authNum = EgovNumberUtil.getRandomNum(1000, 9999) + "";

		LoginAuthHistVO loginAuthHistVO = new LoginAuthHistVO();
		loginAuthHistVO.setUserId(userVO.getUserId());
		loginAuthHistVO.setApproveTimestamp(userVO.getTimestamp());
		loginAuthHistVO.setAuthNum(authNum);
		loginAuthHistVO.setInsrtDate(EgovDateUtil.currentDateTimeString("yyyy-MM-dd HH:mm:ss"));
		
		loginMapper.insertAuthHist(loginAuthHistVO);
	}
	
	public String getAuthNum(LoginAuthHistVO loginAuthHistVO) throws Exception {
		String authNum = loginMapper.selectAuthNum(loginAuthHistVO);
		
		return authNum;
	}
}
