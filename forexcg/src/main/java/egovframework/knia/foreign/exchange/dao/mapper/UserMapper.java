package egovframework.knia.foreign.exchange.dao.mapper;

import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("userMapper")
public interface UserMapper {

	int countUser() throws Exception;
	
	UserVO selectUser(LoginVO loginVO) throws Exception;
	
	int countUserId(String userId) throws Exception;
	
	void insertNewUser(UserVO userVO) throws Exception;
}
