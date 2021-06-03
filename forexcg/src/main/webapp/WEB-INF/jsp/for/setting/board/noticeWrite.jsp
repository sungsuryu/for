<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ include file="/WEB-INF/jsp/for/inc/_header.jsp" %>
<script type="text/javascript" src="<c:url value='/js/EgovMultiFile.js?ver=1.1'/>" ></script>
<script type="text/javascript" src="<c:url value='/smartEditor/js/service/HuskyEZCreator.js'/>" ></script>
</head>
<script>
	var editors = [];
	$(document).ready(function() {
		//fileDropDown();
		makeFileAttachment();
		nhn.husky.EZCreator.createInIFrame({
	 		oAppRef: editors,
	 		elPlaceHolder: 'boardContent',
	 		sSkinURI: "/smartEditor/SmartEditor2Skin.html",
	 		fCreator: "createSEditor2"
	 	});
	});
	
	function insertBoard(){
		$("#boardForm").attr("action", "/setting/board/noticeWriteAction.do");
		$("#boardForm").submit();
	}
	
	function valueCheck(){
		editors.getById["boardContent"].exec("UPDATE_CONTENTS_FIELD", []);
		var boardTitle = $("#boardTitle").val();
		var boardContent = $("#boardContent").val();
		if(boardTitle == "" || boardTitle == null){
			alert("제목을 적어주세요.");
			return
		}
		if( boardContent == ""  || boardContent == null || boardContent == '&nbsp;' || boardContent == '<br>' || boardContent == '<br />' || boardContent == '<p>&nbsp;</p>' || boardContent == '<p><br></p>')  {
	        alert("내용을 입력하세요.");
	        oEditors.getById["COMVISION"].exec("FOCUS"); //포커싱
	        return;
	   }
		insertBoard();
	}
	
	function makeFileAttachment(){
		 var maxFileNum = ${maxFile};
	
		 var multi_selector = new MultiSelector( document.getElementById('uploadFileList'), maxFileNum);
		 multi_selector.addElement(document.getElementById('egovComFileUploader'));
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
		<div class="table_v01">
			<table>
				<colgroup>
					<col style="width:150px">
					<col style="">
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td><input id="boardTitle" name="boardTitle" type="text" style="width:100%"></td>
					</tr>
					<tr>
						<th>알림톡</th>
						<td><input id="alarmYn" name="alarmYn" type="checkbox" value="Y"><i></i> <label for="">전송</label></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><c:out value="${userName}" /></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<div class="add_file_list">
								<ul id="uploadFileList">
								</ul>
								<!-- a href="javascript:;" class="btn btn-sm">삭제</a -->
							</div>
							<div class="add_file">
								<input type="file" name="file" id="egovComFileUploader" title="첨부파일" />
								<!-- a href="javascript:doUploadFileList();" class="btn btn-sm btn-info">업로드</a -->
							</div>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea id="boardContent" name="boardContent" rows="15"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	
	<div class="tbl_btm">
		<div class="f_right">
			<a href="javascript:valueCheck();" class="btn btn-lg btn-primary"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
			<a href="/setting/board/notice.do" class="btn btn-lg"><i class="fa fa-list-alt" aria-hidden="true"></i> 목록</a>
		</div>
	</div>
	
</div>
<!--+++++ /컨텐츠 +++++-->
<%@ include file="/WEB-INF/jsp/for/inc/_help.jsp" %>
</body>
</html>