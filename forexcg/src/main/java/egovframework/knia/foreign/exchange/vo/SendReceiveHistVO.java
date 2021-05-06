package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;
import java.util.Date;

public class SendReceiveHistVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 1065429662767441373L;

	private String insurCd;
	
	private String insurNm;
	
	private String rptNum;
	
	private String rptGrp;
	
	private String rptCycle;
	
	private String rptCycleNm;
	
	private String sndRst;
	
	private String sndRstNm;
	
	private String tgDt;
	
	private String tgDtNm;
	
	private String fileNm;
	
	private String transFlag;
	
	private String reqDate;
	
	private int hisNum;
	
	private String rptTitle;
	
	private int rptSiz;

	public String getInsurCd() {
		return insurCd;
	}

	public void setInsurCd(String insurCd) {
		this.insurCd = insurCd;
	}

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

	public String getSndRst() {
		return sndRst;
	}

	public void setSndRst(String sndRst) {
		this.sndRst = sndRst;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getTransFlag() {
		return transFlag;
	}

	public void setTransFlag(String transFlag) {
		this.transFlag = transFlag;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getInsurNm() {
		return insurNm;
	}

	public void setInsurNm(String insurNm) {
		this.insurNm = insurNm;
	}

	public String getRptCycleNm() {
		return rptCycleNm;
	}

	public void setRptCycleNm(String rptCycleNm) {
		this.rptCycleNm = rptCycleNm;
	}

	public String getSndRstNm() {
		return sndRstNm;
	}

	public void setSndRstNm(String sndRstNm) {
		this.sndRstNm = sndRstNm;
	}

	public String getTgDt() {
		return tgDt;
	}

	public void setTgDt(String tgDt) {
		this.tgDt = tgDt;
	}

	public String getTgDtNm() {
		return tgDtNm;
	}

	public void setTgDtNm(String tgDtNm) {
		this.tgDtNm = tgDtNm;
	}

	public int getHisNum() {
		return hisNum;
	}

	public void setHisNum(int hisNum) {
		this.hisNum = hisNum;
	}

	public String getRptTitle() {
		return rptTitle;
	}

	public void setRptTitle(String rptTitle) {
		this.rptTitle = rptTitle;
	}

	public int getRptSiz() {
		return rptSiz;
	}

	public void setRptSiz(int rptSiz) {
		this.rptSiz = rptSiz;
	}
	
}
