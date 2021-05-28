package egovframework.knia.foreign.exchange.vo;

import java.io.Serializable;

public class UserRoleVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 2937874475287816027L;

	private String roleId;
	
	private String mnuId;
	
	private String roleNm;
	
	private String useYn;
	
	private String url;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMnuId() {
		return mnuId;
	}

	public void setMnuId(String mnuId) {
		this.mnuId = mnuId;
	}

	public String getRoleNm() {
		return roleNm;
	}

	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
