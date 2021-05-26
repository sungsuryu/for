package egovframework.knia.foreign.exchange.service;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.CommonCodeVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;

public interface CommonCodeService {

	public List<?> selectCodeList(String prtCmmCd) throws Exception;
	
	public List<?> selectGroupCodeList() throws Exception;
	
	public CommonCodeVO selectCode(String cmmCd) throws Exception;
	
	public void insertCode(CommonCodeVO commonCodeVO) throws Exception;
	
	public void updateCode(CommonCodeVO commonCodeVO) throws Exception;
	
	public void deleteCode(CommonCodeVO commonCodeVO) throws Exception;
	
	public void addGroupCode(CommonCodeVO commonCodeVO) throws Exception;
	
	public void addSubCode(CommonCodeVO commonCodeVO) throws Exception;
}
