package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class FindIdVO implements Serializable {

	private static final long serialVersionUID = -172879274564890781L;

	private String cellNum;
	
	private String userNm;

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
}
