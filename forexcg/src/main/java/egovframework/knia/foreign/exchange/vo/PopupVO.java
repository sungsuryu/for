package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;
import java.util.Date;

public class PopupVO implements Serializable{
	private static final long serialVersionUID = -7913238605797099585L;
	
	private int listNum;
	
	private int totalCnt;

	private int popupIdx;
	
	private String popupTitle;
	
	private String popupContent;
	
	private Date popupStart = null;
	
	private Date popupEnd = null;
	
	private String popupStartDt;
	
	private String popupEndDt;
	
	private String userNm;
	
	private String isDel;
	
	private String insrtId;
	
	private Date insrtDate = null;
	
	private String updtId;
	
	private Date updtDate = null;
	
	private int page = 0;
	
	private int firstIndex;
	
	private int recordCountPerPage;
	
	private int fileCnt;
	
	private String searchName = "";
	
	private String fileId = "";
	
	public int getListNum() {
		return listNum;
	}

	public void setListNum(int listNum) {
		this.listNum = listNum;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	
	public int getPopupIdx() {
		return popupIdx;
	}

	public void setPopupIdx(int popupIdx) {
		this.popupIdx = popupIdx;
	}

	public String getPopupTitle() {
		return popupTitle;
	}

	public void setPopupTitle(String popupTitle) {
		this.popupTitle = popupTitle;
	}

	public String getPopupContent() {
		return popupContent;
	}

	public void setPopupContent(String popupContent) {
		this.popupContent = popupContent;
	}

	public Date getPopupStart() {
		return popupStart;
	}

	public void setPopupStart(Date popupStart) {
		this.popupStart = popupStart;
	}

	public Date getPopupEnd() {
		return popupEnd;
	}

	public void setPopupEnd(Date popupEnd) {
		this.popupEnd = popupEnd;
	}
	
	public String getPopupStartDt() {
		return popupStartDt;
	}

	public void setPopupStartDt(String popupStartDt) {
		this.popupStartDt = popupStartDt;
	}

	public String getPopupEndDt() {
		return popupEndDt;
	}

	public void setPopupEndDt(String popupEndDt) {
		this.popupEndDt = popupEndDt;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getInsrtId() {
		return insrtId;
	}

	public void setInsrtId(String insrtId) {
		this.insrtId = insrtId;
	}

	public Date getInsrtDate() {
		return insrtDate;
	}

	public void setInsrtDate(Date insrtDate) {
		this.insrtDate = insrtDate;
	}

	public String getUpdtId() {
		return updtId;
	}

	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}

	public Date getUpdtDate() {
		return updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getFileCnt() {
		return fileCnt;
	}

	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
}
