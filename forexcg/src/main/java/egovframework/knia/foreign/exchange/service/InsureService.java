package egovframework.knia.foreign.exchange.service;

import java.util.HashMap;
import java.util.List;

import egovframework.knia.foreign.exchange.vo.InsureVO;

public interface InsureService {

	/**
	 * 보험사 코드 목록 조회
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	List<?> selectInsureList(HashMap<String, Object> hMap) throws Exception;
	
	List<?> selectInsureCdList(InsureVO insureVO) throws Exception;

}
