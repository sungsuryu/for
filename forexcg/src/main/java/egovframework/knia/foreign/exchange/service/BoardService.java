package egovframework.knia.foreign.exchange.service;

import egovframework.knia.foreign.exchange.vo.BoardVO;

public interface BoardService {
	BoardVO selectListCnt(String boardtype) throws Exception;
}
