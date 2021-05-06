package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class ReportMasterVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 1060739766125930040L;

	private String rptNum;
	
	private String rptGrp;
	
	private String rptCycle;
	
	private String rptCycleNm;
	
	private String rptTitle;
	
	private String tgDt;
	
	private int rptSiz;

	public String getRptNum() {
		return rptNum;
	}

	public void setRptNum(String rptNum) {
		this.rptNum = rptNum;
	}

	public String getRptGrp() {
		return rptGrp;
	}

	public void setRptGrp(String rptGrp) {
		this.rptGrp = rptGrp;
	}

	public String getRptCycle() {
		return rptCycle;
	}

	public void setRptCycle(String rptCycle) {
		this.rptCycle = rptCycle;
	}

	public String getRptTitle() {
		return rptTitle;
	}

	public void setRptTitle(String rptTitle) {
		this.rptTitle = rptTitle;
	}

	public String getTgDt() {
		return tgDt;
	}

	public void setTgDt(String tgDt) {
		this.tgDt = tgDt;
	}

	public int getRptSiz() {
		return rptSiz;
	}

	public void setRptSiz(int rptSiz) {
		this.rptSiz = rptSiz;
	}

	public String getRptCycleNm() {
		return rptCycleNm;
	}

	public void setRptCycleNm(String rptCycleNm) {
		this.rptCycleNm = rptCycleNm;
	}
}
