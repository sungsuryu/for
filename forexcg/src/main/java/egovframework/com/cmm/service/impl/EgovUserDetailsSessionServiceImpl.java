package egovframework.com.cmm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import egovframework.knia.foreign.exchange.vo.ActiveHistVO;
import egovframework.knia.foreign.exchange.vo.LoginVO;
import egovframework.knia.foreign.exchange.vo.MenuVO;
import egovframework.knia.foreign.exchange.vo.UserRoleVO;
import egovframework.com.cmm.service.EgovUserDetailsService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import egovframework.knia.foreign.exchange.cmm.code.ConstCode;
import egovframework.knia.foreign.exchange.cmm.code.MenuCode;
import egovframework.knia.foreign.exchange.dao.mapper.ActiveHistMapper;
import egovframework.knia.foreign.exchange.dao.mapper.LoginMapper;
import egovframework.knia.foreign.exchange.dao.mapper.MenuMapper;
import egovframework.knia.foreign.exchange.dao.mapper.UserRoleMapper;
/**
 * 
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 6. 25.
 * @version 1.0
 * @see
 *
 * <pre>
 * 개정이력(Modification Information) 
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011. 8. 12.    서준식        최초생성
 *  2021. 5. 07.    류성수        서비스환경으로 수정
 *  
 *  </pre>
 */

public class EgovUserDetailsSessionServiceImpl extends EgovAbstractServiceImpl implements EgovUserDetailsService {

	@Resource(name="userRoleMapper")
	private UserRoleMapper userRoleMapper;
	
	@Resource(name="activeHistMapper")
	private ActiveHistMapper activeHistMapper;
	
	@Resource(name="menuMapper")
	private MenuMapper menuMapper;
	
	
	
	public Object getAuthenticatedUser() {
		if (RequestContextHolder.getRequestAttributes() == null) {
			return null;
		}

		return RequestContextHolder.getRequestAttributes().getAttribute(ConstCode.loginVO.toString(), RequestAttributes.SCOPE_SESSION);
	}
	
	public List<String> getAuthorities() {

		List<String> listAuth = new ArrayList<String>();
		try {
			listAuth = userRoleMapper.selectUserRoleGroupByRoleId();
		} catch (Exception e) {
			;
		}

		return listAuth;
	}
	
	public MenuVO getMenuInfo(String sPath) {
		
		MenuVO mVo = new MenuVO();
		try {
			mVo = menuMapper.selectMenuFindByUrl(sPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mVo;
	}
	
	public boolean isAuthorities(UserRoleVO userRoleVO) {
		try {
			UserRoleVO getRole = userRoleMapper.selectMenuIdFindByRoleId(userRoleVO);	// 권한여부 확인
			if (getRole != null) {
				if (getRole.getMnuType().equals(MenuCode.VIEW.toString())) {	// 현 메뉴가 VIEW 인지
					ActiveHistVO atvVo = new ActiveHistVO();
					atvVo.setUserId(userRoleVO.getInsrtId());
					atvVo.setMnuId(getRole.getMnuId());
	
					activeHistMapper.insertActiveHist(atvVo);	// 현 메뉴접근 정보를 History 등록
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();	
			return false;
		}
		return false;
	}

	public Boolean isAuthenticated() {
		// 인증된 유저인지 확인한다.

		if (RequestContextHolder.getRequestAttributes() == null) {
			return false;
		} else {
			if (RequestContextHolder.getRequestAttributes().getAttribute(ConstCode.loginVO.toString(), RequestAttributes.SCOPE_SESSION) == null) {
				return false;
			} else {
				return true;
			}
		}
	}

	public List<?> getActiveHistory(ActiveHistVO activeHistVO) {
		try {
			return activeHistMapper.selectActiveHistFindByUserId(activeHistVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
