package egovframework.knia.foreign.exchange.dao.mapper;

import egovframework.knia.foreign.exchange.vo.FindIdVO;
import egovframework.knia.foreign.exchange.vo.FindPwdVO;
import egovframework.knia.foreign.exchange.vo.UserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("findUserAccountMapper")
public interface FindUserAccountMapper {

	UserVO selectUserIdByCellNumUserNm(FindIdVO userVO) throws Exception;
	
	// 승인전, 계정잠김, 미사용, 삭제된 계정은 조회되지 않음.
	UserVO selectUserByCellNumUserNmUserId(FindPwdVO userVO) throws Exception;
}
