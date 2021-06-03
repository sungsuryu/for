package egovframework.knia.foreign.exchange.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
import egovframework.knia.foreign.exchange.cmm.code.PopupCode;
import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
import egovframework.knia.foreign.exchange.service.FileService;
import egovframework.knia.foreign.exchange.service.PopupService;
import egovframework.knia.foreign.exchange.vo.FileVO;
import egovframework.knia.foreign.exchange.vo.GuideVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.PopupVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class PopupController {
	private static final Logger logger = LoggerFactory.getLogger(PopupController.class);
	
	@Resource(name = "popupService")
	private PopupService popupService;
	
	@Resource(name = "fileService")
	private FileService fileService;
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

    @Resource(name="propertiesService")
    protected EgovPropertyService propertyService;
    
    @RequestMapping(value = "/setting/popup.do")
	public String settingPopup(@ModelAttribute("popupVO") PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception {
		logger.debug("팝업 관리 화면");
		Map<String, ?> pageInfo = RequestContextUtils.getInputFlashMap(request);
		
		if (popupVO.getPage() == 0) {
			if(null != pageInfo){
				int pageNo = (int) pageInfo.get("page");
				String searchName = (String) pageInfo.get("searchName");
				String searchType = (String) pageInfo.get("searchType");
				popupVO.setPage(pageNo);
				popupVO.setSearchName(searchName);
				popupVO.setSearchType(searchType);
			}else{
				popupVO.setPage(1);
			}
		}
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(popupVO.getPage());// 개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(propertyService.getInt("pageSize"));// 개발용:페이지 리스트에 게시되는 페이지 건수
		
		popupVO.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한번에 조회할 데이터 수
		popupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할 첫번째 데이터 번호
		
		PopupVO cntPopupVO = popupService.selectPopupCnt(popupVO);
		
		List<?> popupList = popupService.selectPopupList(popupVO);
		
		List<?> resulPopupList = new ArrayList<>();
		int pageCnt = (cntPopupVO.getTotalCnt() - ((popupVO.getPage()-1) * propertyService.getInt("pageUnit"))) + 1;
		
		paginationInfo.setTotalRecordCount(cntPopupVO.getTotalCnt());
		
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("pageCnt", pageCnt);
		model.addAttribute("popupVO", popupVO);
		model.addAttribute("popupList", popupList);
		model.addAttribute("totalCnt", cntPopupVO.getTotalCnt());
		
		return "setting/popup";
	}
    
    @RequestMapping(value = "/setting/popupWriteAction.do")
	public String settingPopupWriteInsert(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("popupVO") PopupVO popupVO, HttpServletRequest request) throws Exception {
		logger.debug("팝업 추가");
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		int popupIdx = 0;

		popupVO.setUserNm(getLoginVO.getUserNm().toString());
		popupVO.setInsrtId(getLoginVO.getLoginId().toString());
		popupVO.setUpdtId(getLoginVO.getLoginId().toString());
		popupVO.setIsDel("N");
		popupIdx = popupService.insertPopup(popupVO);
		List<FileVO> result = null;

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, PopupCode.POPUP.toString(), 0, popupIdx, "");

			int insertFileCnt = fileService.insertFileInfo(result, "");
		}
		return "redirect:/setting/popup.do";
	}
    
    @RequestMapping(value = "/setting/popupEditView.ajax")
	public String settingPopupView(@ModelAttribute("popupVO") PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception {
    	logger.debug("팝업 수정화면");
    	PopupVO resultPopupVO = popupService.selectPopup(popupVO);
    	
		FileVO fileVO = new FileVO();

		fileVO.setFileGrpNum(popupVO.getPopupIdx());
		fileVO.setFileGrpCd(PopupCode.POPUP.toString());

		List<?> fileList = popupService.selectFileList(fileVO);
		
		HashMap<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("popupVO", resultPopupVO);
		if(fileList.size() > 0){
			rMap.put("isFile", "Y");
			rMap.put("fileList", fileList.get(0));
		}
		else{
			rMap.put("isFile", "N");
			rMap.put("fileList", 0);
		}
		
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(rMap));
		return "jsonView";
	}
    
    @RequestMapping(value = "/setting/popupEditAction.do")
	public String settingPopupEditUpdate(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("popupVO") PopupVO popupVO, HttpServletRequest request, RedirectAttributes attr) throws Exception {
		logger.debug("팝업 수정");
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		popupVO.setUserNm(getLoginVO.getUserNm().toString());
		popupVO.setUpdtId(getLoginVO.getLoginId().toString());
		popupService.updatePopup(popupVO);
		
		if(popupVO.getFileId() != null || !"".equals(popupVO.getFileId())){
			FileVO fileVO = new FileVO();
			
			fileVO.setFileGrpNum(popupVO.getPopupIdx());
			fileVO.setFileGrpCd(PopupCode.POPUP.toString());
			fileVO.setFileId(popupVO.getFileId());
			fileVO.setUserId(getLoginVO.getLoginId().toString());
			popupService.deleteFile(fileVO);
		}
		
		List<FileVO> result = null;

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, PopupCode.POPUP.toString(), 0, popupVO.getPopupIdx(), "");

			int insertFileCnt = fileService.insertFileInfo(result, "");
		}
		attr.addFlashAttribute("page", popupVO.getPage());
		attr.addFlashAttribute("searchName", popupVO.getSearchName());
		attr.addFlashAttribute("searchType", popupVO.getSearchType());
		return "redirect:/setting/popup.do";
	}
    
    @RequestMapping(value = "/setting/popupDeleteAction.do")
   	public String settingPopupEditDelete(final MultipartHttpServletRequest multiRequest,
   			@ModelAttribute("popupVO") PopupVO popupVO, HttpServletRequest request, RedirectAttributes attr) throws Exception {
   		logger.debug("팝업 삭제");
   		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

   		popupService.deletePopup(popupVO);
   		
   		if(popupVO.getFileId() != null || !"".equals(popupVO.getFileId())){
   			FileVO fileVO = new FileVO();
   			
   			fileVO.setFileGrpNum(popupVO.getPopupIdx());
   			fileVO.setFileGrpCd(PopupCode.POPUP.toString());
   			fileVO.setFileId(popupVO.getFileId());
   			fileVO.setUserId(getLoginVO.getLoginId().toString());
   			popupService.deleteFile(fileVO);
   		}
   		attr.addFlashAttribute("page", popupVO.getPage());
		attr.addFlashAttribute("searchName", popupVO.getSearchName());
		attr.addFlashAttribute("searchType", popupVO.getSearchType());
   		return "redirect:/setting/popup.do";
   	}
    
    @RequestMapping(value = "/setting/popupDeletefile.ajax")
	public String settingPopupDeleteFile(@ModelAttribute("popupVO") PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception {
    	logger.debug("팝업 첨부파일삭제");
    	LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		FileVO fileVO = new FileVO();
		
		fileVO.setFileGrpNum(popupVO.getPopupIdx());
		fileVO.setFileGrpCd(PopupCode.POPUP.toString());
		fileVO.setFileId(popupVO.getFileId());
		fileVO.setUserId(getLoginVO.getLoginId().toString());
		popupService.deleteFile(fileVO);
		
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0));
		return "jsonView";
	}
    
    @RequestMapping(value = "/popup/downloadFile.do")
	public String downloadFile(@ModelAttribute("popupVO") PopupVO popupVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("첨부파일 다은로드");
		FileVO resultfileVO = new FileVO();
		FileVO fileVO = new FileVO();
		fileVO.setFileId(popupVO.getFileId());
		List<?> fileList = popupService.selectFile(fileVO);
		resultfileVO = (FileVO) fileList.get(0);

		File uFile = new File(resultfileVO.getFilePath(), resultfileVO.getFileNm());
		long fSize = uFile.length();

		if (fSize > 0) {
			String mimetype = "application/x-msdownload";

			// response.setBufferSize(fSize); // OutOfMemeory 발생
			response.setContentType(mimetype);
			// response.setHeader("Content-Disposition", "attachment;
			// filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8")
			// + "\"");
			setDisposition(resultfileVO.getPhyFileNm(), request, response);
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
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + resultfileVO.getPhyFileNm() + "</h2>");
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
