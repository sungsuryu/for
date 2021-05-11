package egovframework.knia.foreign.exchange.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.knia.foreign.exchange.cmm.ResponseResult;
import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
import egovframework.knia.foreign.exchange.service.BoardService;
import egovframework.knia.foreign.exchange.vo.BoardVO;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name = "boardService")
	private BoardService boardService;
	
	@RequestMapping(value="/notice.do")
	public String notice(@ModelAttribute("boardVO") BoardVO boardVO1, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 관리 화면");
	
		return "setting/board";
	}
	
	@RequestMapping(value="/setting/noticelist.ajax")
	public String noticeList(@ModelAttribute("boardVO") BoardVO boardVO1, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 리스트 가져오기");
		int max_content = 10;
		int max_page = 10;
		int total_page = 0;
		int start_num = 0;
		int end_num = 0;
		int cnt = boardService.selectListCnt("NOTICE");
//		int page_num = Integer.parseInt(request.getAttribute("page_num").toString());
		
		int page_num = 1;
		if(cnt == 0){
			return null;
		}
		total_page = cnt/max_content;//총 표출 페이지 개수
		if(total_page % max_content > 0){
			total_page++;
		}
		
		start_num = (page_num - 1) * max_content + 1;
		end_num = start_num + max_content - 1;
		
		HashMap<String, Object> boardInfo = new HashMap<String, Object>();
		boardInfo.put("boardtype", "NOTICE");
		boardInfo.put("start_num", start_num);
		boardInfo.put("end_num", end_num);
		List<?> boardList = boardService.selectBoardList(boardInfo);
		ArrayList <HashMap<String, Object>> notice_list = new ArrayList<HashMap<String, Object>>();

		if (boardList.size() > 0) {
			for(int i = 0; i < boardList.size(); i++){
				BoardVO boardVO = (BoardVO) boardList.get(i);
				HashMap<String, Object> notice = new HashMap<String, Object>();
				notice.put("list_num", boardVO.getListNum());
				notice.put("board_idx", boardVO.getBoardidx());
				notice.put("board_title", boardVO.getTitle());
				notice.put("user_id", boardVO.getUser_id());
				notice.put("user_nm", boardVO.getUser_nm());
				notice.put("updt_date", boardVO.getUpdtdate());
				notice.put("view_cnt", boardVO.getViewcnt());
				notice.put("file_cnt", boardVO.getFileCnt());
				notice_list.add(notice);
			}
			
			HashMap<String, Object> noticeInfo = new HashMap<String, Object>();
			noticeInfo.put("page_num", page_num);
			noticeInfo.put("total_page", total_page);
			noticeInfo.put("total_cnt", cnt);
			noticeInfo.put("notice_list", notice_list);
			noticeInfo.put("status", "SUCCESS");

			logger.debug("공지사항 : 공지사항 목록을 불러옵니다.");
			model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(noticeInfo));
		} else {
			HashMap<String, Object> noticeInfo = new HashMap<String, Object>();
			noticeInfo.put("status", "EMPTY");
			logger.info("공지사항 : 공지사항 목록이 없습니다.");
			model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(noticeInfo));
		}
		
		return "jsonView";
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
