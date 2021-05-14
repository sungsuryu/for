package egovframework.knia.foreign.exchange.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovSmsInfoService;
import egovframework.com.cmm.util.EgovFileScrty;
import egovframework.knia.foreign.exchange.dao.mapper.TermsAgreeHistMapper;
import egovframework.knia.foreign.exchange.dao.mapper.UserAlarmMapper;
import egovframework.knia.foreign.exchange.dao.mapper.UserMapper;
import egovframework.knia.foreign.exchange.service.JoinService;
import egovframework.knia.foreign.exchange.vo.TermsAgreeHistVO;
import egovframework.knia.foreign.exchange.vo.UserAlarmVO;
import egovframework.knia.foreign.exchange.vo.UserVO;

@Service("joinService")
public class JoinServiceImpl implements JoinService {

	@Resource(name = "userMapper")
	UserMapper userMapper;
	
	@Resource(name = "userAlarmMapper")
	UserAlarmMapper userAlarmMapper;
	
	@Resource(name = "termsAgreeHistMapper")
	TermsAgreeHistMapper termsAgreeHistMapper;
	
	@Resource(name = "EgovSmsInfoService")
	EgovSmsInfoService egovSmsInfoService;
	
	@Override
	public void UserJoin(UserVO userVO) throws Exception {
		
		String encPassword = EgovFileScrty.encryptPassword(userVO.getPassword(), userVO.getUserId());
		userVO.setPassword(encPassword);
		
		userVO.setOfficeTelNum(egovSmsInfoService.phoneNumber(userVO.getOfficeTelNum()));
		userVO.setCellNum(egovSmsInfoService.phoneNumber(userVO.getCellNum()));
		
		// 신규사용자 등록
		userMapper.insertNewUser(userVO);
		
		// 알림톡 등록 - 신규등록시 이전알람 삭제 불필요.
		int talkCnt = userVO.getAlrmType().length;
		if (talkCnt > 0) {
			String alarmType[] = userVO.getAlrmType();
			UserAlarmVO userAlarmVO = new UserAlarmVO();
			userAlarmVO.setUserId(userVO.getUserId());
			userAlarmVO.setIsRcvAlrm("Y");
			
			// 이전알람 삭제
			userAlarmMapper.deleteUserAlarm(userAlarmVO);
			
			for (int i=0; i<talkCnt; i++) {
				userAlarmVO.setAlrmType(alarmType[i]);
				// 신규알람 등록
				userAlarmMapper.insertUserAlarm(userAlarmVO);
			}
		}

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
