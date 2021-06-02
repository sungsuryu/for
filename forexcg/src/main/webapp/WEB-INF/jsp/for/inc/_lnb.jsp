<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<nav class="gnb">
		<ul>
		<c:forEach var="tMenu" items="${gnbMenu.tMnu }">
			<li class="depth1">
			<c:choose>
				<c:when test="${tMenu.mnuType eq 'VIEW' }"><a href="<c:out value="${tMenu.url }" />"></c:when>
				<c:otherwise><a href="javascript:void(0)"></c:otherwise>
			</c:choose>
			<c:out value="${tMenu.mnuNm }" /></a>
				<ul>
				<c:forEach var="mMenu" items="${gnbMenu.mMnu }" >
					<c:if test="${mMenu.prtMnuId eq tMenu.mnuId }">
					<li class="depth2">
					<c:choose>
						<c:when test="${mMenu.mnuType eq 'VIEW' }"><a href="<c:out value="${mMenu.url }" />"></c:when>
						<c:otherwise><a href="javascript:void(0)"></c:otherwise>
					</c:choose>
					<c:out value="${mMenu.mnuNm }" /></a>
						<ul>
						<c:forEach var="lMenu" items="${gnbMenu.lMnu }">
							<c:if test="${lMenu.prtMnuId eq mMenu.mnuId }"><li class="depth3">
							<c:choose>
								<c:when test="${lMenu.mnuType eq 'VIEW' }"><a href="<c:out value="${lMenu.url }" />"></c:when>
								<c:otherwise><a href="javascript:void(0)"></c:otherwise>
							</c:choose>
								<c:out value="${lMenu.mnuNm }" /></a>
							</li></c:if>
						</c:forEach>
						</ul>
					</li>
					</c:if>
				</c:forEach>
				</ul>
			</li>
		</c:forEach>
		</ul>
	</nav>