<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/for/inc/_header.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery-ui.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/EgovMultiFile.js?ver=1.1'/>" ></script>
<script type="text/javascript" src="<c:url value='/smartEditor/js/service/HuskyEZCreator.js'/>" ></script>
<script type="text/javascript">
	var editors = [];
	$(document).ready(function() {
 		$("#popupStart").datepicker({
			dateFormat : 'yy-mm-dd'
		});
		
		$("#popupEnd").datepicker({
			dateFormat : 'yy-mm-dd'
		});
		nhn.husky.EZCreator.createInIFrame({
	 		oAppRef: editors,
	 		elPlaceHolder: 'popupContent',
	 		sSkinURI: "/smartEditor/SmartEditor2Skin.html",
	 		fCreator: "createSEditor2"
	 	});
	});
	
	//신규 팝업 추가
	function insertPopup(){
		$("#popupForm").attr("action", "/setting/popupWriteAction.do");
		$("#popupForm").submit();
	}
	
	function valueCheck(){
		editors.getById["popupContent"].exec("UPDATE_CONTENTS_FIELD", []);
		var popupTitle = $("#popupTitle").val();
		var popupContent = $("#popupContent").val();
		var popupStart = $("#popupStart").val();
		var popupEnd = $("#popupEnd").val();
		
		if(popupTitle == "" || popupTitle == null){
			alert("제목을 적어주세요.");
			return
		}
		if( popupContent == ""  || popupContent == null || popupContent == '&nbsp;' || popupContent == '<br>' || popupContent == '<br />' || popupContent == '<p>&nbsp;</p>' || popupContent == '<p><br></p>')  {
	        alert("내용을 입력하세요.");
	        oEditors.getById["COMVISION"].exec("FOCUS"); //포커싱
	        return;
	    }
		if(popupStart == "" || popupStart == null){
			alert("게시 시작일을 선택하여 주세요.");
			return
		}
		if(popupEnd == "" || popupEnd == null){
			alert("게시 종료일을 선택하여 주세요.");
			return
		}
		insertPopup();
	}
	
	function goPopupView(popupIdx){
		
	}
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
		<h2>팝업 관리</h2>
<%@ include file="/WEB-INF/jsp/for/inc/_location.jsp" %>
	</header>
	
	<div class="top_search_area">
		<span class="label">검색항목</span>
		<span class="styled_select">
			<select>
				<option>전체</option>
				<option></option>
				<option></option>
				<option></option>
			</select>
		<i class="fa fa-sort" aria-hidden="true"></i>
		</span>
		<span class="space"></span>
		<span class="label">검색어</span>
		<input type="text">
		<a href="javascript:;" class="btn btn-sm btn-info btn_submit"><i class="fa fa-search" aria-hidden="true"></i> 조회</a>
	</div>
	
	<div class="tbl_top">
		<p class="result"><i class="fa fa-check-circle" aria-hidden="true"></i> 조회건 수 - 총 <strong>10</strong>건</p>
	</div>
	
	<div class="table_h01 paragraph" style="height:calc(100% - 495px)">
		<table>
			<colgroup>
				<col style="width:70px">
				<col style="">
				<col style="width:120px">
				<col style="width:120px">
				<col style="width:120px">
				<col style="width:120px">
				<!--스크롤바 영역 빈th-->
				<col class="col_scrollbar">
			</colgroup>
			<thead>
				<tr>
					<th>순번</th>
					<th>제목</th>
					<th>작성일자</th>
					<th>작성자</th>
					<th>게시 시작일</th>
					<th>게시 종료일</th>
					<th></th>
				</tr>
			</thead>
		</table>
		<div>
			<table>
				<colgroup>
					<col style="width:70px">
					<col style="">
					<col style="width:120px">
					<col style="width:120px">
					<col style="width:120px">
					<col style="width:120px">
				</colgroup>
				<tbody>
					<c:forEach var="result" items="${popupList}" varStatus="status">
						<tr>
							<td><c:out value="${result.listNum}" /></td>
							<td class="left"><a href="javascript:goPopupView(<c:out value="${result.popupIdx}" />)"><c:out value="${result.popupTitle}" /></a></td>
							<td><c:out value="${result.insrtDate}"/></td>
							<td><c:out value="${result.userNm}" /></td>
							<td><c:out value="${result.popupStart}" /></td>
							<td><c:out value="${result.popupEnd}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<form id="popupForm" name="popupForm" method="post" enctype="multipart/form-data">
		<div class="table_v01">
			<table>
				<colgroup>
					<col style="width:150px">
					<col style="">
					<col style="width:150px">
					<col style="">
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td colspan="3"><input id="popupTitle" name="popupTitle" type="text" style="width:100%"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea id="popupContent" name="popupContent" rows="4" style="height:100px"></textarea>
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="3">
								<input type="file" name="file" id="egovComFileUploader" title="첨부파일" />
							</div>
						</td>
					</tr>
					<tr>
						<th>게시 시작일</th>
						<td><input id="popupStart" name="popupStartDt" type="text" readonly class="input_date"><i class="fa fa-calendar" aria-hidden="true"></i></td>
						<th>게시 종료일</th>
						<td><input id="popupEnd" name="popupEndDt" type="text" readonly class="input_date"><i class="fa fa-calendar" aria-hidden="true"></i></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	<div class="tbl_btm">
		<div class="f_right">
			<a href="javascript:;" class="btn btn-lg btn-green"><i class="fa fa-plus-circle" aria-hidden="true"></i> 신규</a>
			<a href="javascript:valueCheck();" class="btn btn-lg btn-primary"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
			<a href="javascript:;" class="btn btn-lg btn-red"><i class="fa fa-trash-o" aria-hidden="true"></i> 삭제</a>
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
$(function(){
});
</script>

</body>
</html>