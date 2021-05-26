package egovframework.knia.foreign.exchange.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.util.EgovBasicLogger;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.cmm.util.EgovStringUtil;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.knia.foreign.exchange.cmm.code.BoardCode;
import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.service.BoardService;
import egovframework.knia.foreign.exchange.service.FileService;
import egovframework.knia.foreign.exchange.vo.BoardVO;
import egovframework.knia.foreign.exchange.vo.FileVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
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

    @Resource(name="propertiesService")
    protected EgovPropertyService propertyService;
    
	@RequestMapping(value = "/setting/board/notice.do")
	public String settingBoardNotice(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 관리 화면");
		Map<String, ?> pageInfo = RequestContextUtils.getInputFlashMap(request);
		if (boardVO.getPage() == 0) {
			if(null != pageInfo){
				int pageNo = (int) pageInfo.get("page");
				boardVO.setPage(pageNo);
			}else{
				boardVO.setPage(1);
			}
		}
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(boardVO.getPage());// 개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(propertyService.getInt("pageData"));// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(propertyService.getInt("pageSize"));// 개발용:페이지 리스트에 게시되는 페이지 건수

		boardVO.setBoardType(BoardCode.NOTICE.toString());// 개발용
		boardVO.setRecordCountPerPage(propertyService.getInt("pageData"));// 개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할 첫번째 데이터 번호

		int total_cnt = boardService.selectBoardCnt(boardVO);// 개발용

		List<?> boardList = boardService.selectBoardList(boardVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("total_cnt", total_cnt);
		model.addAttribute("page", boardVO.getPage());
		paginationInfo.setTotalRecordCount(total_cnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "setting/board/notice";
	}

	@RequestMapping(value = "/setting/board/noticeWrite.do")
	public String settingBoardNoticeWrite(HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 작성 화면");
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		model.addAttribute("userName", getLoginVO.getUserNm().toString());
		model.addAttribute("maxFile", propertyService.getInt("maxFile"));
		return "setting/board/noticeWrite";
	}

	@RequestMapping(value = "/setting/board/noticeWriteAction.do")
	public String settingBoardNoticeWriteInsert(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request) throws Exception {
		logger.debug("공지사항 추가");
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		int boardIdx = 0;

		boardVO.setUserName(getLoginVO.getUserNm().toString());
		boardVO.setUserId(getLoginVO.getLoginId().toString());
		boardVO.setInsrtId(getLoginVO.getLoginId().toString());
		boardVO.setUpdtId(getLoginVO.getLoginId().toString());
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

	@RequestMapping(value = "/setting/board/noticeView.do")
	public String settingBoardNoticeView(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 상세내용 화면");
		
		if(boardVO.getBoardIdx() == 0){
			return "redirect:/setting/board/notice.do";
		}
		boardService.updateBoardViewCnt(boardVO.getBoardIdx());
		
		BoardVO resultBoardVO = new BoardVO();
		resultBoardVO = boardService.selectBoard(boardVO.getBoardIdx());
		resultBoardVO.setPage(boardVO.getPage());
		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(boardVO.getBoardIdx());
		fileVO.setFileGrpCd(BoardCode.NOTICE.toString());

		List<?> fileList = boardService.selectFileList(fileVO);

		model.addAttribute("fileList", fileList);
		model.addAttribute("boardVO", resultBoardVO);
		return "setting/board/noticeView";
	}

	@RequestMapping(value = "/setting/board/noticeEdit.do")
	public String settingBoardNoticeEdit(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 수정 화면");
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(boardVO.getBoardIdx() == 0){
			return "redirect:/setting/board/notice.do";
		}
		BoardVO resultboardVO = new BoardVO();
		resultboardVO = boardService.selectBoard(boardVO.getBoardIdx());
		resultboardVO.setPage(boardVO.getPage());
		
		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(boardVO.getBoardIdx());
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
		model.addAttribute("boardVO", resultboardVO);
		model.addAttribute("userName", getLoginVO.getUserNm().toString());
		model.addAttribute("maxFile", propertyService.getInt("maxFile"));
		return "setting/board/noticeEdit";
	}

	@RequestMapping(value = "/setting/board/noticeEditAction.do")
	public String settingBoardNoticeEditUpdate(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, RedirectAttributes attr) throws Exception {
		logger.debug("공지사항 수정");
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String check[] = request.getParameterValues("deleteOriginFileId");
		String isOriginFile = request.getParameter("isOriginFile").toString();

		boardVO.setUserName(getLoginVO.getUserNm().toString());
		boardVO.setUpdtId(getLoginVO.getLoginId().toString());
		if(EgovStringUtil.isEmpty(boardVO.getAlarmYn())){
			boardVO.setAlarmYn("N");
		}
		boardVO.setBoardType(BoardCode.NOTICE.toString());// 개발용
		boardService.updateBoard(boardVO);

		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(boardVO.getBoardIdx());
		fileVO.setFileGrpCd(BoardCode.NOTICE.toString());
		
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
		attr.addFlashAttribute("page", boardVO.getPage());
		return "redirect:/setting/board/notice.do";
	}

	@RequestMapping(value = "/setting/board/noticeDeleteAction.do")
	public String settingBoardNoticeEditDelete(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, RedirectAttributes attr) throws Exception {
		logger.debug("공지사항 삭제");
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
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
				fileVO.setUserId(getLoginVO.getLoginId().toString());
				fileVO.setFileGrpCd(BoardCode.NOTICE.toString());
				boardService.deleteFile(fileVO);
			}
		}
		attr.addFlashAttribute("page", boardVO.getPage());
		return "redirect:/setting/board/notice.do";
	}

	@RequestMapping(value = "/setting/board/pds.do")
	public String settingBoardPds(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("자료실 관리 화면");
		Map<String, ?> pageInfo = RequestContextUtils.getInputFlashMap(request);
		if (boardVO.getPage() == 0) {
			if(null != pageInfo){
				int pageNo = (int) pageInfo.get("page");
				boardVO.setPage(pageNo);
			}else{
				boardVO.setPage(1);
			}
		}
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(boardVO.getPage());// 개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(propertyService.getInt("pageData"));// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(propertyService.getInt("pageSize"));// 개발용:페이지 리스트에 게시되는 페이지 건수

		boardVO.setBoardType(BoardCode.PDS.toString());// 개발용
		boardVO.setRecordCountPerPage(propertyService.getInt("pageData"));// 개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할 첫번째 데이터 번호

		int total_cnt = boardService.selectBoardCnt(boardVO);// 개발용

		List<?> boardList = boardService.selectBoardList(boardVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("total_cnt", total_cnt);
		model.addAttribute("page", boardVO.getPage());
		paginationInfo.setTotalRecordCount(total_cnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "setting/board/pds";
	}

	@RequestMapping(value = "/setting/board/pdsWrite.do")
	public String settingBoardPdsWrite(HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("자료실 작성 화면");
		HttpSession session = request.getSession();
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		model.addAttribute("userName", getLoginVO.getUserNm().toString());
		model.addAttribute("maxFile", propertyService.getInt("maxFile"));
		return "setting/board/pdsWrite";
	}
	
	@RequestMapping(value = "/setting/board/pdsWriteAction.do")
	public String settingBoardPdsWriteInsert(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request) throws Exception {
		logger.debug("자료실 추가");
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		int boardIdx = 0;

		boardVO.setUserName(getLoginVO.getUserNm().toString());
		boardVO.setUserId(getLoginVO.getLoginId().toString());
		boardVO.setInsrtId(getLoginVO.getLoginId().toString());
		boardVO.setUpdtId(getLoginVO.getLoginId().toString());
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

	@RequestMapping(value = "/setting/board/pdsView.do")
	public String settingBoardPdsView(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("자료실 상세내용 화면");
		
		if(boardVO.getBoardIdx() == 0){
			return "redirect:/setting/board/pds.do";
		}
		boardService.updateBoardViewCnt(boardVO.getBoardIdx());
		
		BoardVO resultBoardVO = new BoardVO();
		resultBoardVO = boardService.selectBoard(boardVO.getBoardIdx());
		resultBoardVO.setPage(boardVO.getPage());
		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(boardVO.getBoardIdx());
		fileVO.setFileGrpCd(BoardCode.PDS.toString());

		List<?> fileList = boardService.selectFileList(fileVO);

		model.addAttribute("fileList", fileList);
		model.addAttribute("boardVO", resultBoardVO);
		return "setting/board/pdsView";
	}
	
	@RequestMapping(value = "/setting/board/pdsEdit.do")
	public String settingBoardPdsEdit(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("자료실 수정 화면");
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(boardVO.getBoardIdx() == 0){
			return "redirect:/setting/board/pds.do";
		}
		BoardVO resultboardVO = new BoardVO();
		resultboardVO = boardService.selectBoard(boardVO.getBoardIdx());
		resultboardVO.setPage(boardVO.getPage());
		
		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(boardVO.getBoardIdx());
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
		model.addAttribute("boardVO", resultboardVO);
		model.addAttribute("userName", getLoginVO.getUserNm().toString());
		model.addAttribute("maxFile", propertyService.getInt("maxFile"));

		return "setting/board/pdsEdit";
	}
	
	@RequestMapping(value = "/setting/board/pdsEditAction.do")
	public String settingBoardPdsEditUpdate(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, RedirectAttributes attr) throws Exception {
		logger.debug("자료실 수정");
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String check[] = request.getParameterValues("deleteOriginFileId");
		String isOriginFile = request.getParameter("isOriginFile").toString();

		boardVO.setUserName(getLoginVO.getUserNm().toString());
		boardVO.setUpdtId(getLoginVO.getLoginId().toString());
		if(EgovStringUtil.isEmpty(boardVO.getAlarmYn())){
			boardVO.setAlarmYn("N");
		}
		boardVO.setBoardType(BoardCode.PDS.toString());// 개발용
		boardService.updateBoard(boardVO);

		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(boardVO.getBoardIdx());
		fileVO.setFileGrpCd(BoardCode.PDS.toString());
		
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
		attr.addFlashAttribute("page", boardVO.getPage());
		return "redirect:/setting/board/pds.do";
	}

	@RequestMapping(value = "/setting/board/pdsDeleteAction.do")
	public String settingBoardPdsEditDelete(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, RedirectAttributes attr) throws Exception {
		logger.debug("자료실 삭제");
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
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
				fileVO.setUserId(getLoginVO.getLoginId().toString());
				fileVO.setFileGrpCd(BoardCode.PDS.toString());
				boardService.deleteFile(fileVO);
			}
		}
		attr.addFlashAttribute("page", boardVO.getPage());
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
		if (boardVO.getPage() == 0) {
			boardVO.setPage(1);
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPage());// 개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(propertyService.getInt("pageData"));// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(propertyService.getInt("pageSize"));// 개발용:페이지 리스트에 게시되는 페이지 건수

		boardVO.setBoardType(BoardCode.NOTICE.toString());// 개발용
		boardVO.setRecordCountPerPage(propertyService.getInt("pageData"));// 개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할

		int total_cnt = boardService.selectBoardCnt(boardVO);// 개발용

		List<?> boardList = boardService.selectBoardList(boardVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("total_cnt", total_cnt);
		model.addAttribute("searchName", boardVO.getSearchName());
		model.addAttribute("page", boardVO.getPage());
		paginationInfo.setTotalRecordCount(total_cnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "board/notice";
	}

	@RequestMapping(value = "/board/noticeView.do")
	public String boardNoticeView(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("공지사항 상세내용 화면");
		if(boardVO.getBoardIdx() == 0){
			return "redirect:/board/notice.do";
		}
		BoardVO resultboardVO = new BoardVO();
		boardService.updateBoardViewCnt(boardVO.getBoardIdx());
		resultboardVO = boardService.selectBoard(boardVO.getBoardIdx());
		resultboardVO.setPage(boardVO.getPage());
		resultboardVO.setSearchName(boardVO.getSearchName());
		
		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(boardVO.getBoardIdx());

		fileVO.setFileGrpCd(BoardCode.NOTICE.toString());

		List<?> fileList = boardService.selectFileList(fileVO);

		model.addAttribute("fileList", fileList);
		model.addAttribute("boardVO", resultboardVO);

		return "board/noticeView";
	}
	
	@RequestMapping(value = "/board/pds.do")
	public String boardPds(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model)
			throws Exception {
		logger.debug("자료실 화면");

		if (boardVO.getPage() == 0) {
			boardVO.setPage(1);
		}

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPage());// 개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(propertyService.getInt("pageData"));// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(propertyService.getInt("pageSize"));// 개발용:페이지 리스트에 게시되는 페이지 건수

		boardVO.setBoardType(BoardCode.PDS.toString());// 개발용
		boardVO.setRecordCountPerPage(propertyService.getInt("pageData"));// 개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할

		int total_cnt = boardService.selectBoardCnt(boardVO);// 개발용

		List<?> boardList = boardService.selectBoardList(boardVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("total_cnt", total_cnt);
		model.addAttribute("searchName", boardVO.getSearchName());
		model.addAttribute("page", boardVO.getPage());
		paginationInfo.setTotalRecordCount(total_cnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "board/pds";
	}

	@RequestMapping(value = "/board/pdsView.do")
	public String boardPdsView(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model)
			throws Exception {
		if(boardVO.getBoardIdx() == 0){
			return "redirect:/board/notice.do";
		}
		BoardVO resultboardVO = new BoardVO();
		boardService.updateBoardViewCnt(boardVO.getBoardIdx());
		resultboardVO = boardService.selectBoard(boardVO.getBoardIdx());
		resultboardVO.setPage(boardVO.getPage());
		resultboardVO.setSearchName(boardVO.getSearchName());
		
		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(boardVO.getBoardIdx());

		fileVO.setFileGrpCd(BoardCode.PDS.toString());

		List<?> fileList = boardService.selectFileList(fileVO);

		model.addAttribute("fileList", fileList);
		model.addAttribute("boardVO", resultboardVO);

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
