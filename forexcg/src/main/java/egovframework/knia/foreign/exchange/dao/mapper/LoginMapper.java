package egovframework.knia.foreign.exchange.dao.mapper;

import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;

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
}
