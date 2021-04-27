package egovframework.knia.foreign.exchange.service;

import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;

public interface LoginService {

	UserVO selectUser(LoginVO loginVO) throws Exception;
}
