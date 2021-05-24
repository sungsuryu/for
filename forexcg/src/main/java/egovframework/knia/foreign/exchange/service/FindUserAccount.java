package egovframework.knia.foreign.exchange.service;

import egovframework.knia.foreign.exchange.vo.FindIdVO;
import egovframework.knia.foreign.exchange.vo.FindPwdVO;

public interface FindUserAccount {

	public boolean findLoginId(FindIdVO findIdVO) throws Exception;
	
	public boolean findPassword(FindPwdVO findPwdVO) throws Exception;
}
