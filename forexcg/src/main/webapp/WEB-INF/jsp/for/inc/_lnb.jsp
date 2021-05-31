<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<nav class="gnb">
		<ul>
		<c:forEach var="tMenu" items="${gnbMenu.tMnu }" varStatus="status">
			<li class="depth1">
			<a href="javascript:void(0)"><c:out value="${tMenu.mnuNm }" /></a>
				<ul>
				<c:forEach var="mMenu" items="${gnbMenu.mMnu }" varStatus="status">
					<c:if test="${mMenu.prtMnuId eq tMenu.mnuId }"><li class="depth2"><a href="javascript:void(0)"><c:out value="${mMenu.mnuNm }" /></a></li></c:if>
				</c:forEach>
					<ul>
					<c:forEach var="lMenu" items="${gnbMenu.lMnu }" varStatus="status">
						<c:forEach var="lMenu" items="${gnbMenu.lMnu }" varStatus="status">
							<c:if test="${lMenu.prtMnuId eq mMenu.mnuId }"><li class="depth3"><a href="javascript:void(0)"><c:out value="${lMenu.mnuNm }" /></a></li></c:if>
						</c:forEach>
					</c:forEach>
					</ul>
				</ul>
			</li>
		</c:forEach>
		</ul>
	</nav>