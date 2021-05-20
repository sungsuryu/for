package egovframework.knia.foreign.exchange.dao.mapper;

import egovframework.knia.foreign.exchange.vo.KaTalkVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("kaTalkMapper")
public interface KaTalkMapper {

	void insertKaTalk(KaTalkVO kaTalkVO) throws Exception;
}
