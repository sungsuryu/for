package egovframework.knia.foreign.exchange.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.util.EgovStringUtil;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.knia.foreign.exchange.cmm.code.PopupCode;
import egovframework.knia.foreign.exchange.service.FileService;
import egovframework.knia.foreign.exchange.service.PopupService;
import egovframework.knia.foreign.exchange.vo.FileVO;
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
		if (popupVO.getPage() == 0) {
			popupVO.setPage(1);
		}
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(popupVO.getPage());// 개발용:현재 페이지 번호
		paginationInfo.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한페이지에 표시할 데이터 갯수
		paginationInfo.setPageSize(propertyService.getInt("pageSize"));// 개발용:페이지 리스트에 게시되는 페이지 건수
		
		popupVO.setRecordCountPerPage(propertyService.getInt("pageUnit"));// 개발용:한번에 조회할 데이터 수
		popupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());// 개발용:조회할 첫번째 데이터 번호
		
		PopupVO resultPopupVO = popupService.selectPopupCnt(popupVO);
		
		List<?> popupList = popupService.selectPopupList(popupVO);
		
		paginationInfo.setTotalRecordCount(resultPopupVO.getTotalCnt());
		
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("page", popupVO.getPage());
		model.addAttribute("popupList", popupList);
		model.addAttribute("total_cnt", resultPopupVO.getTotalCnt());
		
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
}
