package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class MenuVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = -3062302107336523108L;

	private String mnuId;
	
	private String mnuNm;
	
	private String prtMnuId;
	
	private String srcMnuId;
	
	private String stMnuId;
	
	private String isInc;
	
	private String mnuType;
	
	private int lvl = 0;
	
	private int sortNum;
	
	private String useYn = "Y";
	
	private String url = "N";

	private String firstMnuId;
	
	private String secondMnuId;
	
	private String thirdMnuId;
	
	public String getStMnuId() {
		return stMnuId;
	}

	public void setStMnuId(String stMnuId) {
		this.stMnuId = stMnuId;
	}

	public String getSrcMnuId() {
		return srcMnuId;
	}

	public void setSrcMnuId(String srcMnuId) {
		this.srcMnuId = srcMnuId;
	}

	public String getMnuId() {
		return mnuId;
	}

	public void setMnuId(String mnuId) {
		this.mnuId = mnuId;
	}

	public String getMnuNm() {
		return mnuNm;
	}

	public void setMnuNm(String mnuNm) {
		this.mnuNm = mnuNm;
	}

	public String getPrtMnuId() {
		return prtMnuId;
	}

	public void setPrtMnuId(String prtMnuId) {
		this.prtMnuId = prtMnuId;
	}

	public String getMnuType() {
		return mnuType;
	}

	public void setMnuType(String mnuType) {
		this.mnuType = mnuType;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIsInc() {
		return isInc;
	}

	public void setIsInc(String isInc) {
		this.isInc = isInc;
	}

	public String getFirstMnuId() {
		return firstMnuId;
	}

	public void setFirstMnuId(String firstMnuId) {
		this.firstMnuId = firstMnuId;
	}

	public String getSecondMnuId() {
		return secondMnuId;
	}

	public void setSecondMnuId(String secondMnuId) {
		this.secondMnuId = secondMnuId;
	}

	public String getThirdMnuId() {
		return thirdMnuId;
	}

	public void setThirdMnuId(String thirdMnuId) {
		this.thirdMnuId = thirdMnuId;
	}
}
