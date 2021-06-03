<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ include file="/WEB-INF/jsp/for/inc/_header.jsp" %>
</head>
<script>
	$(document).ready(function() {
	});
	
 	function fn_egov_downFile(fileId){
		$("#fileId").val(fileId);
		$("#fileForm").attr("action", "/board/downloadFile.do");
		$("#fileForm").submit();
	}
	
	function goPds(){
		$("#boardForm").attr("action", "/board/pds.do");
		$("#boardForm").submit();
	}
</script>
<body>

<div id="popLayerBg"></div>

<!--+++++ 상단 +++++-->
<header id="header">
<%@ include file="/WEB-INF/jsp/for/inc/_gnb.jsp" %>		
	<div class="top_con">
		<a href="javascript:" class="btn_sh" title="상단메뉴 열기/닫기">
			<i class="fa fa-angle-down" aria-hidden="true"></i>
			<i class="fa fa-angle-up" aria-hidden="true"></i>
		</a>
		<%@ include file="/WEB-INF/jsp/for/inc/_menu_history.jsp" %>
		<%@ include file="/WEB-INF/jsp/for/inc/_setting.jsp" %>	
	</div>
</header>
<!--+++++ /상단 +++++-->

<!--+++++ 좌측 레이어 +++++-->
<aside id="aside_left">
	<a href="javascript:;" class="btn btn_sh" title="좌측메뉴 열기/닫기">
		<i class="fa fa-angle-right" aria-hidden="true"></i>
		<i class="fa fa-angle-left" aria-hidden="true"></i>
	</a>
	<header class="aside_left_header">
		<h1><a href="main.htm"><img src="img/ci.png" alt="ci"></a></h1>
		<h2>외환정보시스템</h2>
	</header>
<%@ include file="/WEB-INF/jsp/for/inc/_lnb.jsp" %>	
</aside>
<!--+++++ /좌측 레이어 +++++-->

<!--+++++ 컨텐츠 +++++-->
<div id="contents">
	
	<header class="contents_header">
		<h2>보고관련 자료실</h2>
<%@ include file="/WEB-INF/jsp/for/inc/_location.jsp" %>		
	</header>
	<form id="boardForm" name="boardForm" method="post" enctype="multipart/form-data">
		<input type='hidden' id="alarmYn" name='alarmYn'>
		<input type='hidden' id="page" name='page' value="${boardVO.page}">
		<input type='hidden' id="searchName" name='searchName' value="${boardVO.searchName}">
		<div class="table_v01">
			<table>
				<colgroup>
					<col style="width:150px">
					<col style="">
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td><c:out value="${boardVO.boardTitle}" /></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><c:out value="${boardVO.userName}" /></td>
					</tr>
					<c:if test="${fn:length(fileList)!= 0}">
						<tr>
							<th>첨부파일</th>
							<td>
								<c:forEach var="result" items="${fileList}" varStatus="status">
									<a href="javascript:fn_egov_downFile('<c:out value="${result.fileId}"/>')" class="link01">
										<c:out value="${result.phyFileNm}"/>&nbsp;[<c:out value="${result.fileSize}"/>&nbsp;byte]</br>
									</a>
								</c:forEach>
							</td>
						</tr>
					</c:if>
					<tr>
						<th>내용</th>
						<td>
							<div>
								<c:out value="${boardVO.boardContent}" escapeXml="false" />
							<div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	<form id="fileForm" name="fileForm" method="post">
		<input id="fileId" name="fileId" type="hidden">
	</form>
	<div class="tbl_btm">
		<div class="f_right">
			<a href="javascript:goPds()"  class="btn btn-lg"><i class="fa fa-list-alt" aria-hidden="true"></i> 목록</a>
		</div>
	</div>
	
</div>
<!--+++++ /컨텐츠 +++++-->
<%@ include file="/WEB-INF/jsp/for/inc/_help.jsp" %>
</body>
</html>