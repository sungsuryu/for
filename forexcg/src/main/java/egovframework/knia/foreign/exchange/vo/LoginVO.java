package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class LoginVO implements Serializable {

	private static final long serialVersionUID = -2459454960913481924L;

	private String loginId;
	
	private String password;

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
	
	
}
