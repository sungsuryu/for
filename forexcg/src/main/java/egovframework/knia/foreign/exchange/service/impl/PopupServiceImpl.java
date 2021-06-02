package egovframework.knia.foreign.exchange.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.knia.foreign.exchange.dao.mapper.FileServiceMapper;
import egovframework.knia.foreign.exchange.dao.mapper.PopupMapper;
import egovframework.knia.foreign.exchange.service.PopupService;
import egovframework.knia.foreign.exchange.vo.FileVO;
import egovframework.knia.foreign.exchange.vo.PopupVO;

@Service("popupService")
public class PopupServiceImpl implements PopupService{
	@Resource(name="popupMapper")
	private PopupMapper popupMapper;
	
	@Resource(name="fileServiceMapper")
	private FileServiceMapper fileServiceMapper;

	@Override
	public PopupVO selectPopupCnt(PopupVO popupVO) throws Exception {
		return popupMapper.selectPopupCnt(popupVO);
	}
	
	@Override
	public List<?> selectPopupList(PopupVO popupVO) throws Exception {
		return popupMapper.selectPopupList(popupVO);
	}
	
	@Override
	public PopupVO selectPopup(PopupVO popupVO) throws Exception {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		PopupVO resultPopupVO = popupMapper.selectPopup(popupVO);
		resultPopupVO.setPopupStartDt(transFormat.format(resultPopupVO.getPopupStart()));
		resultPopupVO.setPopupEndDt(transFormat.format(resultPopupVO.getPopupEnd()));
		return resultPopupVO;
	}
	
	@Override
	public int insertPopup(PopupVO popupVO) throws Exception {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = popupVO.getPopupStartDt();
		String endDate = popupVO.getPopupEndDt();
		
		Date popupStart = transFormat.parse(startDate);
		Date popupEnd = transFormat.parse(endDate);
		
		popupVO.setPopupStart(popupStart);
		popupVO.setPopupEnd(popupEnd);

		popupMapper.insertPopup(popupVO);
		int popupIdx = popupVO.getPopupIdx();
		return popupIdx;
	}
	
	@Override
	public void updatePopup(PopupVO popupVO) throws Exception {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = popupVO.getPopupStartDt();
		String endDate = popupVO.getPopupEndDt();
		
		Date popupStart = transFormat.parse(startDate);
		Date popupEnd = transFormat.parse(endDate);
		
		popupVO.setPopupStart(popupStart);
		popupVO.setPopupEnd(popupEnd);
		
		popupMapper.updatePopup(popupVO);
	}
	

	@Override
	public void deletePopup(PopupVO popupVO) throws Exception {
		popupMapper.deletePopup(popupVO);
	}
	
	@Override
	public List<?> selectFileList(FileVO fileVO) throws Exception {
		return fileServiceMapper.selectFileList(fileVO);
	}
	
	@Override
	public List<?> selectFile(FileVO fileVO) throws Exception {
		return fileServiceMapper.selectFile(fileVO);
	}
	
	@Override
	public void deleteFile(FileVO fileVO) throws Exception {
		fileServiceMapper.deleteFile(fileVO);
	}
}
