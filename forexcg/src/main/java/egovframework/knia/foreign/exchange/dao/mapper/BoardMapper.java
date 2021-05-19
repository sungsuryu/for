package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.HashMap;
import java.util.List;

import egovframework.knia.foreign.exchange.vo.BoardVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("boardMapper")
public interface BoardMapper {
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
	 * @return List<?> 게시판정보
	 * @throws Exception
	 */
	List<?> selectBoardList(BoardVO boardVO) throws Exception;
	
	/**
	 * 게시판 관리 문서  조회
	 * @param int 게시글 번호
	 * @return BoardVO 게시판정보
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
	 * @throws Exception
	 */
	void insertBoard(BoardVO boardVO) throws Exception;
	
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
}
