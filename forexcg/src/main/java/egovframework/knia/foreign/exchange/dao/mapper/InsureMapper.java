package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.InsureVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("insurMapper")
public interface InsureMapper {

	List<?> selectInsurList(InsureVO insurVO) throws Exception;
}
