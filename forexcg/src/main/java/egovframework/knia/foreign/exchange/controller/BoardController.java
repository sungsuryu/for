package egovframework.knia.foreign.exchange.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.knia.foreign.exchange.service.BoardService;
import egovframework.knia.foreign.exchange.vo.BoardVO;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name = "boardService")
	private BoardService boardService;
	
	@RequestMapping(value="/notice.do")
	public String notice(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 관리 화면");
	
		return "setting/board";
	}
	
	@RequestMapping(value="/setting/ajax/noticelist.do")
	public String noticeList(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 리스트 가져오기");
		boardVO = boardService.selectListCnt("NOTICE");
		System.out.println("KJWKJW CHECK - " + boardVO.toString());
		//return "setting/board";
		return "";
	}
	
	@RequestMapping(value="/setting/pds.do")
	public String pds(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("게시판 관리 화면");
		
		return "setting/board";
	}
	
	@RequestMapping(value="/setting/faq.do")
	public String faq(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("FAQ 관리  화면");
		
		return "setting/faq";
	}
}
