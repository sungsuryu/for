package egovframework.knia.foreign.exchange.service;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.GuideVO;

public interface GuideService {
	/**
	 * 도움말 조회
	 * @param GuideVO 도움말정보
	 * @return List<?> 도움말정보
	 * @throws Exception
	 */
	GuideVO selectGuide(GuideVO guideVO) throws Exception;
	
	/**
	 * 도움말 생성 및 수정
	 * @param GuideVO 도움말 수정정보
	 * @throws Exception
	 */
	void mergeInsertGuide(GuideVO guideVO) throws Exception;
}
