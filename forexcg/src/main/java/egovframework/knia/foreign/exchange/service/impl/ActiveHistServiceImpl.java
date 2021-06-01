package egovframework.knia.foreign.exchange.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.knia.foreign.exchange.dao.mapper.ActiveHistMapper;
import egovframework.knia.foreign.exchange.service.ActiveHistService;
import egovframework.knia.foreign.exchange.vo.ActiveHistVO;

@Service("activeHistService")
public class ActiveHistServiceImpl implements ActiveHistService {

	@Resource(name="activeHistMapper")
	private ActiveHistMapper activeHistMapper;

	@Override
	public void insertActiveHist(ActiveHistVO activeHistVO) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public List<?> selectActiveHistFindByUserId(ActiveHistVO activeHistVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteActiveHist(ActiveHistVO activeHistVO) throws Exception {
		activeHistMapper.deleteActiveHist(activeHistVO);
	}

	@Override
	public void updateActiveHist(ActiveHistVO activeHistVO) throws Exception {
		activeHistMapper.updateActiveHist(activeHistVO);
	}
}
