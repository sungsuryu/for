package egovframework.knia.foreign.exchange.service;

import java.util.HashMap;
import java.util.List;

import egovframework.knia.foreign.exchange.vo.BoardVO;
import egovframework.knia.foreign.exchange.vo.FileVO;

public interface BoardService {
	/**
	 * 게시판 관리 총 문서 개수 조회
	 * @param BoardVO 게시판정보
	 * @return int 총 문서 갯수
	 * @throws Exception
	 */
	int selectBoardCnt(BoardVO boardVO) throws Exception;
	
	/**
	 * 게시판 관리 문서 리스트 조회
	 * @param HashMap 게시판 조회정보
	 * @return BoardVO 게시판정보
	 * @throws Exception
	 */
	List<?> selectBoardList(BoardVO boardVO) throws Exception;
	
	/**
	 * 게시판 관리 문서  조회
	 * @param int 게시글 번호
	 * @throws Exception
	 */
	BoardVO selectBoard(int boardIdx) throws Exception;
	
	/**
	 * 게시판 관리 조회수 증가
	 * @param int 게시글 번호
	 * @throws Exception
	 */
	void updateBoardViewCnt(int boardIdx) throws Exception;
	
	/**
	 * 게시판 등록
	 * @param BoardVO 게시판 등록 정보
	 * @return int 게시판 순번
	 * @throws Exception
	 */
	int insertBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * 게시판 수정
	 * @param BoardVO 게시판 수정 정보
	 * @throws Exception
	 */
	void updateBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * 게시판 삭제
	 * @param INT 게시판 등록번호
	 * @throws Exception
	 */
	void deleteBoard(int boardIdx) throws Exception;
	
	/**
	 * 게시판 관리 문서 리스트 조회
	 * @param FileVO 파일정보
	 * @return List 파일정보 리스트
	 * @throws Exception
	 */
	List<?> selectFileList(FileVO fileVO) throws Exception;

	/**
	 * 게시판 첨부파일 다운로드
	 * @param FileVO 파일정보
	 * @return List 파일정보
	 * @throws Exception
	 */
	List<?> selectFile(FileVO fileVO) throws Exception;
	
	/**
	 * FAQ 문서 다운로드
	 * @return List 파일정보
	 * @throws Exception
	 */
	List<?> selectFaqFile() throws Exception;
	
	/**
	 * 첨부파일 삭제
	 * @param FileVO 파일정보
	 * @throws Exception
	 */
	void deleteFile(FileVO fileVO) throws Exception;
	
	/**
	 * FAQ 총 문서 개수 조회
	 * @param BoardVO FAQ정보
	 * @return int 총 문서 갯수
	 * @throws Exception
	 */
	int selectFaqCnt(BoardVO boardVO) throws Exception;
	
	/**
	 * FAQ 조회
	 * @param BoardVO FAQ 조회정보
	 * @return List<?> FAQ정보
	 * @throws Exception
	 */
	List<?> selectFaqList(BoardVO boardVO) throws Exception;
	
	/**
	 * FAQ 최종반영여부 업데이트
	 * @param BoardVO FAQ 정보
	 * @throws Exception
	 */
	void updateFaqUseYn(BoardVO boardVO) throws Exception;
	
	/**
	 * FAQ 등록
	 * @param BoardVO 게시판 등록 정보
	 * @return int FAQ 순번
	 * @throws Exception
	 */
	int insertFaq(BoardVO boardVO) throws Exception;
	
	/**
	 * FAQ파일 반영여부 업데이트
	 * @param FileVO 파일정보
	 * @throws Exception
	 */
	void updateFaqFile(FileVO fileVO) throws Exception;
}
