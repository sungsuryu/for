package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class InsureVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = -8193803072183120458L;

	private String insurCd;
	
	private String orgCd;
	
	private String insurNm;
	
	private String useYn;

	public String getInsurCd() {
		return insurCd;
	}

	public void setInsurCd(String insurCd) {
		this.insurCd = insurCd;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getInsurNm() {
		return insurNm;
	}

	public void setInsurNm(String insurNm) {
		this.insurNm = insurNm;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
