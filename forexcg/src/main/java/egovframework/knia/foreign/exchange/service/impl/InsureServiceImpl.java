package egovframework.knia.foreign.exchange.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.knia.foreign.exchange.dao.mapper.InsureMapper;
import egovframework.knia.foreign.exchange.service.InsureService;
import egovframework.knia.foreign.exchange.vo.InsureVO;

@Service("insureService")
public class InsureServiceImpl implements InsureService {

	private static final Logger logger = LoggerFactory.getLogger(InsureServiceImpl.class);
	
	@Resource(name="insureMapper")
	InsureMapper insureMapper;
	
	@Override
	public List<?> selectInsureList(HashMap<String, Object> hMap) throws Exception {
		return insureMapper.selectInsureList(hMap);
	}
	
	@Override
	public List<?> selectInsureCdList(InsureVO insureVO) throws Exception {
		return insureMapper.selectInsureCdList(insureVO);
	}
}
