package egovframework.knia.foreign.exchange.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.util.EgovStringUtil;
import egovframework.knia.foreign.exchange.cmm.ResponseResult;
import egovframework.knia.foreign.exchange.cmm.code.BoardCode;
import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
import egovframework.knia.foreign.exchange.service.BoardService;
import egovframework.knia.foreign.exchange.vo.BoardVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name = "boardService")
	private BoardService boardService;
	
	@RequestMapping(value="/setting/board/notice.do")
	public String settingNotice(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 관리 화면");
		
		int pageIndex;
		if(EgovStringUtil.isEmpty(request.getParameter("pageIndex"))){
			pageIndex = 1;
		}else{
			pageIndex = Integer.parseInt(request.getParameter("pageIndex").toString());
		}
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(pageIndex);//개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);//개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(10);//개발용:페이지 리스트에 게시되는 페이지 건수
		
		boardVO.setBoardType(BoardCode.NOTICE.toString());//개발용
		boardVO.setRecordCountPerPage(10);//개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());//개발용:조회할 첫번째 데이터 번호
		
		int total_cnt = boardService.selectBoardCnt(boardVO);//개발용
		
		List<?> boardList = boardService.selectBoardList(boardVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("total_cnt", total_cnt);
		paginationInfo.setTotalRecordCount(total_cnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "setting/board/notice";
	}
	
	
	
	@RequestMapping(value="/setting/board/noticeWrite.do")
	public String boardInsert(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 작성 화면");
	
		return "setting/board/noticeWrite";
	}
	
	@RequestMapping(value="/setting/board/noticeWriteAction.ajax")
	public String insertBoard(final MultipartHttpServletRequest multiRequest, @ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 추가");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());
		
		//System.out.println("KJW file check - " + multiRequest.getFileMap());
		//System.out.println("KJW file check - " + request.getParameter("board_alarm").toString());
		
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
		boardVO.setBoardType(BoardCode.NOTICE.toString());//개발용
		//boardService.insertBoard(boardVO);
		
		HashMap<String, Object> boardInfo = new HashMap<String, Object>();
		boardInfo.put("STATUS", "SUCCESS");
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(boardInfo));
		//return "jsonView";
		return "redirect:/setting/board/noticeWrite.do";
		//return null;
	}
	
	@RequestMapping(value="/setting/board/noticeEdit.do", method=RequestMethod.GET)
	public String boardWrite(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 수정 화면");
		int board_idx = Integer.parseInt(request.getParameter("board_idx").toString());
		boardVO = boardService.selectBoard(board_idx);
		
		boardService.updateBoardViewCnt(board_idx);
		model.addAttribute("board_idx", board_idx);
		model.addAttribute("board_title", boardVO.getBoardTitle().toString());
		model.addAttribute("board_content", boardVO.getBoardContent().toString());
		model.addAttribute("alarm_yn", boardVO.getAlarmYn().toString());
		model.addAttribute("board_usernm", boardVO.getUserName().toString());
		
		return "setting/board/noticeEdit";
	}
	
	@RequestMapping(value="/setting/board/noticeEditAction.ajax")
	public String updateBoard(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 수정");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());
		String alarm_yn = request.getParameter("board_alarm").toString();
		boardVO.setBoardIdx(Integer.parseInt(request.getParameter("board_idx").toString()));
		boardVO.setBoardTitle(request.getParameter("board_title").toString());
		boardVO.setBoardContent(request.getParameter("board_content").toString());
		//boardVO.setContent(request.getParameter("board_content").toString());//운영용
		boardVO.setUserName(request.getParameter("board_usernm").toString());
		boardVO.setUpdtId(loginVO.getLoginId().toString());
		if(alarm_yn.equals("on")){
			boardVO.setAlarmYn("Y");
		}
		else{
			boardVO.setAlarmYn(alarm_yn);
		}
		//boardVO.setBoardtype(request.getParameter("board_type").toString());//운영용
		boardVO.setBoardType(BoardCode.NOTICE.toString());//개발용
		boardService.updateBoard(boardVO);
		
		//return null;
		HashMap<String, Object> boardInfo = new HashMap<String, Object>();
		boardInfo.put("status", "SUCCESS");
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(boardInfo));
		return "jsonView";
	}
	
	@RequestMapping(value="/setting/board/noticeDeleteAction.ajax")
	public String boardDelete(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 삭제");
		int boardIdx = Integer.parseInt(request.getParameter("board_idx").toString());
		
		boardService.deleteBoard(boardIdx);
		
		HashMap<String, Object> boardInfo = new HashMap<String, Object>();
		boardInfo.put("STATUS", "SUCCES");
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(boardInfo));
		return "jsonView";
	}
	
	@RequestMapping(value="/setting/board/pds.do")
	public String pds(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("게시판 관리 화면");
		
		return "setting/board/pds";
	}
	
	@RequestMapping(value="/setting/board/faq.do")
	public String faq(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("FAQ 관리  화면");
		int pageIndex;
		if(EgovStringUtil.isEmpty(request.getParameter("pageIndex"))){
			pageIndex = 1;
		}else{
			pageIndex = Integer.parseInt(request.getParameter("pageIndex").toString());
		}
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(pageIndex);//개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);//개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(10);//개발용:페이지 리스트에 게시되는 페이지 건수
		
		boardVO.setBoardType(BoardCode.NOTICE.toString());//개발용
		boardVO.setRecordCountPerPage(10);//개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());//개발용:조회할 첫번째 데이터 번호
		
		int total_cnt = boardService.selectBoardCnt(boardVO);//개발용
		
		List<?> boardList = boardService.selectBoardList(boardVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("total_cnt", total_cnt);
		paginationInfo.setTotalRecordCount(total_cnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		
		return "setting/board/faq";
	}
	
	@RequestMapping(value="/board/notice.do")
	public String boardNotice(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 화면");
		
		String searchName;
		int pageIndex;
		
		if(EgovStringUtil.isEmpty(request.getParameter("searchName"))){
			searchName = "";
		}else{
			searchName = request.getParameter("searchName").toString();
		}
		
		if(EgovStringUtil.isEmpty(request.getParameter("pageIndex"))){
			pageIndex = 1;
		}else{
			pageIndex = Integer.parseInt(request.getParameter("pageIndex").toString());
		}
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(pageIndex);//개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(2);//개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(2);//개발용:페이지 리스트에 게시되는 페이지 건수
		
		boardVO.setBoardType(BoardCode.NOTICE.toString());//개발용
		boardVO.setRecordCountPerPage(2);//개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());//개발용:조회할 첫번째 데이터 번호
		boardVO.setSearchName(searchName);
		
		int total_cnt = boardService.selectBoardCnt(boardVO);//개발용
		
		List<?> boardList = boardService.selectBoardList(boardVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("total_cnt", total_cnt);
		model.addAttribute("searchName", searchName);
		paginationInfo.setTotalRecordCount(total_cnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "board/notice";
	}
	
	@RequestMapping(value="/board/noticeView.do", method=RequestMethod.GET)
	public String boardNoticeView(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 상세내용 화면");
		int board_idx = Integer.parseInt(request.getParameter("board_idx").toString());
		boardVO = boardService.selectBoard(board_idx);
		
		boardService.updateBoardViewCnt(board_idx);
		model.addAttribute("board_idx", board_idx);
		model.addAttribute("board_title", boardVO.getBoardTitle().toString());
		model.addAttribute("board_content", boardVO.getBoardContent().toString());
		model.addAttribute("board_usernm", boardVO.getUserName().toString());
		
		return "board/noticeView";
	}

	
	@RequestMapping(value="/board/pds.do")
	public String boardPds(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("자료실 화면");
		
		String searchName;
		int pageIndex;
		
		if(EgovStringUtil.isEmpty(request.getParameter("searchName"))){
			searchName = "";
		}else{
			searchName = request.getParameter("searchName").toString();
		}
		if(EgovStringUtil.isEmpty(request.getParameter("pageIndex"))){
			pageIndex = 1;
		}else{
			pageIndex = Integer.parseInt(request.getParameter("pageIndex").toString());
		}
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(pageIndex);//개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);//개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(10);//개발용:페이지 리스트에 게시되는 페이지 건수
		
		boardVO.setBoardType(BoardCode.PDS.toString());//개발용
		boardVO.setRecordCountPerPage(10);//개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());//개발용:조회할 첫번째 데이터 번호
		
		int total_cnt = boardService.selectBoardCnt(boardVO);//개발용
		
		List<?> boardList = boardService.selectBoardList(boardVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("total_cnt", total_cnt);
		paginationInfo.setTotalRecordCount(total_cnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "board/pds";
	}
	
	@RequestMapping(value="/board/pdsView.do", method=RequestMethod.GET)
	public String boardPdsView(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("자료실 상세내용 화면");
		int board_idx = Integer.parseInt(request.getParameter("board_idx").toString());
		boardVO = boardService.selectBoard(board_idx);
		
		boardService.updateBoardViewCnt(board_idx);
		model.addAttribute("board_idx", board_idx);
		model.addAttribute("board_title", boardVO.getBoardTitle().toString());
		model.addAttribute("board_content", boardVO.getBoardContent().toString());
		model.addAttribute("board_usernm", boardVO.getUserName().toString());
		
		return "board/pdsView";
	}
	
	@RequestMapping(value="/board/faq.do", method=RequestMethod.GET)
	public String boardFaq(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("자료실 상세내용 화면");
		return "board/faq";
	}
	
}
