<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<div class="navi">
		
		<c:choose>
			<c:when test="${menuInfo.lvl == 1 }">
				<c:choose>
					<c:when test="${menuInfo.mnuType eq 'VIEW' }">
						<c:set var="firstId" value="${menuInfo.mnuId }" />
					</c:when>
					<c:otherwise>
						<c:set var="firstId" value="${menuInfo.srcMnuId }" />
					</c:otherwise>
				</c:choose>
				<c:set var="secondId" value="" />
				<c:set var="thirdId" value="" />
			</c:when>
			<c:when test="${menuInfo.lvl == 2 }">
				<c:set var="firstId" value="${menuInfo.prtMnuId }" />
				<c:choose>
					<c:when test="${menuInfo.mnuType eq 'VIEW' }">
						<c:set var="secondId" value="${menuInfo.mnuId }" />
					</c:when>
					<c:otherwise>
						<c:set var="secondId" value="${menuInfo.srcMnuId }" />
					</c:otherwise>
				</c:choose>
				<c:set var="thirdId" value="" />
			</c:when>
			<c:when test="${menuInfo.lvl == 3 }">
				<c:set var="firstId" value="${menuInfo.stMnuId }" />
				<c:set var="secondId" value="${menuInfo.prtMnuId }" />
				<c:choose>
					<c:when test="${menuInfo.mnuType eq 'VIEW' }">
						<c:set var="thirdId" value="${menuInfo.mnuId }" />
					</c:when>
					<c:otherwise>
						<c:set var="thirdId" value="${menuInfo.srcMnuId }" />
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
		
		<c:out value="${crMid }" />
			<a href="main.htm" class="btn_home" title="홈"><i class="fa fa-home" aria-hidden="true"></i></a>
			<span class="styled_select">
				<select id="tMnu" name="tMnu">
				<c:forEach var="tMenu" items="${gnbMenu.tMnu }" varStatus="status">
					<option value="<c:out value="${tMenu.mnuId }" />"
					<c:if test="${tMenu.mnuId eq firstId }">selected="selected"</c:if>
					><c:out value="${tMenu.mnuNm }" /></option>
				</c:forEach>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
			<span class="styled_select">
				<select id="mMnu" name="mMnu">
				<c:forEach var="mMenu" items="${gnbMenu.mMnu }" varStatus="status">
					<c:if test="${mMenu.prtMnuId eq firstId }">
						<option value="<c:out value="${mMenu.mnuId }" />"
						<c:if test="${mMenu.mnuId eq secondId }">selected="selected"</c:if>
						><c:out value="${mMenu.mnuNm }" /></option>
					</c:if>
				</c:forEach>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
			<span class="styled_select">
				<select id="lMnu" name="lMnu">
				<c:forEach var="lMenu" items="${gnbMenu.lMnu }" varStatus="status">
					<c:if test="${lMenu.prtMnuId eq secondId }">
						<option value="<c:out value="${lMenu.mnuId }" />"
						<c:if test="${lMenu.mnuId eq thirdId }">selected="selected"</c:if>
						><c:out value="${lMenu.mnuNm }" /></option>
					</c:if>
				</c:forEach>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
		</div>