package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;
import java.util.Date;

public class KaTalkVO implements Serializable {

	private static final long serialVersionUID = 1576028539855249354L;

	private int messageSeqno;
	
	private String serviceSeqno;
	
	private String sendMessage;
	
	private String subject;
	
	private String backupMessage;
	
	private String backupProcessCode;
	
	private String messageType;
	
	private String contentsType;
	
	private String receiveMobileNo;
	
	private String callbackNo;
	
	private String jobType;
	
	private Date sendReserveDate;
	
	private String sendReserveDateStr;
	
	private String templateCode;
	
	private Date registerDate;
	
	private String registerBy;
	
	private String imgAttachFlag;
	
	private String taxLevel1Nm;
	
	private String taxLevel2Nm;

	public int getMessageSeqno() {
		return messageSeqno;
	}

	public void setMessageSeqno(int messageSeqno) {
		this.messageSeqno = messageSeqno;
	}

	public String getServiceSeqno() {
		return serviceSeqno;
	}

	public void setServiceSeqno(String serviceSeqno) {
		this.serviceSeqno = serviceSeqno;
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBackupMessage() {
		return backupMessage;
	}

	public void setBackupMessage(String backupMessage) {
		this.backupMessage = backupMessage;
	}

	public String getBackupProcessCode() {
		return backupProcessCode;
	}

	public void setBackupProcessCode(String backupProcessCode) {
		this.backupProcessCode = backupProcessCode;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getContentsType() {
		return contentsType;
	}

	public void setContentsType(String contentsType) {
		this.contentsType = contentsType;
	}

	public String getReceiveMobileNo() {
		return receiveMobileNo;
	}

	public void setReceiveMobileNo(String receiveMobileNo) {
		this.receiveMobileNo = receiveMobileNo;
	}

	public String getCallbackNo() {
		return callbackNo;
	}

	public void setCallbackNo(String callbackNo) {
		this.callbackNo = callbackNo;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public Date getSendReserveDate() {
		return sendReserveDate;
	}

	public void setSendReserveDate(Date sendReserveDate) {
		this.sendReserveDate = sendReserveDate;
	}

	public String getSendReserveDateStr() {
		return sendReserveDateStr;
	}

	public void setSendReserveDateStr(String sendReserveDateStr) {
		this.sendReserveDateStr = sendReserveDateStr;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getRegisterBy() {
		return registerBy;
	}

	public void setRegisterBy(String registerBy) {
		this.registerBy = registerBy;
	}

	public String getImgAttachFlag() {
		return imgAttachFlag;
	}

	public void setImgAttachFlag(String imgAttachFlag) {
		this.imgAttachFlag = imgAttachFlag;
	}

	public String getTaxLevel1Nm() {
		return taxLevel1Nm;
	}

	public void setTaxLevel1Nm(String taxLevel1Nm) {
		this.taxLevel1Nm = taxLevel1Nm;
	}

	public String getTaxLevel2Nm() {
		return taxLevel2Nm;
	}

	public void setTaxLevel2Nm(String taxLevel2Nm) {
		this.taxLevel2Nm = taxLevel2Nm;
	}
}
