package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.SendReceiveHistVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("sendReceiveHistMapper")
public interface SendReceiveHistMapper {

	List<?> selectSendReceiveHistList() throws Exception;
	
	/**
	 * 전송요청 후 아직 결과를 수신하지 않은 이력 조회
	 * @param sendReceiveHistVO 전송요청한 VO
	 * @return int 결과를 수신하지 않은 요청 건 수
	 * @throws Exception
	 */
	int countNotyetFinish(SendReceiveHistVO sendReceiveHistVO) throws Exception;
	
	void insertSendHist(SendReceiveHistVO sendReceiveHistVO) throws Exception;
}
