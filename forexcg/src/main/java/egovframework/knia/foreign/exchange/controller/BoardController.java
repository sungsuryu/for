package egovframework.knia.foreign.exchange.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
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
import egovframework.knia.foreign.exchange.cmm.ResponseResult;
import egovframework.knia.foreign.exchange.cmm.code.BoardCode;
import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
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
		paginationInfo.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(propertyService.getInt("pageSize"));// 개발용:페이지 리스트에 게시되는 페이지 건수

		boardVO.setBoardType(BoardCode.NOTICE.toString());// 개발용
		boardVO.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할 첫번째 데이터 번호

		int totalCnt = boardService.selectBoardCnt(boardVO);// 개발용
		boardVO.setTotalCnt(totalCnt);
		paginationInfo.setTotalRecordCount(totalCnt);
		
		List<?> boardList = boardService.selectBoardList(boardVO);
		
		int pageCnt = (totalCnt - ((boardVO.getPage()-1) * propertyService.getInt("pageUnit"))) + 1;
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("pageCnt", pageCnt);
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
		paginationInfo.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(propertyService.getInt("pageSize"));// 개발용:페이지 리스트에 게시되는 페이지 건수

		boardVO.setBoardType(BoardCode.PDS.toString());// 개발용
		boardVO.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할 첫번째 데이터 번호

		int totalCnt = boardService.selectBoardCnt(boardVO);// 개발용
		boardVO.setTotalCnt(totalCnt);
		List<?> boardList = boardService.selectBoardList(boardVO);
		
		int pageCnt = (totalCnt - ((boardVO.getPage()-1) * propertyService.getInt("pageUnit"))) + 1;
		paginationInfo.setTotalRecordCount(totalCnt);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("pageCnt", pageCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "setting/board/pds";
	}

	@RequestMapping(value = "/setting/board/pdsWrite.do")
	public String settingBoardPdsWrite(HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("자료실 작성 화면");
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
		
		if (boardVO.getPage() == 0) {
			boardVO.setPage(1);
		}
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(boardVO.getPage());// 개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(propertyService.getInt("pageSize"));// 개발용:페이지 리스트에 게시되는 페이지 건수

		boardVO.setBoardType(BoardCode.FAQ.toString());// 개발용
		boardVO.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할 첫번째 데이터 번호

		int totalCnt = boardService.selectFaqCnt(boardVO);// 개발용
		boardVO.setTotalCnt(totalCnt);
		List<?> faqList = boardService.selectFaqList(boardVO);
		paginationInfo.setTotalRecordCount(totalCnt);
		
		int pageCnt = (totalCnt - ((boardVO.getPage()-1) * propertyService.getInt("pageUnit"))) + 1;
		
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("faqList", faqList);
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("pageCnt", pageCnt);
		
		return "setting/board/faq";
	}
	
	@RequestMapping(value = "/setting/board/faqUpdateAction.do")
	public String settingBoardFaqUpdate(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request,
			ModelMap model) throws Exception {
		logger.debug("FAQ 최종사용여부 업데이트");
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		FileVO fileVO = new FileVO();
		if(boardVO.getUseYn().equals("Y")){
			fileVO.setIsDel("N");
		}
		else{
			fileVO.setIsDel("Y");
		}
		fileVO.setUserId(getLoginVO.getLoginId().toString());
		fileVO.setFileGrpCd(BoardCode.FAQ.toString());
		fileVO.setFileGrpNum(boardVO.getFaqIdx());
		boardService.updateFaqUseYn(boardVO);
		boardService.updateFaqFile(fileVO);
		
		return "redirect:/setting/board/faq.do";
	}
	
	@RequestMapping(value = "/setting/board/faqInsertAction.do")
	public String settingBoardFaqInsert(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("FAQ 추가");
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		int faqIdx = 0;
		
		boardVO.setUserName(getLoginVO.getUserNm().toString());
		boardVO.setInsrtId(getLoginVO.getLoginId().toString());
		boardVO.setUpdtId(getLoginVO.getLoginId().toString());
		boardVO.setUseYn("N");
		boardVO.setBoardType(BoardCode.FAQ.toString());// 개발용
		faqIdx = boardService.insertFaq(boardVO);
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		List<FileVO> result = null;
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, BoardCode.FAQ.toString(), 0, faqIdx, "");

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
		paginationInfo.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(propertyService.getInt("pageSize"));// 개발용:페이지 리스트에 게시되는 페이지 건수

		boardVO.setBoardType(BoardCode.NOTICE.toString());// 개발용
		boardVO.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할

		int totalCnt = boardService.selectBoardCnt(boardVO);// 개발용
		boardVO.setTotalCnt(totalCnt);
		paginationInfo.setTotalRecordCount(totalCnt);
		
		List<?> boardList = boardService.selectBoardList(boardVO);
		
		int pageCnt = (totalCnt - ((boardVO.getPage()-1) * propertyService.getInt("pageUnit"))) + 1;
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("pageCnt", pageCnt);
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
		paginationInfo.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(propertyService.getInt("pageSize"));// 개발용:페이지 리스트에 게시되는 페이지 건수

		boardVO.setBoardType(BoardCode.PDS.toString());// 개발용
		boardVO.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한번에 조회할 데이터 수
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할

		int totalCnt = boardService.selectBoardCnt(boardVO);// 개발용
		boardVO.setTotalCnt(totalCnt);
		paginationInfo.setTotalRecordCount(totalCnt);
		List<?> boardList = boardService.selectBoardList(boardVO);
		
		int pageCnt = (totalCnt - ((boardVO.getPage()-1) * propertyService.getInt("pageUnit"))) + 1;
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("pageCnt", pageCnt);
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

	@RequestMapping(value = "/board/downloadFile.do")
	public void downloadFile(@ModelAttribute("fileVO") FileVO fileVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("첨부파일 다은로드");
		FileVO resultfileVO = new FileVO();
		List<?> fileList = boardService.selectFile(fileVO);
		resultfileVO = (FileVO) fileList.get(0);

		File uFile = new File(resultfileVO.getFilePath(), resultfileVO.getFileNm());
		long fSize = uFile.length();

		if (fSize > 0) {
			String mimetype = "application/x-msdownload";

			response.setContentType(mimetype);
			setDisposition(resultfileVO.getPhyFileNm(), request, response);
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			
			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());

				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
				EgovBasicLogger.ignore("IO Exception", ex);
			} finally {
				EgovResourceCloseHelper.close(in, out);
			}

		} else {
			response.setContentType("application/x-msdownload");

			PrintWriter printwriter = response.getWriter();

			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + resultfileVO.getPhyFileNm() + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");

			printwriter.flush();
			printwriter.close();
		}
	}
	
	/*@RequestMapping(value = "/board/downloadZipFile.do")
	public void downloadZipFile(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("첨부파일 묶음 다은로드");
			
		ZipOutputStream zout = null;
		String zipName = propertyService.getString("downloadZipName") + boardVO.getBoardType() + ".zip";
		FileVO fileVO = new FileVO();
		fileVO.setFileGrpNum(boardVO.getBoardIdx());
		fileVO.setFileGrpCd(boardVO.getBoardType());

		List<?> fileList = boardService.selectFileList(fileVO);
		
		List<FileVO> iniFileLst = new ArrayList<FileVO>();
		
		int fileSize = fileList.size();
		for (int i=0; i<fileSize; i++) {
			FileVO itm = (FileVO)fileList.get(i);
			String getName = itm.getPhyFileNm();
			int k = 1;
			boolean firstCheck = false;
			if (iniFileLst.size() > 0) {
				for (int m=0; m<iniFileLst.size(); m++) {
					FileVO sItm = iniFileLst.get(m);
					if (getName.equals(sItm.getPhyFileNm())) {
						if(firstCheck == false){
							itm.setPhyFileNm(k+"-"+itm.getPhyFileNm());
							k++;
							getName = itm.getPhyFileNm();
							firstCheck = true;
						}
						else{
							String subText = itm.getPhyFileNm().substring(2);
							itm.setPhyFileNm(k+"-"+subText);
							k++;
							getName = itm.getPhyFileNm();
						}
					}
				}
			}
			iniFileLst.add(itm);
		}
		
		if(fileList.size() > 0){
			//String mimetype = "application/x-msdownload";
			String mimetype = "text/html";
			
			BufferedInputStream bin = null;
			BufferedOutputStream out = null;
			response.setContentType(mimetype);
			setDisposition(zipName, request, response);
			try{
				zout = new ZipOutputStream(new FileOutputStream(zipName));
				byte[] buffer = new byte[1024];
				FileInputStream in = null;
				for(int i=0; i<fileList.size(); i++){
					FileVO tempVO = new FileVO();
					tempVO = (FileVO) fileList.get(i);
					
					ZipEntry zipEntry = new ZipEntry(tempVO.getPhyFileNm());
				    zout.putNextEntry(zipEntry);
					
					in = new FileInputStream(tempVO.getFilePath() + tempVO.getFileNm());
			        int length;

			        while((length = in.read(buffer)) > 0){
			            zout.write(buffer, 0, length);
			        }
					zout.closeEntry();
					in.close();
				}
				zout.close();
				
				bin = new BufferedInputStream(new FileInputStream(zipName));
				out = new BufferedOutputStream(response.getOutputStream());
				
				FileCopyUtils.copy(bin, out);
				
				out.flush();
			}
			catch (IOException ex) {
				EgovBasicLogger.ignore("IO Exception", ex);
			}finally {
				zout = null;
				EgovResourceCloseHelper.close(bin, out);
			}
		}else{
			response.setContentType("application/x-msdownload");

			PrintWriter printwriter = response.getWriter();

			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + zipName + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}*/
	
	@RequestMapping(value = "/board/downloadZipFile.do")
	public void downloadZipFile(@ModelAttribute("boardVO") BoardVO boardVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("첨부파일 묶음 다은로드");
			
		ZipOutputStream zout = null;
		String zipName = propertyService.getString("downloadZipName") + boardVO.getBoardType() + ".zip";
		FileVO fileVO = new FileVO();
		fileVO.setFileGrpNum(boardVO.getBoardIdx());
		fileVO.setFileGrpCd(boardVO.getBoardType());

		List<?> fileList = boardService.selectFileList(fileVO);
		
		List<FileVO> iniFileLst = new ArrayList<FileVO>();
		
		int fileSize = fileList.size();
		for (int i=0; i<fileSize; i++) {
			FileVO itm = (FileVO)fileList.get(i);
			String getName = itm.getPhyFileNm();
			int k = 1;
			boolean firstCheck = false;
			if (iniFileLst.size() > 0) {
				for (int m=0; m<iniFileLst.size(); m++) {
					FileVO sItm = iniFileLst.get(m);
					if (getName.equals(sItm.getPhyFileNm())) {
						if(firstCheck == false){
							itm.setPhyFileNm(k+"-"+itm.getPhyFileNm());
							k++;
							getName = itm.getPhyFileNm();
							firstCheck = true;
						}
						else{
							String subText = itm.getPhyFileNm().substring(2);
							itm.setPhyFileNm(k+"-"+subText);
							k++;
							getName = itm.getPhyFileNm();
						}
					}
				}
			}
			iniFileLst.add(itm);
		}
		
		if(fileList.size() > 0){
			//String mimetype = "application/x-msdownload";
			String mimetype = "text/html";
			
			BufferedInputStream bin = null;
			BufferedOutputStream out = null;
			response.setContentType(mimetype);
			setDisposition(zipName, request, response);
			try{
				zout = new ZipOutputStream(new FileOutputStream(zipName));
				byte[] buffer = new byte[1024];
				FileInputStream in = null;
				for(int i=0; i<fileList.size(); i++){
					FileVO tempVO = new FileVO();
					tempVO = (FileVO) fileList.get(i);
					
					ZipEntry zipEntry = new ZipEntry(tempVO.getPhyFileNm());
				    zout.putNextEntry(zipEntry);
					
					in = new FileInputStream(tempVO.getFilePath() + tempVO.getFileNm());
			        int length;

			        while((length = in.read(buffer)) > 0){
			            zout.write(buffer, 0, length);
			        }
					zout.closeEntry();
					in.close();
				}
				zout.close();
				
//				bin = new BufferedInputStream(new FileInputStream(zipName));
//				out = new BufferedOutputStream(response.getOutputStream());
				
				//bin = new BufferedInputStream(new FileInputStream(zipName));
				byte fileByte[] = FileUtils.readFileToByteArray(new File(zipName));
				response.getOutputStream().write(fileByte);
				//FileCopyUtils.copy(bin, out);
				response.getOutputStream().flush();
				response.getOutputStream().close();
				//out.flush();
			}
			catch (IOException ex) {
				EgovBasicLogger.ignore("IO Exception", ex);
			}finally {
				zout = null;
				EgovResourceCloseHelper.close(bin, out);
			}
		}
	}

	@RequestMapping(value = "/board/downloadFaqFile.do")
	public String downloadFaqFile(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("FAQ첨부파일 다은로드");
		FileVO fileVO = new FileVO();
		List<?> fileList = boardService.selectFaqFile();
		fileVO = (FileVO) fileList.get(0);

		File uFile = new File(fileVO.getFilePath(), fileVO.getFileNm());
		long fSize = uFile.length();

		if (fSize > 0) {
			String mimetype = "application/x-msdownload";

			response.setContentType(mimetype);
			setDisposition(fileVO.getPhyFileNm(), request, response);
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			
			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());

				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
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
		return null;
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
