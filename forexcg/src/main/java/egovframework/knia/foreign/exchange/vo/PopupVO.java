package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class PopupVO implements Serializable{
	private static final long serialVersionUID = -7913238605797099585L;
	
	private int listNum;
	
	private int totalCnt;

	private int popupIdx;
	
	private String popupTitle;
	
	private String popupContent;
	
	private String popupStart;
	
	private String popupEnd;
	
	private String userNm;
	
	private String isDel;
	
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

	public String getPopupStart() {
		return popupStart;
	}

	public void setPopupStart(String popupStart) {
		this.popupStart = popupStart;
	}

	public String getPopupEnd() {
		return popupEnd;
	}

	public void setPopupEnd(String popupEnd) {
		this.popupEnd = popupEnd;
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

	public String getInsrtDate() {
		return insrtDate;
	}

	public void setInsrtDate(String insrtDate) {
		this.insrtDate = insrtDate;
	}

	public String getUpdtId() {
		return updtId;
	}

	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}

	public String getUpdtDate() {
		return updtDate;
	}

	public void setUpdtDate(String updtDate) {
		this.updtDate = updtDate;
	}

	private String insrtId;
	
	private String insrtDate = null;
	
	private String updtId;
	
	private String updtDate = null;

}
