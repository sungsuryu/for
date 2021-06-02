package egovframework.knia.foreign.exchange.service;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.FileVO;
import egovframework.knia.foreign.exchange.vo.PopupVO;

public interface PopupService {
	/**
	 * 팝업 총 문서 개수 조회
	 * @param PopupVO 게시판정보
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
	 * 팝업 조회
	 * @param PopupVO 팝업 조회정보
	 * @return PopupVO 팝업정보
	 * @throws Exception
	 */
	PopupVO selectPopup(PopupVO popupVO) throws Exception;
	
	/**
	 * 팝업 등록
	 * @param popupVO 팝업 등록 정보
	 * @return popupVO 등록된 팝업 순번
	 * @throws Exception
	 */
	int insertPopup(PopupVO popupVO) throws Exception;
	
	/**
	 * 팝업 수정
	 * @param popupVO 팝업 수정 정보
	 * @throws Exception
	 */
	void updatePopup(PopupVO popupVO) throws Exception;
	
	/**
	 * 팝업 삭제
	 * @param popupVO 팝업 삭제정보
	 * @throws Exception
	 */
	void deletePopup(PopupVO popupVO) throws Exception;
	
	/**
	 * 팝업 첨부파일 조회
	 * @param FileVO 파일정보
	 * @return List 파일정보 리스트
	 * @throws Exception
	 */
	List<?> selectFileList(FileVO fileVO) throws Exception;

	/**
	 * 팝업 첨부파일 다운로드
	 * @param FileVO 파일정보
	 * @return List 파일정보
	 * @throws Exception
	 */
	List<?> selectFile(FileVO fileVO) throws Exception;

	/**
	 * 첨부파일 삭제
	 * @param FileVO 파일정보
	 * @throws Exception
	 */
	void deleteFile(FileVO fileVO) throws Exception;
}
