<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/for/inc/_header.jsp" %>
</head>
<script>
	// 파일 드롭 다운
	function fileDropDown(){
	    var dropZone = $("#dropZone");
	    //Drag기능 
	    dropZone.on('dragenter',function(e){
	        e.stopPropagation();
	        e.preventDefault();
	        // 드롭다운 영역 css
	        dropZone.css('background-color','#E3F2FC');
	    });
	    dropZone.on('dragleave',function(e){
	        e.stopPropagation();
	        e.preventDefault();
	        // 드롭다운 영역 css
	        dropZone.css('background-color','#FFFFFF');
	    });
	    dropZone.on('dragover',function(e){
	        e.stopPropagation();
	        e.preventDefault();
	        // 드롭다운 영역 css
	        dropZone.css('background-color','#E3F2FC');
	    });
	    dropZone.on('drop',function(e){
	        e.preventDefault();
	        // 드롭다운 영역 css
	        dropZone.css('background-color','#FFFFFF');
	        
	        var files = e.originalEvent.dataTransfer.files;
	        if(files != null){
	            if(files.length < 1){
	                alert("폴더 업로드 불가");
	                return;
	            }
	        }else{
	            alert("ERROR");
	        }
	    });
	}
	
	//페이지 전환 함수
	function fn_egov_link_page(page){
		$("#page").val(page);
		$("#boardForm").attr("action", "/setting/board/faq.do");
		$("#boardForm").submit();
	}
	
	function insertFaq(){
		var fileName = $("#file")[0].files;
		if(fileName.length <= 0){
			alert("파일을 선택하여 주세요");
			return;
		}
		else{
			$("#faqFileNm").val(fileName[0].name);
	 		$("#boardForm").attr("action", "/setting/board/faqInsertAction.do");
			$("#boardForm").submit();
		}
	}
 	function fn_egov_downFile(fileId){
		$("#fileId").val(fileId);
		$("#fileForm").attr("action", "/board/downloadFile.do");
		$("#fileForm").submit();
	}
 	function updateUseYn(faqIdx, useYn){
 		if (confirm("변경하시겠습니까?") == true){    //확인
 			$("#faqIdx").val(faqIdx);
 			$("#useYn").val(useYn);
 			$("#boardForm").attr("action", "/setting/board/faqUpdateAction.do");
 			$("#boardForm").submit();
 		}else{   //취소
 		     return false;
 		}
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
		<h2>게시판 관리</h2>
<%@ include file="/WEB-INF/jsp/for/inc/_location.jsp" %>		
	</header>
	
	<div class="catg_area">
		<ul>
			<li><a href="for_014.htm">공지사항</a></li>
			<li><a href="javascript:;">자료실</a></li>
			<li class="on"><a href="for_014_faq.htm">FAQ</a></li>
		</ul>
	</div>
	
	<div class="tbl_top">
		<p class="result"><i class="fa fa-check-circle" aria-hidden="true"></i> 조회건 수 - 총 <strong><c:out value="${total_cnt}" /></strong>건</p>
	</div>
	
	<div class="table_h01 paragraph" style="height:calc(100% - 275px)">
		<table>
			<colgroup>
				<col style="width:70px">
				<col style="">
				<col style="">
				<col style="">
				<col style="">
				<!--스크롤바 영역 빈th-->
				<col class="col_scrollbar">
			</colgroup>
			<thead>
				<tr>
					<th>순번</th>
					<th>파일명</th>
					<th>작성자</th>
					<th>작성일자</th>
					<th>최종반영여부</th>
					<th></th>
				</tr>
			</thead>
		</table>
		<div>
			<table>
				<colgroup>
					<col style="width:70px">
					<col style="">
					<col style="">
					<col style="">
					<col style="">
				</colgroup>
				<tbody>
					<c:forEach var="result" items="${faqList}" varStatus="status">
					<tr>
						<td><c:out value="${result.listNum}" /></td>
						<td><a href="javascript:fn_egov_downFile('<c:out value="${result.fileId}" />')" class="link01"><c:out value="${result.faqFileNm}" /></a></td>
						<td><c:out value="${result.userName}" /></td>
						<fmt:parseDate value="${result.insrtDate}" var="dateValue" pattern="yyyy-MM-dd"/>
						<fmt:formatDate var="insrtDate" value="${dateValue}" pattern="yyyy-MM-dd" />
						<td><c:out value="${insrtDate}"/></td>
						<td>
							<c:choose>
								<c:when test="${result.useYn eq 'N'}">
									<a href="javascript:updateUseYn('<c:out value="${result.faqIdx}" />', 'Y')">
										<strong class="txt_red">
											<c:out value="${result.useYn}" />
										</strong>
									</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:updateUseYn('<c:out value="${result.faqIdx}" />', 'N')">
										<strong class="txt_blue">
											<c:out value="${result.useYn}" />
										</strong>
									</a>
								</c:otherwise>
							</c:choose>
						</td>
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
	<form id="boardForm" name="boardForm" method="post" enctype="multipart/form-data">
		<input id="faqFileNm" name="faqFileNm" type="hidden" value="">
		<input id="faqIdx" name="faqIdx" type="hidden", value="0">
		<input id="useYn" name="useYn" type="hidden", value="N">
		<input type="hidden" id="page" name="page" value="${page}" />
		<input type='hidden' id="boardType" name="boardType" value="FAQ" />
		<div class="table_v01">
			<table>
				<colgroup>
					<col style="width:150px">
					<col style="">
				</colgroup>
				<tbody>
					<tr>
						<th>첨부파일</th>
						<td><input id="file" name="file" type="file" style="width:100%"></td>
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
			<a href="javascript:insertFaq();" class="btn btn-lg btn-primary"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
		</div>
	</div>
	
</div>
<!--+++++ /컨텐츠 +++++-->
<%@ include file="/WEB-INF/jsp/for/inc/_help.jsp" %>
</body>
</html>