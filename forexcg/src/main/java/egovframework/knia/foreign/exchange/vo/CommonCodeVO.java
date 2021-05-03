package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class CommonCodeVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 2801974448710674972L;

	private String cmmCd;
	
	private String cmmCdNm;
	
	private int lvl;
	
	private String prtCmmCd;
	
	private String cdDesc;
	
	private int sortNum;
	
	private String useYn;

	public String getCmmCd() {
		return cmmCd;
	}

	public void setCmmCd(String cmmCd) {
		this.cmmCd = cmmCd;
	}

	public String getCmmCdNm() {
		return cmmCdNm;
	}

	public void setCmmCdNm(String cmmCdNm) {
		this.cmmCdNm = cmmCdNm;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getPrtCmmCd() {
		return prtCmmCd;
	}

	public void setPrtCmmCd(String prtCmmCd) {
		this.prtCmmCd = prtCmmCd;
	}

	public String getCdDesc() {
		return cdDesc;
	}

	public void setCdDesc(String cdDesc) {
		this.cdDesc = cdDesc;
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
	
}
