package egovframework.knia.foreign.exchange.dao.mapper;

import egovframework.knia.foreign.exchange.vo.BoardVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("boardMapper")
public interface BoardMapper {
	/**
	 * 게시판 관리 총 문서 개수 조회
	 * @param loginVO 로그인정보
	 * @return UserVO 사용자정보
	 * @throws Exception
	 */
	BoardVO selectBoardCnt(String boardtype) throws Exception;
}
