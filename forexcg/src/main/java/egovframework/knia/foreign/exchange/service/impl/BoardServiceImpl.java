package egovframework.knia.foreign.exchange.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.knia.foreign.exchange.dao.mapper.BoardMapper;
import egovframework.knia.foreign.exchange.dao.mapper.FileServiceMapper;
import egovframework.knia.foreign.exchange.service.BoardService;
import egovframework.knia.foreign.exchange.vo.BoardVO;
import egovframework.knia.foreign.exchange.vo.FileVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name="boardMapper")
	private BoardMapper boardMapper;
	
	@Resource(name="fileServiceMapper")
	private FileServiceMapper fileServiceMapper;
	
	@Override
	public int selectBoardCnt(BoardVO boardVO) throws Exception {
		int cnt = boardMapper.selectBoardCnt(boardVO);
		return cnt;
	}

	@Override
	public List<?> selectBoardList(BoardVO boardVO) throws Exception {
		return boardMapper.selectBoardList(boardVO);
	}

	@Override
	public BoardVO selectBoard(int boardIdx) throws Exception {
		return boardMapper.selectBoard(boardIdx);
	}
	
	@Override
	public void updateBoardViewCnt(int boardIdx) throws Exception {
		boardMapper.updateBoardViewCnt(boardIdx);
		
	}

	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {
		boardMapper.insertBoard(boardVO);
		int boardIdx = boardVO.getBoardIdx();
		return boardIdx;
		
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		boardMapper.updateBoard(boardVO);
		
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
		
	}

	@Override
	public List<?> selectFileList(FileVO fileVO) throws Exception {
		return fileServiceMapper.selectFileList(fileVO);
	}
	
	@Override
	public List<?> selectFile(FileVO fileVO) throws Exception {
		return fileServiceMapper.selectFile(fileVO);
	}
}
