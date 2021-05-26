package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class GuideVO implements Serializable {
	private static final long serialVersionUID = -2524203977749965426L;
	
	private String uiId;
	
	private String guideContent;
	
	private String userNm;

	private String insrtId;
	
	private String insrtDate = null;
	
	private String updtId;
	
	private String updtDate = null;
	
	public String getUiId() {
		return uiId;
	}

	public void setUiId(String uiId) {
		this.uiId = uiId;
	}
	
	public String getGuideContent() {
		return guideContent;
	}

	public void setGuideContent(String guideContent) {
		this.guideContent = guideContent;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
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
}
