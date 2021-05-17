package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;
import java.util.Date;

public class CellAuthVO implements Serializable {
	
	private static final long serialVersionUID = -7643433873681259333L;

	private String cellNum;
	
	private String authNum;
	
	private String authType;
	// 검증코드
	private String encAuthVal;
	
	private String isExp;
	
	private Date insrtDate;

	public String getCellNum() {
		return cellNum;
	}

	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}

	public String getAuthNum() {
		return authNum;
	}

	public void setAuthNum(String authNum) {
		this.authNum = authNum;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getEncAuthVal() {
		return encAuthVal;
	}

	public void setEncAuthVal(String encAuthVal) {
		this.encAuthVal = encAuthVal;
	}

	public String getIsExp() {
		return isExp;
	}

	public void setIsExp(String isExp) {
		this.isExp = isExp;
	}

	public Date getInsrtDate() {
		return insrtDate;
	}

	public void setInsrtDate(Date insrtDate) {
		this.insrtDate = insrtDate;
	}
}
