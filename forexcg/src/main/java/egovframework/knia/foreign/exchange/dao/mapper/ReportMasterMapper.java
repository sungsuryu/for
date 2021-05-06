package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.ReportMasterVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("reportMasterMapper")
public interface ReportMasterMapper {

	List<?> selectReportMasterList(ReportMasterVO reportMasterVO) throws Exception;
	
}
