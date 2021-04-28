package egovframework.knia.foreign.exchange.cmm;

import java.util.HashMap;

import egovframework.knia.foreign.exchange.cmm.code.ResponseCode;
import egovframework.com.cmm.util.EgovStringUtil;

public class ResponseResult {

	private String resultStatus;
	
	private String reasonCode;
	
	private String message;
	
	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseResult() {
		;
	}
	
	public ResponseResult(ResponseCode code) {
		this.resultStatus = getStatus(code.getCode());
		this.reasonCode = code.getCode();
		this.message = code.getMessage();
	}
	
	public HashMap<String, Object> toMap() {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("status", this.resultStatus);
		resultMap.put("reasonCode", this.getReasonCode());
		resultMap.put("message", this.getMessage());
		
		return resultMap;
	}
	
	public HashMap<String, Object> toMap(HashMap<String, Object> customValue) {
		HashMap<String, Object> customMap = this.toMap();
		
		customMap.putAll(customValue);
		
		return customMap;
	}
	
	private String getStatus(String code) {	
		return (EgovStringUtil.equals(code, "0") ? "SUCCESS" : "ERROR");
	}
}
