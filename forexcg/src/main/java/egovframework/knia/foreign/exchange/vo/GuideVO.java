package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class GuideVO implements Serializable {
	private static final long serialVersionUID = -2524203977749965426L;
	
	private int uiNum;
	
	private int guideNum;
	
	private String guideContent;
	
	private String useYn;
	
	private String userNm;

	private String insrtId;
	
	private String insrtDate = null;
	
	private String updtId;
	
	private String updtDate = null;
	
	public int getUiNum() {
		return uiNum;
	}

	public void setUiNum(int uiNum) {
		this.uiNum = uiNum;
	}

	public int getGuideNum() {
		return guideNum;
	}

	public void setGuideNum(int guideNum) {
		this.guideNum = guideNum;
	}

	public String getGuideContent() {
		return guideContent;
	}

	public void setGuideContent(String guideContent) {
		this.guideContent = guideContent;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
