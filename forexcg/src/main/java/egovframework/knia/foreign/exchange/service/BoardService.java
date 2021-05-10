package egovframework.knia.foreign.exchange.service;

import egovframework.knia.foreign.exchange.vo.BoardVO;

public interface BoardService {
	int selectListCnt(String boardtype) throws Exception;
}
