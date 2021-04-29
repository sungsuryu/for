package egovframework.knia.foreign.exchange.cmm.code;

public enum ResponseCode {

	RESULT_0("0", "Success"), 
	RESULT_403("403", "Forbidden"), 
	RESULT_500("500", "Server Error");
	
	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private ResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
