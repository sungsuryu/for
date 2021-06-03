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
 			changeMonth: true,
 			changeYear: true,
 			nextText: '다음 달',
 			prevText: '이전 달',
 			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
 			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dateFormat : 'yy-mm-dd',
				onSelect: function (date) {
					var endDate = $('#popupEnd');
					var startDate = $(this).datepicker('getDate');
					var minDate = $(this).datepicker('getDate');
					endDate.datepicker('setDate', minDate);
					startDate.setDate(startDate.getDate() + 30);
					endDate.datepicker('option', 'maxDate', startDate);
					endDate.datepicker('option', 'minDate', minDate);
				}
		}).datepicker("setDate", new Date());
		
		$("#popupEnd").datepicker({
 			changeMonth: true,
 			changeYear: true,
 			nextText: '다음 달',
 			prevText: '이전 달',
 			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
 			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dateFormat : 'yy-mm-dd'
		}).datepicker("setDate", new Date());
		
		$("#fileNm").click(function(){
			fn_egov_downFile();
		});
		
		nhn.husky.EZCreator.createInIFrame({
	 		oAppRef: editors,
	 		elPlaceHolder: 'popupContent',
	 		sSkinURI: "/smartEditor/SmartEditor2Skin.html",
	 		fOnAppLoad : function(){
	 			$("#popupForm").hide();
	        },
	 		fCreator: "createSEditor2",
	 		htParams : {fOnBeforeUnload : function(){}}
	 	});
		
		$("#" + "${popupVO.searchType}").attr("selected", "selected");
		
	});
	
	function fn_egov_link_page(page){
		$("#page").val(page);
		$("#popupForm").attr("action", "/setting/popup.do");
		$("#popupForm").submit();
	}
	
	//신규 팝업 추가
	function insertPopup(){
		$("#page").val(0);
		$("#searchName").val("");
		$("#searchType").val("");
		$("#popupForm").attr("action", "/setting/popupWriteAction.do");
		$("#popupForm").submit();
	}
	
	//팝업 수정
	function updatePopup(){
		$("#popupForm").attr("action", "/setting/popupEditAction.do");
		$("#popupForm").submit();
	}
	
	//팝업 수정
	function deletePopup(){
		$("#popupForm").attr("action", "/setting/popupDeleteAction.do");
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
		if($("#popupIdx").val() == 0){
			insertPopup();
		}
		else{
			updatePopup();
		}
	}
	
	function newWrite(){
		$("#newBtn").addClass("dispNon");
		$("#saveBtn").removeClass("dispNon");
		$("#popupForm").show();
	}
	
	function goPopupView(popupIdx){
		$("#popupIdx").val(popupIdx);
		var formData = $("#popupForm").serialize();
		$.ajax({
	        type:"POST",
	        url:"/setting/popupEditView.ajax",
	        data : formData,
	        success: function(data){
	        	var res = data.result;
	        	if(res.status == "SUCCESS"){
	        		$("#newBtn").addClass("dispNon");
	        		$("#saveBtn").removeClass("dispNon");
	        		$("#deleteBtn").removeClass("dispNon");
	        		$("#popupForm").show();
	        		
	        		$("#popupTitle").val(res.popupVO.popupTitle);
	        		$("#popupStart").val(res.popupVO.popupStartDt);
	        		$("#popupEnd").val(res.popupVO.popupEndDt);
	        		editors.getById["popupContent"].exec("SET_IR", [""]);
	        		editors.getById["popupContent"].exec("PASTE_HTML", [res.popupVO.popupContent]);
	        		
	        		if(res.isFile == "Y"){
	        			$("#fileId").val(res.fileList.fileId);
	        			$("#originFile").removeClass("dispNon");
	        			$("#fileNm").text(res.fileList.phyFileNm);
	        			$("#fileNm").css("display", "inline-block");
	        			$("#fileNm").css("margin-bottom", "8px");
	        		}
	        		else{
	        			$("#fileId").val("");
	        			$("#fileNm").text("");
	        			$("#originFile").addClass("dispNon");
	        		}
	        	}
	        	else{
	        		alert("불러오기 실패");
	        	}
	        },
			error: function(e){
				alert("불러오기 실패");
			}			
    	});
	}
	
	function deleteFile(){
		if (confirm("첨부파일을 삭제하시겠습니까?") == true){    //확인
			var formData = $("#popupForm").serialize();
			$.ajax({
		        type:"POST",
		        url:"/setting/popupDeletefile.ajax",
		        data : formData,
		        success: function(data){
		        	var res = data.result;
		        	if(res.resultStatus == "SUCCESS"){
	        			$("#fileId").val("");
	        			$("#fileNm").text("");
	        			$("#originFile").addClass("dispNon");
		        	}
		        	else{
		        		alert("파일삭제 실패");
		        	}
		        },
				error: function(e){
					alert("파일삭제 실패");
				}			
	    	});
 		}else{   //취소
 		     return false;
 		}
	}
	
 	function fn_egov_downFile(){
		$("#popupForm").attr("action", "/popup/downloadFile.do");
		$("#popupForm").submit();
	}
 	
 	function goSearch(){
 		$("#page").val(0);
 		$("#searchName").val($("#SearchText").val());
 		$("#searchType").val($("#searchTypeSelector option:selected").val());
 		$("#popupForm").attr("action", "/setting/popup.do");
		$("#popupForm").submit();
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
			<select id="searchTypeSelector">
				<option id="all" value="all">전체</option>
				<option id="title" value="title">제목</option>
				<option id="content" value="content">내용</option>
				<option id="userNm" value="userNm">작성자</option>
			</select>
		<i class="fa fa-sort" aria-hidden="true"></i>
		</span>
		<span class="space"></span>
		<span class="label">검색어</span>
		<input id="SearchText" name="SearchText" type="text" value="<c:out value="${popupVO.searchName}" />">
		<a href="javascript:goSearch();" class="btn btn-sm btn-info btn_submit"><i class="fa fa-search" aria-hidden="true"></i> 조회</a>
	</div>
	
	<div class="tbl_top">
		<p class="result"><i class="fa fa-check-circle" aria-hidden="true"></i> 조회건 수 - 총 <strong><c:out value="${popupVO.totalCnt}" /></strong>건</p>
	</div>
	
	<div class="table_h01 paragraph">
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
						<c:set var="pageCnt" value="${pageCnt-1}"></c:set>
						<tr>
							<td><c:out value="${pageCnt}"/></td>
							<td class="left"><a href="javascript:goPopupView(<c:out value="${result.popupIdx}" />)"><c:out value="${result.popupTitle}" /></a></td>
							<td><fmt:formatDate value="${result.insrtDate }" pattern="yyyy.MM.dd"/></td>
							<td><c:out value="${result.userNm}" /></td>
							<td><fmt:formatDate value="${result.popupStart }" pattern="yyyy.MM.dd"/></td>
							<td><fmt:formatDate value="${result.popupEnd }" pattern="yyyy.MM.dd"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="f_left">
				<div class="pagenum">
					<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
				</div>
			</div>
		</div>
	</div>
	<form id="popupForm" name="popupForm" method="post" enctype="multipart/form-data" style="display:inline-block; margin-top:5px;">
		<input id="popupIdx" name="popupIdx" type="hidden" value="0">
		<input id="fileId" name="fileId" type="hidden" value="">
		<input id="page" name="page" type="hidden" value="${popupVO.page}">
		<input id="searchName" name="searchName" type="hidden" value="${popupVO.searchName}">
		<input id="searchType" name="searchType" type="hidden" value="${popupVO.searchType}">
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
				        	<div id="originFile" class="dispNon">
				        		<h3 id="fileNm" style="cursor:pointer;"></h3>&nbsp
								<a href="javascript:deleteFile()"><i id="deleteFilebtn" class="fa fa-times-circle" aria-hidden="true"></i></a>
				        	</div>
							<div>
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
			<a id="newBtn" href="javascript:newWrite();" class="btn btn-lg btn-green"><i class="fa fa-plus-circle" aria-hidden="true"></i> 신규</a>
			<a id="saveBtn" href="javascript:valueCheck();" class="btn btn-lg btn-primary dispNon"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
			<a id="deleteBtn" href="javascript:deletePopup();" class="btn btn-lg btn-red dispNon"><i class="fa fa-trash-o" aria-hidden="true"></i> 삭제</a>
		</div>
	</div>
	
</div>
<!--+++++ /컨텐츠 +++++-->
<%@ include file="/WEB-INF/jsp/for/inc/_help.jsp" %>
</body>
</html>