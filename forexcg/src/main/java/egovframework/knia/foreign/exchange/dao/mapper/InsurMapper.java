package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.InsurVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("insurMapper")
public interface InsurMapper {

	List<?> selectInsurList(InsurVO insurVO) throws Exception;
}
