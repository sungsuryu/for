package egovframework.knia.foreign.exchange.dao.mapper;

import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;
import egovframework.knia.foreign.exchange.vo.LoginAuthHistVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("loginMapper")
public interface LoginMapper {

	/**
	 * 로그인 사용자 조회
	 * @param loginVO 로그인정보
	 * @return UserVO 사용자정보
	 * @throws Exception
	 */
	UserVO selectUser(LoginVO loginVO) throws Exception;
	
	/**
	 * 2차인증 AuthNum 생성
	 * @param loginAuthHistVO 인증정보
	 * @throws Exception
	 */
	void insertAuthHist(LoginAuthHistVO loginAuthHistVO) throws Exception;
	
	/**
	 * AuthNum 조회
	 * @param loginAuthHistVO 인증 요청자 정보
	 * @return LoginAuthHistVO 인증번호 및 timestamp
	 * @throws Exception
	 */
	LoginAuthHistVO selectAuthNum(LoginAuthHistVO loginAuthHistVO) throws Exception;
	
	void updateExpireOtp(LoginVO loginVO) throws Exception;
	
	int countUser() throws Exception;
}
