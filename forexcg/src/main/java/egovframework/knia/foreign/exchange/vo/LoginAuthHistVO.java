package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class LoginAuthHistVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 3250262388224502053L;

	private String userId;
	
	private String loginId;
	
	private String approveTimestamp;
	
	private String authNum;
	
	private String isExp;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApproveTimestamp() {
		return approveTimestamp;
	}

	public void setApproveTimestamp(String approveTimestamp) {
		this.approveTimestamp = approveTimestamp;
	}

	public String getAuthNum() {
		return authNum;
	}

	public void setAuthNum(String authNum) {
		this.authNum = authNum;
	}

	public String getIsExp() {
		return isExp;
	}

	public void setIsExp(String isExp) {
		this.isExp = isExp;
	}
}
