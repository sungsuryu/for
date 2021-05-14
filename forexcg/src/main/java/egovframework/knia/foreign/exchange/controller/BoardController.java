package egovframework.knia.foreign.exchange.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.knia.foreign.exchange.cmm.ResponseResult;
import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
import egovframework.knia.foreign.exchange.service.BoardService;
import egovframework.knia.foreign.exchange.vo.BoardVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;

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
	
	@RequestMapping(value="/setting/noticeList.ajax")
	public String noticeList(@ModelAttribute("boardVO") BoardVO boardVO1, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 리스트 가져오기");
		int max_content = 2;
		int max_page = 5;
		int total_page = 0;
		int start_num = 0;
		int end_num = 0;
		int cnt = boardService.selectListCnt("NOTICE");
		int page_num = Integer.parseInt(request.getParameter("page_num").toString());
		System.out.println("KJWKJWKJW value chekc - " + page_num);
//		int page_num = 1;
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
				notice.put("board_idx", boardVO.getBoardIdx());
				notice.put("board_title", boardVO.getBoardTitle());
				notice.put("user_id", boardVO.getUserId());
				notice.put("user_nm", boardVO.getUserName());
				notice.put("updt_date", boardVO.getUpdtDate());
				notice.put("view_cnt", boardVO.getViewCnt());
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
	
	@RequestMapping(value="/boardInsert.do")
	public String boardInsert(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("게시판 글쓰기 화면");
	
		return "setting/board_insert";
	}
	
	@RequestMapping(value="/setting/insertBoard.ajax")
	public String insertBoard(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("게시판 추가");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());
		
		String alarm_yn = request.getParameter("board_alarm").toString();
		boardVO.setBoardTitle(request.getParameter("board_title").toString());
		boardVO.setBoardContent(request.getParameter("board_content").toString());
		//boardVO.setContent(request.getParameter("board_content").toString());//운영용
		boardVO.setInsurCd("N00");//개발용
		boardVO.setUserId(loginVO.getLoginId().toString());
		boardVO.setUserName(request.getParameter("board_usernm").toString());
		boardVO.setInsrtId(loginVO.getLoginId().toString());
		boardVO.setUpdtId(loginVO.getLoginId().toString());
		boardVO.setViewCnt(0);
		boardVO.setIsDel("N");
		if(alarm_yn.equals("on")){
			boardVO.setAlarmYn("Y");
		}
		else{
			boardVO.setAlarmYn(alarm_yn);
		}
		System.out.println("KJWKJW value check - " + boardVO.getAlarmYn());
		//boardVO.setBoardtype(request.getParameter("board_type").toString());//운영용
		boardVO.setBoardType("NOTICE");//개발용
		boardService.insertBoard(boardVO);
		
		//return null;
		HashMap<String, Object> boardInfo = new HashMap<String, Object>();
		boardInfo.put("STATUS", "SUCCES");
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(boardInfo));
		return "jsonView";
	}
	
	@RequestMapping(value="/boardWrite.do")
	public ModelAndView boardWrite(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("게시판 상세내용 화면");
		int board_idx = Integer.parseInt(request.getParameter("board_idx").toString());
		System.out.println("KJWKJW value check - " + board_idx);
		boardVO = boardService.selectBoard(board_idx);
		
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		dataMap.put("board_idx", board_idx);
		dataMap.put("board_title", boardVO.getBoardTitle().toString());
		dataMap.put("board_content", boardVO.getBoardContent().toString());
		dataMap.put("board_usernm", boardVO.getUserName().toString());
		
		return new ModelAndView("setting/board_write", "dataMap", dataMap);
	}
	
	@RequestMapping(value="/setting/updateBoard.ajax")
	public String updateBoard(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("게시판 수정");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());
		
		String alarm_yn = request.getParameter("board_alarm").toString();
		boardVO.setBoardTitle(request.getParameter("board_title").toString());
		boardVO.setBoardContent(request.getParameter("board_content").toString());
		//boardVO.setContent(request.getParameter("board_content").toString());//운영용
		boardVO.setInsurCd("N00");//개발용
		boardVO.setUserId(loginVO.getLoginId().toString());
		boardVO.setUserName(request.getParameter("board_usernm").toString());
		boardVO.setUpdtId(loginVO.getLoginId().toString());
		boardVO.setViewCnt(0);
		boardVO.setIsDel("N");
		if(alarm_yn.equals("on")){
			boardVO.setAlarmYn("Y");
		}
		else{
			boardVO.setAlarmYn(alarm_yn);
		}
		System.out.println("KJWKJW value check - " + boardVO.getAlarmYn());
		//boardVO.setBoardtype(request.getParameter("board_type").toString());//운영용
		boardVO.setBoardType("NOTICE");//개발용
		boardService.updateBoard(boardVO);
		
		//return null;
		HashMap<String, Object> boardInfo = new HashMap<String, Object>();
		boardInfo.put("STATUS", "SUCCES");
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(boardInfo));
		return "jsonView";
	}
	
	@RequestMapping(value="/setting/boardDelete.ajax")
	public String boardDelete(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("게시판 삭제");
		
		return null;
		//return "jsonView";
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
