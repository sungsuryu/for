package egovframework.knia.foreign.exchange.service;

import java.util.HashMap;
import java.util.List;

import egovframework.knia.foreign.exchange.vo.BoardVO;

public interface BoardService {
	/**
	 * 게시판 관리 총 문서 개수 조회
	 * @param String 게시판 구분값
	 * @return int 총 문서 갯수
	 * @throws Exception
	 */
	int selectListCnt(String boardtype) throws Exception;
	
	/**
	 * 게시판 관리 문서 리스트 조회
	 * @param HashMap 게시판 조회정보
	 * @return BoardVO 게시판정보
	 * @throws Exception
	 */
	List<?> selectBoardList(HashMap<String, Object> boardInfo) throws Exception;
	
	/**
	 * 게시판 관리 조회수 증가
	 * @throws Exception
	 */
	void updateBoardViewCnt(HashMap<String, Object> boardInfo) throws Exception;

}
