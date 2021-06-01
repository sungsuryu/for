package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.FileVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("fileServiceMapper")
public interface FileServiceMapper {

	void selectFileId(FileVO fileVO) throws Exception;
	
	List<?> selectFileList(FileVO fileVO) throws Exception;
	
	List<?> selectFile(FileVO fileVO) throws Exception;
	
	List<?> selectFaqFile() throws Exception;
	
	void insertFileInfo(FileVO fileVO) throws Exception;
	
	void deleteFile(FileVO fileVO) throws Exception;
	
	void updateFaqFile(FileVO fileVO) throws Exception;
}
