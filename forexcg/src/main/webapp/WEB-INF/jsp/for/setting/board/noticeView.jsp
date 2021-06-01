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
	var editors = [];
	$(document).ready(function() {
	});
	
 	function fn_egov_downFile(fileId){
		$("#fileId").val(fileId);
		$("#fileForm").attr("action", "/board/downloadFile.do");
		$("#fileForm").submit();
	}
	
	function goEdit(){
		$("#boardForm").attr("action", "/setting/board/noticeEdit.do");
		$("#boardForm").submit();
	}
	
	function goNotice(){
		$("#boardForm").attr("action", "/setting/board/notice.do");
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
	
	<!--div class="tab_menu col7">
		<ul>
			<li><a href="for_009.htm">공통코드 관리</a></li>
			<li><a href="for_010.htm">보고서 관리</a></li>
			<li><a href="for_011.htm">메뉴 관리</a></li>
			<li><a href="for_012.htm">그룹별 메뉴 관리</a></li>
			<li><a href="for_013.htm">팝업 관리</a></li>
			<li class="on"><a href="for_014.htm">게시판 관리</a></li>
			<li><a href="for_015.htm">한국은행 시스템 정보 관리</a></li>
		</ul>
	</div-->
	
	<header class="contents_header">
		<h2>게시판 관리</h2>
<%@ include file="/WEB-INF/jsp/for/inc/_location.jsp" %>		
	</header>
	
	<div class="catg_area">
		<ul>
			<li class="on"><a href="javascript:;">공지사항</a></li>
			<li><a href="javascript:;">자료실</a></li>
			<li><a href="for_014_faq.htm">FAQ</a></li>
		</ul>
	</div>
	<form id="boardForm" name="boardForm" method="post" enctype="multipart/form-data">
		<input type='hidden' id="alarmYn" name='alarmYn'>
		<input type='hidden' id="boardIdx" name='boardIdx' value="${boardVO.boardIdx}">
		<input type='hidden' id="page" name='page' value="${boardVO.page}">
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
			<a href="javascript:goEdit();" class="btn btn-lg btn-primary"><i class="fa fa-check-circle" aria-hidden="true"></i> 수정</a>
			<a href="javascript:goNotice()" class="btn btn-lg"><i class="fa fa-list-alt" aria-hidden="true"></i> 목록</a>
		</div>
	</div>
	
</div>
<!--+++++ /컨텐츠 +++++-->

<!--+++++ 우측 레이어(도움말) +++++-->
<aside id="aside_right">
	<header class="aside_right_header">
		<h2>도움말</h2>
		<a href="javascript:;" class="btn_close">창닫기</a>
	</header>
	<div class="aside_right_con">
		<textarea>도움말 내용</textarea>
		<a href="javascript:;" class="btn"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
	</div>
</aside>
<!--+++++ /우측 레이어(도움말) +++++-->

<script>

</script>

</body>
</html>