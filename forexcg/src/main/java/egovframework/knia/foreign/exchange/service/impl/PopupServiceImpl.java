package egovframework.knia.foreign.exchange.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.knia.foreign.exchange.dao.mapper.PopupMapper;
import egovframework.knia.foreign.exchange.service.PopupService;
import egovframework.knia.foreign.exchange.vo.PopupVO;

@Service("popupService")
public class PopupServiceImpl implements PopupService{
	@Resource(name="popupMapper")
	private PopupMapper popupMapper;

	@Override
	public PopupVO selectPopupCnt(PopupVO popupVO) throws Exception {
		return popupMapper.selectPopupCnt(popupVO);
	}
	
	@Override
	public List<?> selectPopupList(PopupVO popupVO) throws Exception {
		return popupMapper.selectPopupList(popupVO);
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
}
