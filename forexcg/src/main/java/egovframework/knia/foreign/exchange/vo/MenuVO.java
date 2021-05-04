package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;
import java.util.Date;

public class MenuVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = -3062302107336523108L;

	private String mnuId;
	
	private String mnuNm;
	
	private String prtMnuId;
	
	private String mnuType;
	
	private int lvl;
	
	private int sortNum;
	
	private String useYn = "Y";
	
	private String isDel = "N";
	
	private String insrtId;
	
	private Date insrtDate = null;
	
	private String updtId;
	
	private Date updtDate = null;

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
}
