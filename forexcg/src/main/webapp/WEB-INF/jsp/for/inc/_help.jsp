<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${menuInfo.mnuType eq 'VIEW' }">
		<c:set var="helpId" value="${menuInfo.mnuId }" />
	</c:when>
	<c:otherwise>
		<c:set var="helpId" value="${menuInfo.srcMnuId }" />
	</c:otherwise>
</c:choose>
<aside id="aside_right">
	<header class="aside_right_header">
		<h2>도움말</h2>
		<a href="javascript:;" class="btn_close">창닫기</a>
	</header>
	<div class="aside_right_con">
		<input id="uiId" name="uiId" type="hidden" value="<c:out value="${helpId }" />" />
		<c:choose>
			<c:when test="${loginVO.roleId eq 'ROLE_ADMIN'}">
				<textarea id="guideContent" name="guideContent" rows="40" style="resize:none;"></textarea>
				<a id="updateGuide" href="javascript:updateGuide();" class="btn"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
			</c:when>
			<c:otherwise>
				<textarea id="guideContent" name="guideContent" rows="40" style="resize:none;" readonly="readonly"></textarea>
				<a id="updateGuide" href="javascript:updateGuide();" class="btn" style='display:none;'><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
			</c:otherwise>
		</c:choose>
	</div>
</aside>
