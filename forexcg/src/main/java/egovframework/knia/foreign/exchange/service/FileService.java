package egovframework.knia.foreign.exchange.service;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.FileVO;

public interface FileService {

	/**
	 * 첨부파일 등록
	 * @param fileVO &lt;List&gt;첨부파일 
	 * @param grpVal 그룹 식별값 - 불필요시 Empty전송
	 * @return int 등록된 파일 갯수
	 * @throws Exception
	 */
	public int insertFileInfo(List<?> fileVO, String grpVal) throws Exception;
	
	public void deleteFile(FileVO fileVO) throws Exception;
}
