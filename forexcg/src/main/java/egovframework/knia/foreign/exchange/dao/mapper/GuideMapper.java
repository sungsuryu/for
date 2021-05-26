package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.GuideVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("guideMapper")
public interface GuideMapper {
	/**
	 * 도움말 조회
	 * @param GuideVO 도움말정보
	 * @return List<?> 도움말정보
	 * @throws Exception
	 */
	GuideVO selectGuide(GuideVO guideVO) throws Exception;
	
	/**
	 * 도움말 수정
	 * @param GuideVO 도움말 수정정보
	 * @throws Exception
	 */
	void updateGuide(GuideVO guideVO) throws Exception;
}
