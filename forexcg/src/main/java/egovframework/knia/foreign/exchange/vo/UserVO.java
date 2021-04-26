package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;
import java.util.Date;

public class UserVO implements Serializable {

	private static final long serialVersionUID = -432319567215992186L;

	private String userId;
	
	private String userName;
	
	private String password;
	
	private String cmpyCd;
	
	private String emlAddr;
	
	private String isRcvEml;
	
	private String cellNum;
	
	private String isRcvCell;
	
	private String isApprove;
	
	private String isDel;
	
	private String useYn;
	
	private Date insrtDate = null;
	
	private String insrtId;
	
	private Date updtDate = null;
	
	private String updtId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCmpyCd() {
		return cmpyCd;
	}

	public void setCmpyCd(String cmpyCd) {
		this.cmpyCd = cmpyCd;
	}

	public String getEmlAddr() {
		return emlAddr;
	}

	public void setEmlAddr(String emlAddr) {
		this.emlAddr = emlAddr;
	}

	public String getIsRcvEml() {
		return isRcvEml;
	}

	public void setIsRcvEml(String isRcvEml) {
		this.isRcvEml = isRcvEml;
	}

	public String getCellNum() {
		return cellNum;
	}

	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}

	public String getIsRcvCell() {
		return isRcvCell;
	}

	public void setIsRcvCell(String isRcvCell) {
		this.isRcvCell = isRcvCell;
	}

	public String getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public Date getInsrtDate() {
		return insrtDate;
	}

	public void setInsrtDate(Date insrtDate) {
		this.insrtDate = insrtDate;
	}

	public String getInsrtId() {
		return insrtId;
	}

	public void setInsrtId(String insrtId) {
		this.insrtId = insrtId;
	}

	public Date getUpdtDate() {
		return updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public String getUpdtId() {
		return updtId;
	}

	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}
}
