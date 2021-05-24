package egovframework.knia.foreign.exchange.service;

import java.util.HashMap;

public interface KaTalkService {

	/**
	 * 회원가입 - 휴대폰인증
	 * @param hMap
	 * @throws Exception
	 */
	public void sendOTPforJoin(HashMap<String, Object> hMap) throws Exception;
	
	/**
	 * 사용자 ID찾기 
	 * @param hMap
	 * @throws Exception
	 */
	public void sendFindUserId(HashMap<String, Object> hMap) throws Exception;
}
