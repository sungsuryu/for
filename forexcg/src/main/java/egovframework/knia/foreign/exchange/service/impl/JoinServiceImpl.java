package egovframework.knia.foreign.exchange.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.util.EgovFileScrty;
import egovframework.knia.foreign.exchange.dao.mapper.TermsAgreeHistMapper;
import egovframework.knia.foreign.exchange.dao.mapper.UserMapper;
import egovframework.knia.foreign.exchange.service.JoinService;
import egovframework.knia.foreign.exchange.vo.TermsAgreeHistVO;
import egovframework.knia.foreign.exchange.vo.UserVO;

@Service("joinService")
public class JoinServiceImpl implements JoinService {

	@Resource(name = "userMapper")
	UserMapper userMapper;
	
	@Resource(name = "termsAgreeHistMapper")
	TermsAgreeHistMapper termsAgreeHistMapper;
	
	@Override
	public void UserJoin(UserVO userVO) throws Exception {
		
		String encPassword = EgovFileScrty.encryptPassword(userVO.getPassword(), userVO.getUserId());
		userVO.setPassword(encPassword);
		
		// 신규사용자 등록
		userMapper.insertNewUser(userVO);
		
		// 개인정보수집동의 이력 저장
		TermsAgreeHistVO hisVO = new TermsAgreeHistVO();
		hisVO.setUserId(userVO.getUserId());
		hisVO.setAgreeYn("Y");
		termsAgreeHistMapper.insertTermsAgree(hisVO);
	}
	
	@Override
	public int countUser(String userId) throws Exception {
		return (Integer)userMapper.countUser(userId);
	}
}
