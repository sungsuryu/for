package egovframework.knia.foreign.exchange.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.knia.foreign.exchange.dao.mapper.FileServiceMapper;
import egovframework.knia.foreign.exchange.service.FileService;
import egovframework.knia.foreign.exchange.vo.FileVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;

@Service("fileService")
public class FileServiceImpl implements FileService {

	@Resource(name = "fileServiceMapper")
	FileServiceMapper fileServiceMapper;
	
	public int insertFileInfo(List<?> fileVO, String grpVal) throws Exception {
		int insrtCnt = 0;
		
		LoginVO getLoginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		FileVO filevo;
		
		Iterator<?> iter = fileVO.iterator();
		while (iter.hasNext()) {
			filevo = (FileVO)iter.next();
			
			fileServiceMapper.selectFileId(filevo);

			filevo.setFileId(filevo.getFileGrpCd() +"-"+ String.valueOf(filevo.getFileIdx()));
			filevo.setFileGrpVal(grpVal);
			
			if (getLoginVO != null) {
				filevo.setUserId(getLoginVO.getLoginId());
			} else {
				filevo.setUserId(grpVal);
			}
			
			fileServiceMapper.insertFileInfo(filevo);
			insrtCnt ++;
		}
		
		return insrtCnt;
	}
	
	public void deleteFile(FileVO fileVO) throws Exception {
		
		fileServiceMapper.deleteFile(fileVO);
	}
}
