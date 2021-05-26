package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;
import java.util.Date;

public class CommonVO implements Serializable {

	private static final long serialVersionUID = -7869275865924832744L;

	private String isDel;
	
	private String insrtId;
	
	private Date insrtDate = null;
	
	private String updtId;
	
	private Date updtDate = null;

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
