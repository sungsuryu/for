package egovframework.knia.foreign.exchange.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.knia.foreign.exchange.dao.mapper.CommonCodeMapper;
import egovframework.knia.foreign.exchange.service.CommonCodeService;
import egovframework.knia.foreign.exchange.vo.CommonCodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("commonCodeService")
public class CommonCodeServiceImpl extends EgovAbstractServiceImpl implements CommonCodeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonCodeServiceImpl.class);
	
	@Resource(name="commonCodeMapper")
	private CommonCodeMapper commonCodeMapper;

	@Override
	public List<?> selectCodeList(String prtCmmCd) throws Exception {
		return commonCodeMapper.selectCodeList(prtCmmCd);
	}

	@Override
	public List<?> selectGroupCodeList() throws Exception {
		return commonCodeMapper.selectGroupCodeList();
	}
	
	@Override
	public CommonCodeVO selectCode(String cmmCd) throws Exception {
		return commonCodeMapper.selectCode(cmmCd);
	}

	@Override
	public void insertCode(CommonCodeVO commonCodeVO) throws Exception {
		commonCodeMapper.insertCode(commonCodeVO);
	}

	@Override
	public void updateCode(CommonCodeVO commonCodeVO) throws Exception {
		commonCodeMapper.updateCode(commonCodeVO);
	}

	@Override
	public void deleteCode(String cmmCd) throws Exception {
		commonCodeMapper.deleteCode(cmmCd);
	}
}
