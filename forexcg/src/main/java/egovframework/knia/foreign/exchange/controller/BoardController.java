package egovframework.knia.foreign.exchange.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.util.EgovBasicLogger;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.cmm.util.EgovStringUtil;
import egovframework.knia.foreign.exchange.cmm.code.BoardCode;
import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.service.BoardService;
import egovframework.knia.foreign.exchange.service.FileService;
import egovframework.knia.foreign.exchange.vo.BoardVO;
import egovframework.knia.foreign.exchange.vo.FileVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Resource(name = "boardService")
	private BoardService boardService;

	@Resource(name = "fileService")
	private FileService fileService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@RequestMapping(value = "/setting/board/notice.do")
	public String settingBoardNotice(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
		logger.debug("공지사항 관리 화면");

		if (boardVO.getPageNo() == 0) {
			boardVO.setPageNo(1);
		}
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageNo());// 개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(10);// 개발용:페이지 리스트에 게시되는 페이지 건수

		boardVO.setBoardType(BoardCode.NOTICE.toString());// 개발용
		boardVO.setRecordCountPerPage(10);// 개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할 첫번째 데이터 번호

		int total_cnt = boardService.selectBoardCnt(boardVO);// 개발용

		List<?> boardList = boardService.selectBoardList(boardVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("total_cnt", total_cnt);
		paginationInfo.setTotalRecordCount(total_cnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "setting/board/notice";
	}

	@RequestMapping(value = "/setting/board/noticeWrite.do")
	public String settingBoardNoticeWrite(HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 작성 화면");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());
		
		model.addAttribute("userName", loginVO.getUserNm().toString());
		return "setting/board/noticeWrite";
	}

	@RequestMapping(value = "/setting/board/noticeWriteAction.do")
	public String settingBoardNoticeWriteInsert(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request) throws Exception {
		logger.debug("공지사항 추가");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());
		int boardIdx = 0;

		boardVO.setUserName(loginVO.getUserNm().toString());
		boardVO.setUserId(loginVO.getLoginId().toString());
		boardVO.setInsrtId(loginVO.getLoginId().toString());
		boardVO.setUpdtId(loginVO.getLoginId().toString());
		boardVO.setViewCnt(0);
		boardVO.setIsDel("N");
		if(EgovStringUtil.isEmpty(boardVO.getAlarmYn())){
			boardVO.setAlarmYn("N");
		}
		boardVO.setBoardType(BoardCode.NOTICE.toString());// 개발용
		boardIdx = boardService.insertBoard(boardVO);
		List<FileVO> result = null;

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, BoardCode.NOTICE.toString(), 0, boardIdx, "");

			int insertFileCnt = fileService.insertFileInfo(result, "");
		}
		return "redirect:/setting/board/notice.do";
	}

	@RequestMapping(value = "/setting/board/noticeView.do", method = RequestMethod.GET)
	public String settingBoardNoticeView(HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 상세내용 화면");
		int boardIdx = Integer.parseInt(request.getParameter("board_idx").toString());
		boardService.updateBoardViewCnt(boardIdx);
		
		BoardVO boardVO = new BoardVO();
		boardVO = boardService.selectBoard(boardIdx);
		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(boardIdx);
		fileVO.setFileGrpCd(BoardCode.NOTICE.toString());

		List<?> fileList = boardService.selectFileList(fileVO);

		model.addAttribute("fileList", fileList);
		model.addAttribute("boardVO", boardVO);
		return "setting/board/noticeView";
	}

	@RequestMapping(value = "/setting/board/noticeEdit.do", method = RequestMethod.GET)
	public String settingBoardNoticeEdit(HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 수정 화면");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());
		
		
		int board_idx = Integer.parseInt(request.getParameter("board_idx").toString());
		
		BoardVO boardVO = new BoardVO();
		boardVO = boardService.selectBoard(board_idx);

		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(board_idx);
		fileVO.setFileGrpCd(BoardCode.NOTICE.toString());

		List<?> fileList = boardService.selectFileList(fileVO);

		if (fileList.size() <= 0) {
			model.addAttribute("isOriginFile", "N");
			model.addAttribute("fileCnt", 0);
		} else {
			model.addAttribute("isOriginFile", "Y");
			model.addAttribute("fileCnt", fileList.size());
		}
		model.addAttribute("fileList", fileList);
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("userName", loginVO.getUserNm().toString());
		return "setting/board/noticeEdit";
	}

	@RequestMapping(value = "/setting/board/noticeEditAction.do")
	public String settingBoardNoticeEditUpdate(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request) throws Exception {
		logger.debug("공지사항 수정");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());

		String check[] = request.getParameterValues("deleteOriginFileId");
		String isOriginFile = request.getParameter("isOriginFile").toString();

		boardVO.setUserName(loginVO.getUserNm().toString());
		boardVO.setUpdtId(loginVO.getLoginId().toString());
		if(EgovStringUtil.isEmpty(boardVO.getAlarmYn())){
			boardVO.setAlarmYn("N");
		}
		boardVO.setBoardType(BoardCode.NOTICE.toString());// 개발용
		boardService.updateBoard(boardVO);

		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(boardVO.getBoardIdx());
		fileVO.setFileGrpCd(BoardCode.NOTICE.toString());
		List<?> fileList = boardService.selectFileList(fileVO);
		
		if (check != null) {
			for (int i = 0; i < check.length; i++) {
				fileVO.setFileId(check[i]);
				fileVO.setFileGrpNum(boardVO.getBoardIdx());
				fileVO.setFileGrpCd(BoardCode.NOTICE.toString());
				boardService.deleteFile(fileVO);
			}
		}

		List<FileVO> result = null;

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, BoardCode.NOTICE.toString(), 0, boardVO.getBoardIdx(), "");

			int insertFileCnt = fileService.insertFileInfo(result, "");
		}
		return "redirect:/setting/board/notice.do";
	}

	@RequestMapping(value = "/setting/board/noticeDeleteAction.do")
	public String settingBoardNoticeEditDelete(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request) throws Exception {
		logger.debug("공지사항 삭제");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());
		
		String isOriginFile = request.getParameter("isOriginFile").toString();

		boardService.deleteBoard(boardVO.getBoardIdx());

		if ("Y".equals(isOriginFile)) {
			FileVO fileVO = new FileVO();

			fileVO.setFileGrpNum(boardVO.getBoardIdx());
			fileVO.setFileGrpCd(BoardCode.NOTICE.toString());
			List<?> fileList = boardService.selectFileList(fileVO);

			for (int i = 0; i < fileList.size(); i++) {
				FileVO checkfileVO = new FileVO();
				checkfileVO = (FileVO) fileList.get(i);
				fileVO.setFileId(checkfileVO.getFileId());
				fileVO.setFileGrpNum(boardVO.getBoardIdx());
				fileVO.setUserId(loginVO.getLoginId().toString());
				fileVO.setFileGrpCd(BoardCode.NOTICE.toString());
				boardService.deleteFile(fileVO);
			}
		}
		return "redirect:/setting/board/notice.do";
	}

	@RequestMapping(value = "/setting/board/pds.do")
	public String settingBoardPds(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
		logger.debug("자료실 관리 화면");
		if (boardVO.getPageNo() == 0) {
			boardVO.setPageNo(1);
		}
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageNo());// 개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(10);// 개발용:페이지 리스트에 게시되는 페이지 건수

		boardVO.setBoardType(BoardCode.PDS.toString());// 개발용
		boardVO.setRecordCountPerPage(10);// 개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할 첫번째 데이터 번호

		int total_cnt = boardService.selectBoardCnt(boardVO);// 개발용

		List<?> boardList = boardService.selectBoardList(boardVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("total_cnt", total_cnt);
		paginationInfo.setTotalRecordCount(total_cnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "setting/board/pds";
	}

	@RequestMapping(value = "/setting/board/pdsWrite.do")
	public String settingBoardPdsWrite(HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("자료실 작성 화면");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());
		
		model.addAttribute("userName", loginVO.getUserNm().toString());
		return "setting/board/pdsWrite";
	}
	
	@RequestMapping(value = "/setting/board/pdsWriteAction.do")
	public String settingBoardPdsWriteInsert(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request) throws Exception {
		logger.debug("자료실 추가");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());
		int boardIdx = 0;

		boardVO.setUserName(loginVO.getUserNm().toString());
		boardVO.setUserId(loginVO.getLoginId().toString());
		boardVO.setInsrtId(loginVO.getLoginId().toString());
		boardVO.setUpdtId(loginVO.getLoginId().toString());
		boardVO.setViewCnt(0);
		boardVO.setIsDel("N");
		if(EgovStringUtil.isEmpty(boardVO.getAlarmYn())){
			boardVO.setAlarmYn("N");
		}
		boardVO.setBoardType(BoardCode.PDS.toString());// 개발용
		boardIdx = boardService.insertBoard(boardVO);
		List<FileVO> result = null;

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, BoardCode.PDS.toString(), 0, boardIdx, "");

			int insertFileCnt = fileService.insertFileInfo(result, "");
		}
		return "redirect:/setting/board/pds.do";
	}
	
	@RequestMapping(value = "/setting/board/pdsView.do", method = RequestMethod.GET)
	public String settingBoardPdsView(HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("자료실 상세내용 화면");
		int boardIdx = Integer.parseInt(request.getParameter("board_idx").toString());
		boardService.updateBoardViewCnt(boardIdx);
		
		BoardVO boardVO = new BoardVO();
		boardVO = boardService.selectBoard(boardIdx);
		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(boardIdx);
		fileVO.setFileGrpCd(BoardCode.PDS.toString());

		List<?> fileList = boardService.selectFileList(fileVO);

		model.addAttribute("fileList", fileList);
		model.addAttribute("boardVO", boardVO);
		return "setting/board/pdsView";
	}
	
	@RequestMapping(value = "/setting/board/pdsEdit.do", method = RequestMethod.GET)
	public String settingBoardPdsEdit(HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("자료실 수정 화면");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());
		
		
		int board_idx = Integer.parseInt(request.getParameter("board_idx").toString());
		
		BoardVO boardVO = new BoardVO();
		boardVO = boardService.selectBoard(board_idx);

		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(board_idx);
		fileVO.setFileGrpCd(BoardCode.PDS.toString());

		List<?> fileList = boardService.selectFileList(fileVO);

		if (fileList.size() <= 0) {
			model.addAttribute("isOriginFile", "N");
			model.addAttribute("fileCnt", 0);
		} else {
			model.addAttribute("isOriginFile", "Y");
			model.addAttribute("fileCnt", fileList.size());
		}
		model.addAttribute("fileList", fileList);
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("userName", loginVO.getUserNm().toString());

		return "setting/board/pdsEdit";
	}
	
	@RequestMapping(value = "/setting/board/pdsEditAction.do")
	public String settingBoardPdsEditUpdate(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request) throws Exception {
		logger.debug("자료실 수정");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());

		String check[] = request.getParameterValues("deleteOriginFileId");
		String isOriginFile = request.getParameter("isOriginFile").toString();

		boardVO.setUserName(loginVO.getUserNm().toString());
		boardVO.setUpdtId(loginVO.getLoginId().toString());
		if(EgovStringUtil.isEmpty(boardVO.getAlarmYn())){
			boardVO.setAlarmYn("N");
		}
		boardVO.setBoardType(BoardCode.PDS.toString());// 개발용
		boardService.updateBoard(boardVO);

		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(boardVO.getBoardIdx());
		fileVO.setFileGrpCd(BoardCode.PDS.toString());
		List<?> fileList = boardService.selectFileList(fileVO);
		
		if (check != null) {
			for (int i = 0; i < check.length; i++) {
				fileVO.setFileId(check[i]);
				fileVO.setFileGrpNum(boardVO.getBoardIdx());
				fileVO.setFileGrpCd(BoardCode.PDS.toString());
				boardService.deleteFile(fileVO);
			}
		}

		List<FileVO> result = null;

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, BoardCode.PDS.toString(), 0, boardVO.getBoardIdx(), "");

			int insertFileCnt = fileService.insertFileInfo(result, "");
		}
		return "redirect:/setting/board/pds.do";
	}

	@RequestMapping(value = "/setting/board/pdsDeleteAction.do")
	public String settingBoardPdsEditDelete(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request) throws Exception {
		logger.debug("자료실 삭제");
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute(ConstCode.loginVO.toString());
		String isOriginFile = request.getParameter("isOriginFile").toString();

		boardService.deleteBoard(boardVO.getBoardIdx());

		if ("Y".equals(isOriginFile)) {
			FileVO fileVO = new FileVO();

			fileVO.setFileGrpNum(boardVO.getBoardIdx());
			fileVO.setFileGrpCd(BoardCode.PDS.toString());
			List<?> fileList = boardService.selectFileList(fileVO);

			for (int i = 0; i < fileList.size(); i++) {
				FileVO checkfileVO = new FileVO();
				checkfileVO = (FileVO) fileList.get(i);
				fileVO.setFileId(checkfileVO.getFileId());
				fileVO.setFileGrpNum(boardVO.getBoardIdx());
				fileVO.setUserId(loginVO.getLoginId().toString());
				fileVO.setFileGrpCd(BoardCode.PDS.toString());
				boardService.deleteFile(fileVO);
			}
		}
		return "redirect:/setting/board/pds.do";
	}
	
	@RequestMapping(value = "/setting/board/faq.do")
	public String settingBoardFaq(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request,
			ModelMap model) throws Exception {
		logger.debug("FAQ 관리  화면");
//		int pageIndex;
//		if (EgovStringUtil.isEmpty(request.getParameter("pageIndex"))) {
//			pageIndex = 1;
//		} else {
//			pageIndex = Integer.parseInt(request.getParameter("pageIndex").toString());
//		}
//		PaginationInfo paginationInfo = new PaginationInfo();
//
//		paginationInfo.setCurrentPageNo(pageIndex);// 개발용:현재 페이지 번호
//		paginationInfo.setRecordCountPerPage(10);// 개발용:한페이지에 표시할 데이터 갯수
//		paginationInfo.setPageSize(10);// 개발용:페이지 리스트에 게시되는 페이지 건수
//
//		boardVO.setBoardType(BoardCode.NOTICE.toString());// 개발용
//		boardVO.setRecordCountPerPage(10);// 개발용:한번에 조회할 데이터 수
//		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할
//																	// 첫번째 데이터
//																	// 번호
//
//		int total_cnt = boardService.selectBoardCnt(boardVO);// 개발용
//
//		List<?> boardList = boardService.selectBoardList(boardVO);
//		model.addAttribute("boardList", boardList);
//		model.addAttribute("total_cnt", total_cnt);
//		paginationInfo.setTotalRecordCount(total_cnt);
//		model.addAttribute("paginationInfo", paginationInfo);

		FileVO fileVO = new FileVO();
		
		fileVO.setFileGrpCd(BoardCode.FAQ.toString());
		List<?> fileList = boardService.selectFileList(fileVO);
		
		model.addAttribute("boardList", fileList);
		
		return "setting/board/faq";
	}
	
	@RequestMapping(value = "/setting/board/faqInsertAction.do")
	public String settingBoardFaqInsert(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("자료실 추가");
		HttpSession session = request.getSession();
		
		List<FileVO> result = null;

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, BoardCode.FAQ.toString(), 0, 0, "");

			int insertFileCnt = fileService.insertFileInfo(result, "");
		}
		return "redirect:/setting/board/faq.do";
	}

	@RequestMapping(value = "/board/notice.do")
	public String boardNotice(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model)
			throws Exception {
		logger.debug("공지사항 화면");

		String searchName;
		if (boardVO.getPageNo() == 0) {
			boardVO.setPageNo(1);
		}

		if (EgovStringUtil.isEmpty(request.getParameter("searchName"))) {
			searchName = "";
		} else {
			searchName = request.getParameter("searchName").toString();
		}

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageNo());// 개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(10);// 개발용:페이지 리스트에 게시되는 페이지 건수

		boardVO.setBoardType(BoardCode.NOTICE.toString());// 개발용
		boardVO.setRecordCountPerPage(10);// 개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할
																	// 번호
		boardVO.setSearchName(searchName);

		int total_cnt = boardService.selectBoardCnt(boardVO);// 개발용

		List<?> boardList = boardService.selectBoardList(boardVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("total_cnt", total_cnt);
		model.addAttribute("searchName", searchName);
		paginationInfo.setTotalRecordCount(total_cnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "board/notice";
	}

	@RequestMapping(value = "/board/noticeView.do", method = RequestMethod.GET)
	public String boardNoticeView(HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 상세내용 화면");
		int board_idx = Integer.parseInt(request.getParameter("board_idx").toString());
		boardService.updateBoardViewCnt(board_idx);
		BoardVO boardVO = new BoardVO();
		boardVO = boardService.selectBoard(board_idx);

		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(board_idx);
		fileVO.setFileGrpCd(BoardCode.NOTICE.toString());

		List<?> fileList = boardService.selectFileList(fileVO);

		model.addAttribute("fileList", fileList);
		model.addAttribute("boardVO", boardVO);

		return "board/noticeView";
	}

	@RequestMapping(value = "/board/pds.do")
	public String boardPds(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model)
			throws Exception {
		logger.debug("자료실 화면");

		String searchName;
		if (boardVO.getPageNo() == 0) {
			boardVO.setPageNo(1);
		}

		if (EgovStringUtil.isEmpty(request.getParameter("searchName"))) {
			searchName = "";
		} else {
			searchName = request.getParameter("searchName").toString();
		}
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageNo());// 개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(10);// 개발용:페이지 리스트에 게시되는 페이지 건수

		boardVO.setBoardType(BoardCode.PDS.toString());// 개발용
		boardVO.setRecordCountPerPage(10);// 개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할

		boardVO.setSearchName(searchName);
		
		int total_cnt = boardService.selectBoardCnt(boardVO);// 개발용

		List<?> boardList = boardService.selectBoardList(boardVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("total_cnt", total_cnt);
		model.addAttribute("searchName", searchName);
		paginationInfo.setTotalRecordCount(total_cnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "board/pds";
	}

	@RequestMapping(value = "/board/pdsView.do", method = RequestMethod.GET)
	public String boardPdsView(HttpServletRequest request, ModelMap model)
			throws Exception {
		logger.debug("자료실 상세내용 화면");
		int board_idx = Integer.parseInt(request.getParameter("board_idx").toString());
		boardService.updateBoardViewCnt(board_idx);
		BoardVO boardVO = new BoardVO();
		boardVO = boardService.selectBoard(board_idx);
		

		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(board_idx);
		fileVO.setFileGrpCd(BoardCode.PDS.toString());

		List<?> fileList = boardService.selectFileList(fileVO);

		model.addAttribute("fileList", fileList);
		model.addAttribute("boardVO", boardVO);

		return "board/pdsView";
	}

	@RequestMapping(value = "/board/faq.do")
	public String boardFaq()
			throws Exception {
		logger.debug("FAQ 화면");
		return "board/faq";
	}

	@RequestMapping(value = "/board/downloadFile.do", method = RequestMethod.GET)
	public void downloadFile(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("첨부파일 다은로드");
		String fileId = request.getParameter("fileId").toString();
		FileVO fileVO = new FileVO();
		fileVO.setFileId(fileId);

		List<?> fileList = boardService.selectFile(fileVO);
		fileVO = (FileVO) fileList.get(0);

		File uFile = new File(fileVO.getFilePath(), fileVO.getFileNm());
		long fSize = uFile.length();

		if (fSize > 0) {
			String mimetype = "application/x-msdownload";

			// response.setBufferSize(fSize); // OutOfMemeory 발생
			response.setContentType(mimetype);
			// response.setHeader("Content-Disposition", "attachment;
			// filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8")
			// + "\"");
			setDisposition(fileVO.getPhyFileNm(), request, response);
			// response.setContentLength(fSize);

			/*
			 * FileCopyUtils.copy(in, response.getOutputStream()); in.close();
			 * response.getOutputStream().flush();
			 * response.getOutputStream().close();
			 */
			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());

				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
				// 다음 Exception 무시 처리
				// Connection reset by peer: socket write error
				EgovBasicLogger.ignore("IO Exception", ex);
			} finally {
				EgovResourceCloseHelper.close(in, out);
			}

		} else {
			response.setContentType("application/x-msdownload");

			PrintWriter printwriter = response.getWriter();

			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fileVO.getPhyFileNm() + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");

			printwriter.flush();
			printwriter.close();
		}
	}

	@RequestMapping(value = "/board/downloadFaqFile.do")
	public void downloadFaqFile(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("FAQ첨부파일 다은로드");
		FileVO fileVO = new FileVO();
		List<?> fileList = boardService.selectFaqFile();
		fileVO = (FileVO) fileList.get(0);

		File uFile = new File(fileVO.getFilePath(), fileVO.getFileNm());
		long fSize = uFile.length();

		if (fSize > 0) {
			String mimetype = "application/x-msdownload";

			// response.setBufferSize(fSize); // OutOfMemeory 발생
			response.setContentType(mimetype);
			// response.setHeader("Content-Disposition", "attachment;
			// filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8")
			// + "\"");
			setDisposition(fileVO.getPhyFileNm(), request, response);
			// response.setContentLength(fSize);

			/*
			 * FileCopyUtils.copy(in, response.getOutputStream()); in.close();
			 * response.getOutputStream().flush();
			 * response.getOutputStream().close();
			 */
			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());

				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
				// 다음 Exception 무시 처리
				// Connection reset by peer: socket write error
				EgovBasicLogger.ignore("IO Exception", ex);
			} finally {
				EgovResourceCloseHelper.close(in, out);
			}

		} else {
			response.setContentType("application/x-msdownload");

			PrintWriter printwriter = response.getWriter();

			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fileVO.getPhyFileNm() + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");

			printwriter.flush();
			printwriter.close();
		}
	}

	/**
	 * Disposition 지정하기.
	 *
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

	/**
	 * 브라우저 구분 얻기.
	 *
	 * @param request
	 * @return
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}
}
