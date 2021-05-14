package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;
import java.util.Date;

public class UserVO implements Serializable {

	private static final long serialVersionUID = -432319567215992186L;

	private String userId;
	
	private String userNm;
	
	private String password;
	
	private String insurCd;
	
	private String dptNm;
	
	private String officeTelNum;
	
	private String acsIp;
	
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
	
	private String timestamp;
	
	private String[] alrmType;

	private String joinAgree = "Y";
	
	public String getJoinAgree() {
		return joinAgree;
	}

	public void setJoinAgree(String joinAgree) {
		this.joinAgree = joinAgree;
	}

	public String[] getAlrmType() {
		return alrmType;
	}

	public void setAlrmType(String[] alrmType) {
		this.alrmType = alrmType;
	}

	public String getDptNm() {
		return dptNm;
	}

	public void setDptNm(String dptNm) {
		this.dptNm = dptNm;
	}

	public String getOfficeTelNum() {
		return officeTelNum;
	}

	public void setOfficeTelNum(String officeTelNum) {
		this.officeTelNum = officeTelNum;
	}

	public String getAcsIp() {
		return acsIp;
	}

	public void setAcsIp(String acsIp) {
		this.acsIp = acsIp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInsurCd() {
		return insurCd;
	}

	public void setInsurCd(String insurCd) {
		this.insurCd = insurCd;
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
