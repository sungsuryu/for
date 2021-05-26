package egovframework.knia.foreign.exchange.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.knia.foreign.exchange.cmm.code.CommonConst;
import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.dao.mapper.CommonCodeMapper;
import egovframework.knia.foreign.exchange.service.CommonCodeService;
import egovframework.knia.foreign.exchange.vo.CommonCodeVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.UserVO;
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
	public void deleteCode(CommonCodeVO commonCodeVO) throws Exception {
		if (commonCodeVO.getUri().equals("addSubCode")) {
			commonCodeVO.setLvl(2);
		} else {
			CommonCodeVO parentCodeVO = new CommonCodeVO();
			
			parentCodeVO.setUri("addGrpCode");
			parentCodeVO.setPrtCmmCd(commonCodeVO.getCmmCd());
			parentCodeVO.setLvl(2);
			parentCodeVO.setUpdtId(commonCodeVO.getUpdtId());
			
			commonCodeMapper.deleteCode(parentCodeVO);
			
			commonCodeVO.setLvl(1);
		}
		
		commonCodeVO.setUri("addSubCode");
		
		commonCodeMapper.deleteCode(commonCodeVO);
	}
	
	@Override
	public void addGroupCode(CommonCodeVO commonCodeVO) throws Exception {
		
		commonCodeVO.setLvl(1);
		commonCodeVO.setPrtCmmCd(CommonConst.KNIA);
		
		int sortNum = commonCodeMapper.selectMxSortNum(commonCodeVO);
		commonCodeVO.setSortNum(sortNum);
		
		commonCodeMapper.insertCode(commonCodeVO);
	}
	
	@Override
	public void addSubCode(CommonCodeVO commonCodeVO) throws Exception {
		
		commonCodeVO.setLvl(2);
		
		int sortNum = commonCodeMapper.selectMxSortNum(commonCodeVO);
		commonCodeVO.setSortNum(sortNum);
		
		commonCodeMapper.insertCode(commonCodeVO);
	}
}
