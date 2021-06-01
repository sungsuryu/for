package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class ActiveHistVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 2313621047602044762L;

	private int hisNum;
	
	private String userId;
	
	private String mnuId;
	
	private String mnuNm;
	
	private String atvDesc;
	
	private String mnuType;
	
	private String url;
	
	private int lvl;

	public String getMnuNm() {
		return mnuNm;
	}

	public void setMnuNm(String mnuNm) {
		this.mnuNm = mnuNm;
	}

	public String getMnuType() {
		return mnuType;
	}

	public void setMnuType(String mnuType) {
		this.mnuType = mnuType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getHisNum() {
		return hisNum;
	}

	public void setHisNum(int hisNum) {
		this.hisNum = hisNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMnuId() {
		return mnuId;
	}

	public void setMnuId(String mnuId) {
		this.mnuId = mnuId;
	}

	public String getAtvDesc() {
		return atvDesc;
	}

	public void setAtvDesc(String atvDesc) {
		this.atvDesc = atvDesc;
	}
}
