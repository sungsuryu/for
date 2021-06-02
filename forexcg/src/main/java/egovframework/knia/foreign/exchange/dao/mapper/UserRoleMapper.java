package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.UserRoleVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("userRoleMapper")
public interface UserRoleMapper {

	List<String> selectUserRoleGroupByRoleId() throws Exception;
	
	List<?> selectUserRoleFindByRoleId(UserRoleVO userRoleVO) throws Exception;
	
	/**
	 * 권한(ROLE)별 ServletPath 를 비교
	 * @param userRoleVO
	 * @return UserRoleVO 현 메뉴정보 반환
	 * @throws Exception
	 */
	UserRoleVO selectMenuIdFindByRoleId(UserRoleVO userRoleVO) throws Exception;
}
