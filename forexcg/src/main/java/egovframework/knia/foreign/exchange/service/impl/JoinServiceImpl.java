package egovframework.knia.foreign.exchange.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovSmsInfoService;
import egovframework.com.cmm.util.EgovFileScrty;
import egovframework.com.cmm.util.EgovNumberUtil;
import egovframework.knia.foreign.exchange.dao.mapper.AuthNumMapper;
import egovframework.knia.foreign.exchange.dao.mapper.LoginMapper;
import egovframework.knia.foreign.exchange.dao.mapper.TermsAgreeHistMapper;
import egovframework.knia.foreign.exchange.dao.mapper.UserAlarmMapper;
import egovframework.knia.foreign.exchange.dao.mapper.UserMapper;
import egovframework.knia.foreign.exchange.service.JoinService;
import egovframework.knia.foreign.exchange.vo.CellAuthVO;
import egovframework.knia.foreign.exchange.vo.LoginAuthHistVO;
import egovframework.knia.foreign.exchange.vo.TermsAgreeHistVO;
import egovframework.knia.foreign.exchange.vo.UserAlarmVO;
import egovframework.knia.foreign.exchange.vo.UserVO;

@Service("joinService")
public class JoinServiceImpl implements JoinService {

	@Resource(name = "userMapper")
	UserMapper userMapper;
	
	@Resource(name = "userAlarmMapper")
	UserAlarmMapper userAlarmMapper;
	
	@Resource(name = "authNumMapper")
	AuthNumMapper authNumMapper;
	
	@Resource(name = "termsAgreeHistMapper")
	TermsAgreeHistMapper termsAgreeHistMapper;
	
	@Resource(name = "EgovSmsInfoService")
	EgovSmsInfoService egovSmsInfoService;
	
	@Resource(name="loginMapper")
	private LoginMapper loginMapper;
	
	@Override
	public void UserJoin(UserVO userVO) throws Exception {
		
		String encPassword = EgovFileScrty.encryptPassword(userVO.getPassword(), userVO.getUserId());
		userVO.setPassword(encPassword);
		
		String encAuthVal = EgovFileScrty.encryptPassword(userVO.getAuthNum(), userVO.getCellNum());
		if (!userVO.getAuthKey().equals(encAuthVal)) {
			throw new Exception("휴대폰인증 실패");
		}
		
		// 전화번호 형식 렌더링
		userVO.setOfficeTelNum(egovSmsInfoService.phoneNumber(userVO.getOfficeTelNum()));
		userVO.setCellNum(egovSmsInfoService.phoneNumber(userVO.getCellNum()));
		
		// 신규사용자 등록
		userMapper.insertNewUser(userVO);
		
		// 알림톡 등록 - 신규등록시 이전알람 삭제 불필요.
		if (userVO.getAlrmType() != null) {

			String alarmType[] = userVO.getAlrmType();
			UserAlarmVO userAlarmVO = new UserAlarmVO();
			userAlarmVO.setUserId(userVO.getUserId());
			userAlarmVO.setIsRcvAlrm("Y");
			
			// 이전알람 삭제
			userAlarmMapper.deleteUserAlarm(userAlarmVO);
			
			for (int i=0; i<userVO.getAlrmType().length; i++) {
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
	
	@Override
	public CellAuthVO generateAuthNum(CellAuthVO cellAuthVO) throws Exception {
		
		CellAuthVO setInfo = new CellAuthVO();
		setInfo.setCellNum(cellAuthVO.getCellNum());
		setInfo.setAuthNum(EgovNumberUtil.getRandomNum(100000, 999999) + "");	// 인증번호
		setInfo.setAuthType(cellAuthVO.getAuthType());
		
		// 검증문자열 생성
		String encAuthNum = EgovFileScrty.encryptPassword(setInfo.getAuthNum(), setInfo.getCellNum());
		setInfo.setEncAuthVal(encAuthNum);
		
		authNumMapper.insertAuthNum(setInfo);

		return cellAuthVO;
	}
	
	@Override
	public CellAuthVO compareAuthNum(CellAuthVO cellAuthVO) throws Exception {
		
		CellAuthVO reqAuthInfo = authNumMapper.selectLastAuthNum(cellAuthVO);
		if (reqAuthInfo == null)
			return null;
		
		if (cellAuthVO.getAuthNum().equals(reqAuthInfo.getAuthNum())) {
			return reqAuthInfo;
		} else {
			authNumMapper.updateExpireAuthNum(cellAuthVO);
			
			return null;
		}
	}
	
	@Override
	public boolean compairAuthKey(UserVO userVO) throws Exception {
		
//		String cellNum = userVO.getCellNum();
        String authKey = userVO.getAuthKey();
        
        String encAuthNum = EgovFileScrty.encryptPassword(userVO.getAuthNum(), userVO.getCellNum());
        
        if (!encAuthNum.equals(authKey)) {
        	return false;
        }
        return true;
	}
}
