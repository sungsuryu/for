<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/for/inc/_header.jsp" %>
<%
	
%>
<script type="text/javascript">

	$(function(){
	});
	//페이지 전환 함수
	function fn_egov_link_page(page){
		$("#page").val(page);
		$("#boardForm").attr("action", "/setting/board/notice.do");
		$("#boardForm").submit();
	}
	function goBoardView(boardIdx){
		$("#boardIdx").val(boardIdx);
		$("#boardForm").attr("action", "/setting/board/noticeView.do");
		$("#boardForm").submit();
	}
	
  	function doDownloadZip(boardIdx){
 		$("#boardIdx").val(boardIdx);
		$("#boardForm").attr("action", "/board/downloadZipFile.do");
		$("#boardForm").submit();
//		window.open("<c:url value='/board/downloadZipFile.do?boardIdx="+boardIdx+"&boardType="+"NOTICE"+"'/>");
	}

	/* function doDownloadZip(boardIdx){
		$.ajax({
	        type:"POST",
	        url:"/board/downloadZipFile.ajax",
	        data : {
	        	"boardIdx":boardIdx,
	        	"boardType":"NOTICE"
	        },
	        success: function(data){
	        	alert("파일다운로드 성공");
	        },
			error: function(e){
				alert("파일삭제 실패");
			}			
		});
	} */
</script>
</head>

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
		<h2>게시판 관리</h2>
<%@ include file="/WEB-INF/jsp/for/inc/_location.jsp" %>		
	</header>
	
	<div class="catg_area">
		<ul>
			<li class="on"><a href="/setting/board/notice.do">공지사항</a></li>
			<li><a href="/setting/board/pds.do">자료실</a></li>
			<li><a href="/setting/board/faq.do">FAQ</a></li>
		</ul>
	</div>
	
	<div class="tbl_top">
		<p class="result"><i class="fa fa-check-circle" aria-hidden="true"></i> 조회건 수 - 총 <strong><c:out value="${boardVO.totalCnt}" /></strong>건</p>
	</div>
	
	<div class="table_h01">
		<table>
			<colgroup>
				<col style="width:70px">
				<col style="">
				<col style="width:120px">
				<col style="width:120px">
				<col style="width:120px">
				<col style="width:120px">
			</colgroup>
			<thead>
				<tr>
					<th>순번</th>
					<th>제목</th>
					<th>첨부파일</th>
					<th>작성자</th>
					<th>작성일자</th>
					<th>조회</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${boardList}" varStatus="status">
					<c:set var="pageCnt" value="${pageCnt-1}"></c:set>
					<tr>
						<td><c:out value="${pageCnt}" /></td>
						<td class="left"><a href="javascript:goBoardView(<c:out value="${result.boardIdx}" />)"><c:out value="${result.boardTitle}" /></a></td>
						<td>
							<c:if test = "${result.fileCnt > 0}">
								<a href="javascript:doDownloadZip(<c:out value="${result.boardIdx}" />)" title="다운로드"><i class="fa fa-download" aria-hidden="true"></i></a>
							</c:if>
						</td>
						<td><c:out value="${result.userName}" /></td>
						<fmt:parseDate value="${result.insrtDate}" var="dateValue" pattern="yyyy-MM-dd"/>
						<fmt:formatDate var="insrtDate" value="${dateValue}" pattern="yyyy-MM-dd" />
						<td><c:out value="${insrtDate}"/></td>
						<td><c:out value="${result.viewCnt}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="tbl_btm">
		<div class="f_left">
			<div class="pagenum">
				<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
			</div>
		</div>
		<div class="f_right">
			<a href="/setting/board/noticeWrite.do" class="btn btn-lg btn-primary"><i class="fa fa-pencil" aria-hidden="true"></i> 글쓰기</a>
		</div>
	</div>
	<form id="boardForm" name="boardForm" method="post" action="/setting/board/notice.do">
		<input type="hidden" id="page" name="page" value="${boardVO.page}" />
		<input type='hidden' id="boardIdx" name='boardIdx' value="0" />
		<input type='hidden' id="boardType" name="boardType" value="NOTICE" />
	</form>
</div>
<!--+++++ /컨텐츠 +++++-->
<%@ include file="/WEB-INF/jsp/for/inc/_help.jsp" %>
</body>
</html>