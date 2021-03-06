package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.HashMap;
import java.util.List;

import egovframework.knia.foreign.exchange.vo.InsureVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("insureMapper")
public interface InsureMapper {

	List<?> selectInsureList(HashMap<String, Object> hMap) throws Exception;
	
	List<?> selectInsureCdList(InsureVO insureVO) throws Exception;
}
