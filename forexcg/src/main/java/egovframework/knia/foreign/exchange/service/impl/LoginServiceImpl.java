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
import egovframework.com.cmm.util.EgovFileScrty;
import egovframework.com.cmm.util.EgovNumberUtil;

@Service("loginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Resource(name="loginMapper")
	private LoginMapper loginMapper;
	
	@Override
	public UserVO selectUser(LoginVO loginVO) throws Exception {
		
		String encPassword = EgovFileScrty.encryptPassword(loginVO.getPassword(), loginVO.getLoginId());
		loginVO.setPassword(encPassword);

		UserVO userVO = loginMapper.selectUser(loginVO);
		
		if (userVO != null && !userVO.getUserId().equals("") && !userVO.getPassword().equals("")) {
			// 인증번호 생성
			this.insertAuthHist(userVO);
			
			return userVO;
		} else {
			userVO = new UserVO();
		}
		
		return userVO;
	}
	
	/**
	 * 2차 인증번호 등록
	 * @param userVO
	 * @throws Exception
	 */
	private void insertAuthHist(UserVO userVO) throws Exception {
		// 2차인증번호 생성
		String authNum = EgovNumberUtil.getRandomNum(100000, 999999) + "";

		LoginAuthHistVO loginAuthHistVO = new LoginAuthHistVO();
		loginAuthHistVO.setUserId(userVO.getUserId());
		loginAuthHistVO.setAuthNum(authNum);
		
		loginMapper.insertAuthHist(loginAuthHistVO);
	}
	
	private LoginAuthHistVO getAuthNum(LoginAuthHistVO loginAuthHistVO) throws Exception {
		return loginMapper.selectAuthNum(loginAuthHistVO);
	}

	@Override
	public void deleteAuthNum(LoginVO loginVO) throws Exception {
		loginMapper.updateExpireOtp(loginVO);
	}
	
	@Override
	public boolean loginAuthNum(LoginAuthHistVO loginAuthHistVO) throws Exception {
		
		LoginAuthHistVO getHis = this.getAuthNum(loginAuthHistVO);
		
		if (getHis != null) {
			if (loginAuthHistVO.getAuthNum().equals(getHis.getAuthNum())) {
				return true;
			}
		}
		
		return false;
	}
}
