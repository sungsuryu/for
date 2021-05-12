package egovframework.knia.foreign.exchange.dao.mapper;

import egovframework.knia.foreign.exchange.vo.TermsAgreeHistVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("termsAgreeHistMapper")
public interface TermsAgreeHistMapper {

	void insertTermsAgree(TermsAgreeHistVO termsAgreeHistVO) throws Exception;
}
