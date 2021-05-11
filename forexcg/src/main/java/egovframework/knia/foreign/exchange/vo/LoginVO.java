package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class LoginVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = -2459454960913481924L;

	private String uuId;
	
	private String loginId;
	
	private String password;
	
	private String timestamp;

	private int loginStep = 0;
	
	private String authNum;
	
	private String ip;

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getLoginStep() {
		return loginStep;
	}

	public void setLoginStep(int loginStep) {
		this.loginStep = loginStep;
	}

	public String getAuthNum() {
		return authNum;
	}

	public void setAuthNum(String authNum) {
		this.authNum = authNum;
	}
}
