package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.UserRoleVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("userRoleMapper")
public interface UserRoleMapper {

	List<String> selectUserRoleGroupByRoleId() throws Exception;
	
	List<?> selectUserRoleFindByRoleId(UserRoleVO userRoleVO) throws Exception;
	
	UserRoleVO selectMenuIdFindByRoleId(UserRoleVO userRoleVO) throws Exception;
}
