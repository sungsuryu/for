package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class TermsAgreeHistVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = -7478958863419821413L;

	private int hisNum;
	
	private String userId;
	
	private String agreeYn;

	public int getHisNum() {
		return hisNum;
	}

	public void setHisNum(int hisNum) {
		this.hisNum = hisNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAgreeYn() {
		return agreeYn;
	}

	public void setAgreeYn(String agreeYn) {
		this.agreeYn = agreeYn;
	}
}
