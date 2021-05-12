package egovframework.knia.foreign.exchange.service;

import egovframework.knia.foreign.exchange.vo.UserVO;

public interface JoinService {

	void UserJoin(UserVO userVO) throws Exception;

	int countUser(String userId) throws Exception;

}
