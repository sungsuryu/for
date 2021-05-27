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
	public GuideVO selectGuide(GuideVO guideVO) throws Exception {
		return guideMapper.selectGuide(guideVO);
	}

	@Override
	public void mergeInsertGuide(GuideVO guideVO) throws Exception {
		guideMapper.mergeInsertGuide(guideVO);
		
	}
}
