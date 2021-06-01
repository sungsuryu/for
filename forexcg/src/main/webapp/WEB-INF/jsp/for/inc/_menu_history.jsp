<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<div class="page_area">
			<a href="javascript:void(0)" class="btn_move btn_prev" title="이전"><i class="fa fa-angle-left" aria-hidden="true"></i></a>
			<a href="javascript:void(0)" class="btn_move btn_next" title="다음"><i class="fa fa-angle-right" aria-hidden="true"></i></a>
		<c:forEach var="hisMn" items="${histVo }" varStatus="status">
			<div class="page_unit on">
				<h3 class="btnGoHis"><c:out value="${hisMn.mnuNm }" /><span class="dispNon"><c:out value="${hisMn.mnuId }" /></span><url class="dispNon"><c:out value="${hisMn.url }" /></url></h3>
				<a href="javascript:void(0)" class="btn_page_close btnDeleteHis" title="페이지 닫기"><i class="fa fa-times-circle" aria-hidden="true"></i></a>
			</div>
		</c:forEach>
		</div>