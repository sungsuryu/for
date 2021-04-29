package egovframework.knia.foreign.exchange.cmm.code;

public enum ConstCode {

	loginVO("loginVO");
	
	private String code;
	
	public void ConstCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	private ConstCode(String code) {
		this.code = code;
	}
}
