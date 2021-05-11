package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class UserAlarmVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 8842567961079965742L;

	private String userId;
	
	private String alrmType;
	
	private String isRcvAlrm;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAlrmType() {
		return alrmType;
	}

	public void setAlrmType(String alrmType) {
		this.alrmType = alrmType;
	}

	public String getIsRcvAlrm() {
		return isRcvAlrm;
	}

	public void setIsRcvAlrm(String isRcvAlrm) {
		this.isRcvAlrm = isRcvAlrm;
	}
}
