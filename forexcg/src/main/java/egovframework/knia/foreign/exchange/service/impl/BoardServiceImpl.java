package egovframework.knia.foreign.exchange.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.knia.foreign.exchange.dao.mapper.BoardMapper;
import egovframework.knia.foreign.exchange.service.BoardService;
import egovframework.knia.foreign.exchange.vo.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name="boardMapper")
	private BoardMapper boardMapper;
	
	@Override
	public BoardVO selectListCnt(String boardtype) throws Exception {
		BoardVO boardVO = boardMapper.selectBoardCnt(boardtype);
		return boardVO;
	}

}
