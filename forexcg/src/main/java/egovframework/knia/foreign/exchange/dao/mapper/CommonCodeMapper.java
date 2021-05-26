package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.CommonCodeVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("commonCodeMapper")
public interface CommonCodeMapper {

	CommonCodeVO selectCode(String cmmCd) throws Exception;
	
	List<?> selectCodeList(String prtCmmCd) throws Exception;

	List<?> selectGroupCodeList() throws Exception;
	
	int selectMxSortNum(CommonCodeVO commonCodeVO) throws Exception;
	
	void insertCode(CommonCodeVO commonCodeVO) throws Exception;
	
	void updateCode(CommonCodeVO commonCodeVO) throws Exception;
	
	void deleteCode(CommonCodeVO commonCodeVO) throws Exception;
}
