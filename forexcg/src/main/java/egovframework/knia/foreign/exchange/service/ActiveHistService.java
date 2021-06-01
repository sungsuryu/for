package egovframework.knia.foreign.exchange.service;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.ActiveHistVO;

public interface ActiveHistService {

	void insertActiveHist(ActiveHistVO activeHistVO) throws Exception;
	
	List<?> selectActiveHistFindByUserId(ActiveHistVO activeHistVO) throws Exception;
	
	void deleteActiveHist(ActiveHistVO activeHistVO) throws Exception;
	
	void updateActiveHist(ActiveHistVO activeHistVO) throws Exception;
}
