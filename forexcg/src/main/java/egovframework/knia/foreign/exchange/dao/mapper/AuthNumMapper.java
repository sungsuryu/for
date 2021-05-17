package egovframework.knia.foreign.exchange.dao.mapper;

import egovframework.knia.foreign.exchange.vo.CellAuthVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("authNumMapper")
public interface AuthNumMapper {

	void insertAuthNum(CellAuthVO cellAuthVO) throws Exception;
	
	CellAuthVO selectLastAuthNum(CellAuthVO cellAuthVO) throws Exception;
	
	void updateExpireAuthNum(CellAuthVO cellAuthVO) throws Exception;
} 
