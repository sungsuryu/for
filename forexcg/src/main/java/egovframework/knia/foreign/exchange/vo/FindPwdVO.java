package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class FindPwdVO implements Serializable {

	private static final long serialVersionUID = 7180599305003370638L;

	private String cellNum;
	
	private String userNm;
	
	private String loginId;

	public String getCellNum() {
		return cellNum;
	}

	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
