<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<div class="navi">
			<a href="main.htm" class="btn_home" title="홈"><i class="fa fa-home" aria-hidden="true"></i></a>
			<span class="styled_select">
				<select id="tMnu" name="tMnu">
				<c:forEach var="tMenu" items="${gnbMenu.tMnu }" varStatus="status">
					<option value="<c:out value="${tMenu.mnuId }" />"><c:out value="${tMenu.mnuNm }" /></option>
				</c:forEach>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
			<span class="styled_select">
				<select id="mMnu" name="mMnu">
				<c:forEach var="mMenu" items="${gnbMenu.mMnu }" varStatus="status">
					<option value="<c:out value="${mMenu.mnuId }" />"><c:out value="${mMenu.mnuNm }" /></option>
				</c:forEach>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
			<span class="styled_select">
				<select id="lMnu" name="lMnu">
				<c:forEach var="lMenu" items="${gnbMenu.lMnu }" varStatus="status">
					<option value="<c:out value="${lMenu.mnuId }" />"><c:out value="${lMenu.mnuNm }" /></option>
				</c:forEach>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
		</div>