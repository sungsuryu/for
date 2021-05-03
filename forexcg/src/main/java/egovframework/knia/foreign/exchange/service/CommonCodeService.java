package egovframework.knia.foreign.exchange.service;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.CommonCodeVO;

public interface CommonCodeService {

	List<?> selectCodeList(String prtCmmCd) throws Exception;
	
	List<?> selectGroupCodeList() throws Exception;
	
	CommonCodeVO selectCode(String cmmCd) throws Exception;
	
	void insertCode(CommonCodeVO commonCodeVO) throws Exception;
	
	void updateCode(CommonCodeVO commonCodeVO) throws Exception;
	
	void deleteCode(String cmmCd) throws Exception;
}
