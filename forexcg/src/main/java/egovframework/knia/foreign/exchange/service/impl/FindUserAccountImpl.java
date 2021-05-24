package egovframework.knia.foreign.exchange.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.knia.foreign.exchange.dao.mapper.FindUserAccountMapper;
import egovframework.knia.foreign.exchange.dao.mapper.UserMapper;
import egovframework.knia.foreign.exchange.service.FindUserAccount;
import egovframework.knia.foreign.exchange.service.KaTalkService;
import egovframework.knia.foreign.exchange.vo.FindIdVO;
import egovframework.knia.foreign.exchange.vo.FindPwdVO;
import egovframework.knia.foreign.exchange.vo.UserVO;
import egovframework.com.cmm.util.EgovFileScrty;
import egovframework.knia.foreign.exchange.cmm.GeneratePassword;

@Service("findUserAccount")
public class FindUserAccountImpl implements FindUserAccount {

	@Resource(name="userMapper")
	UserMapper userMapper;
	
	@Resource(name="findUserAccountMapper")
	FindUserAccountMapper findUserAccountMapper;
	
	@Resource(name="kaTalkService")
	KaTalkService kaTalkService;

	
	private static final Logger logger = LoggerFactory.getLogger(FindUserAccount.class);
			
	public boolean findLoginId(FindIdVO findIdVO) throws Exception {
		
		UserVO getUser = findUserAccountMapper.selectUserIdByCellNumUserNm(findIdVO);
		
		if (getUser != null) {

			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("loginId", getUser.getUserId());
	        hMap.put("cellNum", getUser.getCellNum());
	        
	        kaTalkService.sendFindUserId(hMap);
	        
	        return true;
		}
		
		return false;
	}
	
	public boolean findPassword(FindPwdVO findPwdVO) throws Exception {
		
		UserVO getUser = findUserAccountMapper.selectUserByCellNumUserNmUserId(findPwdVO);
		
		if (getUser != null) {

			String newPassword = GeneratePassword.getRamdomPassword(8);
			logger.debug("새로운 비밀번호: {}", newPassword);
			
			newPassword = EgovFileScrty.encryptPassword(newPassword, getUser.getUserId());
			
			UserVO setUser = new UserVO();
			setUser.setUserId(getUser.getUserId());
			setUser.setPassword(newPassword);
			// 새로운 비밀번호로 업데이트
			userMapper.updateNewPassword(setUser);
			
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("loginId", getUser.getUserId());
	        hMap.put("cellNum", getUser.getCellNum());
	        // katalk 알람 발송
	        kaTalkService.sendFindUserId(hMap);
	        
	        return true;
		}
		
		return false;
	}
}
