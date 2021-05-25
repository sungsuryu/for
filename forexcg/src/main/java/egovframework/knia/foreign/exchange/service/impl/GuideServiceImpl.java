package egovframework.knia.foreign.exchange.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.knia.foreign.exchange.dao.mapper.BoardMapper;
import egovframework.knia.foreign.exchange.dao.mapper.GuideMapper;
import egovframework.knia.foreign.exchange.service.GuideService;
import egovframework.knia.foreign.exchange.vo.GuideVO;

@Service("guideService")
public class GuideServiceImpl implements GuideService {

	@Resource(name="guideMapper")
	private GuideMapper guideMapper;
	
	@Override
	public List<?> selectGuide(GuideVO guideVO) throws Exception {
		return guideMapper.selectGuide(guideVO);
	}

	@Override
	public void insertGuide(GuideVO guideVO) throws Exception {
		guideMapper.insertGuide(guideVO);
		
	}

	@Override
	public void updateGuide(GuideVO guideVO) throws Exception {
		guideMapper.updateGuide(guideVO);
		
	}

	@Override
	public void deleteGuide(GuideVO guideVO) throws Exception {
		guideMapper.deleteGuide(guideVO);
	}

}
