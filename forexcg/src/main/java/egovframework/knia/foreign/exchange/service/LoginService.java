package egovframework.knia.foreign.exchange.service;

import egovframework.knia.foreign.exchange.vo.LoginAuthHistVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;

public interface LoginService {

	UserVO selectUser(LoginVO loginVO) throws Exception;
	
//	LoginAuthHistVO getAuthNum(LoginAuthHistVO loginAuthHistVO) throws Exception;
	
	void deleteAuthNum(LoginVO loginVO) throws Exception;
	
	boolean loginAuthNum(LoginAuthHistVO loginAuthHistVO) throws Exception;
}
