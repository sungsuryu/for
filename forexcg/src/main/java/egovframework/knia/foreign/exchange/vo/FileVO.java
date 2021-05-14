package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class FileVO extends CommonVO implements Serializable {
	
	private static final long serialVersionUID = -8331070594242347062L;

	// 파일 시퀀스
	private int fileIdx;
	
	private String filePrefix;
	// 첨부파일번호 - 개벌번호
	private String fileId;
	
	private String fileExt;
	
	private String fileSize;
	
	private String filePath;
	
	private String fileNm;
	
	private String phyFileNm;
	// 첨부파일 그룹번호 - eg) 게시글 번호
	private int fileGrpNum = 0;
	// 게시판, 공지사항 등 유형구분코드 사용
	private String fileGrpCd;
	// 파일그룹내 식별값
	private String fileGrpVal;

	private String userId;
	
	public int getFileIdx() {
		return fileIdx;
	}

	public void setFileIdx(int fileIdx) {
		this.fileIdx = fileIdx;
	}

	public String getFilePrefix() {
		return filePrefix;
	}

	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getFileGrpNum() {
		return fileGrpNum;
	}

	public void setFileGrpNum(int fileGrpNum) {
		this.fileGrpNum = fileGrpNum;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getPhyFileNm() {
		return phyFileNm;
	}

	public void setPhyFileNm(String phyFileNm) {
		this.phyFileNm = phyFileNm;
	}

	public String getFileGrpCd() {
		return fileGrpCd;
	}

	public void setFileGrpCd(String fileGrpCd) {
		this.fileGrpCd = fileGrpCd;
	}

	public String getFileGrpVal() {
		return fileGrpVal;
	}

	public void setFileGrpVal(String fileGrpVal) {
		this.fileGrpVal = fileGrpVal;
	}
}
