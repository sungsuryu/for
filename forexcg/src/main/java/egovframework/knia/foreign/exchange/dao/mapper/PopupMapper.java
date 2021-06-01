package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.BoardVO;
import egovframework.knia.foreign.exchange.vo.PopupVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("popupMapper")
public interface PopupMapper {
	/**
	 * 팝업 총 문서 개수 조회
	 * @param BoardVO 게시판정보
	 * @return PopupVO 팝업정보
	 * @throws Exception
	 */
	PopupVO selectPopupCnt(PopupVO popupVO) throws Exception;
	
	/**
	 * 팝업 리스트 조회
	 * @param PopupVO 팝업 조회정보
	 * @return List<?> 팝업정보
	 * @throws Exception
	 */
	List<?> selectPopupList(PopupVO popupVO) throws Exception;
	
	/**
	 * 팝업 등록
	 * @param popupVO 팝업 등록 정보
	 * @throws Exception
	 */
	void insertPopup(PopupVO popupVO) throws Exception;

}
