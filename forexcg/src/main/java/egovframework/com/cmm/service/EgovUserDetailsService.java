package egovframework.com.cmm.service;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.ActiveHistVO;
import egovframework.knia.foreign.exchange.vo.MenuVO;
import egovframework.knia.foreign.exchange.vo.UserRoleVO;

public interface EgovUserDetailsService {

	/**
	 * 로그인 (Session)정보 반화
	 * @return Object - 사용자 ValueObject
	 */
	public Object getAuthenticatedUser();

	/**
	 * 인증된 사용자의 권한 정보를 가져온다.
	 * 예) [ROLE_ADMIN, ROLE_USER, ROLE_A, ROLE_B, ROLE_RESTRICTED, IS_AUTHENTICATED_FULLY, IS_AUTHENTICATED_REMEMBERED, IS_AUTHENTICATED_ANONYMOUSLY]
	 * @return List - 사용자 권한정보 목록
	 */
	@Deprecated
	public List<String> getAuthorities();
	
	/**
	 * 현 메뉴 정보 조회
	 * @param ServletPath 접근URL
	 * @return MenuVO 메뉴 세부정보
	 */
	public MenuVO getMenuInfo(String sPath);
	
	/**
	 * ServletPath 기준 메뉴 이용권한 체크
	 * @param isMap
	 * @return Boolean 권한부여 여부
	 */
	public boolean isAuthorities(UserRoleVO userRoleVO);
	
	/**
	 * 인증된 사용자 여부를 체크한다.
	 * @return Boolean - 인증된 사용자 여부(TRUE / FALSE)	
	 */
	public Boolean isAuthenticated(); 

	public List<?> getActiveHistory(ActiveHistVO activeHistVO);
}
