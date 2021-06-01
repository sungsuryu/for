package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.ActiveHistVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("activeHistMapper")
public interface ActiveHistMapper {

	void insertActiveHist(ActiveHistVO activeHistVO) throws Exception;
	
	List<?> selectActiveHistFindByUserId(ActiveHistVO activeHistVO) throws Exception;
	
	void deleteActiveHist(ActiveHistVO activeHistVO) throws Exception;
	
	void updateActiveHist(ActiveHistVO activeHistVO) throws Exception;
}
