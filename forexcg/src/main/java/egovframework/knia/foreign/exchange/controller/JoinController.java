package egovframework.knia.foreign.exchange.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
//import egovframework.com.cmm.service.EgovFileMngService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.knia.foreign.exchange.cmm.ResponseResult;
import egovframework.knia.foreign.exchange.cmm.code.CommonConst;
import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
import egovframework.knia.foreign.exchange.service.InsureService;
import egovframework.knia.foreign.exchange.service.JoinService;
import egovframework.knia.foreign.exchange.service.FileService;
import egovframework.knia.foreign.exchange.vo.CellAuthVO;
import egovframework.knia.foreign.exchange.vo.FileVO;
import egovframework.knia.foreign.exchange.vo.InsureVO;
import egovframework.knia.foreign.exchange.vo.UserVO;

@Controller
public class JoinController {

	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	@Resource(name = "joinService")
	private JoinService joinService;
	
	@Resource(name = "fileService")
	private FileService fileService;
	
	@Resource(name="insureService")
	private InsureService insureService;
	
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	@Autowired
    private DefaultBeanValidator beanValidator;
	
	private final String joinType = "APPLI";
	/**
	 * 회원가입 화면
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/join.do")
	public String join(HttpServletRequest request, ModelMap model) throws Exception {
		
		HashMap<String, Object> hMap = new HashMap<String, Object>();
		hMap.put("useYn", "Y");
		
		List<InsureVO> insureList = (List<InsureVO>) insureService.selectInsureList(hMap);
		model.addAttribute("insureList", insureList);
		
		logger.debug("보험사 목록 조회 - insureList:{}개", insureList.size());
		
		return "usr/join";
	}
	
	/**
	 * 사용자아이디 중복 체크
	 * @param userVO 중복체크 대상 아이디
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkUserId.ajax")
	public String countUserId(@ModelAttribute("userVO") UserVO userVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		if (userVO.getUserId() == null || userVO.getUserId().equals("")) {
			logger.debug("중복확인을 위한 'userId' 값이 없음.");
			throw new Exception();
		}
		
		HashMap<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("countUserId", joinService.countUser(userVO.getUserId()));
		
		model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(rMap));
		
		return "jsonView";
	}
	
	/**
	 * 회원가입
	 * @param userVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/joinAction.do")
	public String joinAction(final MultipartHttpServletRequest multiRequest, @ModelAttribute("userVO") UserVO userVO, BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception {
		
		beanValidator.validate(userVO, bindingResult);
        if (bindingResult.hasErrors()) {
        	throw new Exception();
        }
        
		List<FileVO> result = null;
	    
	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, joinType, 0, 0, "");
			
			int insertFileCnt = fileService.insertFileInfo(result, userVO.getUserId());
	    }
	    
		// 신규사용자 등록
		joinService.UserJoin(userVO);
		
		return "redirect:/index.do";
	}
	
	@RequestMapping(value="reqCellAuth.ajax")
	public String requestCellAuth(@ModelAttribute("CellAuthVO") CellAuthVO cellAuthVO, BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception {
		
		beanValidator.validate(cellAuthVO, bindingResult);
        if (bindingResult.hasErrors()) {
        	throw new Exception();
        }
		
        cellAuthVO.setAuthType(joinType);
        CellAuthVO authInfo = joinService.generateAuthNum(cellAuthVO);
        
        logger.debug("gen authNum:{}", authInfo.getAuthNum());
        
        model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap());
        
		return "jsonView";
	}
	
	@RequestMapping(value="resCellAuth.ajax")
	public String responseCellAuth(@ModelAttribute("CellAuthVO") CellAuthVO cellAuthVO, BindingResult bindingResult, HttpServletRequest request, ModelMap model) throws Exception {
		
		beanValidator.validate(cellAuthVO, bindingResult);
        if (bindingResult.hasErrors()) {
        	throw new Exception();
        }
		
        String reqAuthNum = cellAuthVO.getAuthNum();
        if (reqAuthNum == null || reqAuthNum.equals("")) {
        	logger.error("인증번호 누락");
        	throw new Exception("인증번호 없음.");
        }
        
        cellAuthVO.setAuthType(joinType);
        CellAuthVO getAuthInfo = joinService.compareAuthNum(cellAuthVO);
        
        HashMap<String, Object> authResult = new HashMap<String, Object>();
        if (getAuthInfo == null) {
        	authResult.put("authResult", CommonConst.AJX_FAIL);
        } else {
        	authResult.put("authResult", CommonConst.AJX_SUCCESS);
        	authResult.put("authKey", getAuthInfo.getEncAuthVal());
        }
        
        model.addAttribute("result", new ResponseResult(ResponseCode.RESULT_0).toMap(authResult));
        
		return "jsonView";
	}
}
