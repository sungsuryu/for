<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ include file="/WEB-INF/jsp/for/inc/_header.jsp" %>
<script type="text/javascript" src="<c:url value='/js/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/smartEditor/js/service/HuskyEZCreator.js'/>" ></script>
<script type="text/javascript">

	var multi_selector;
	var deleteOriginFileId;
	var editors = [];
	$(document).ready(function() {
		makeFileAttachment();
		nhn.husky.EZCreator.createInIFrame({
	 		oAppRef: editors,
	 		elPlaceHolder: 'boardContent',
	 		sSkinURI: "/smartEditor/SmartEditor2Skin.html",
	 		fOnAppLoad : function(){
	            //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
	            editors.getById["boardContent"].exec("PASTE_HTML", ['${boardVO.boardContent}']);
	        },
	 		fCreator: "createSEditor2"
	 	});
	});
	function updateBoard(){
		$("#boardForm").attr("action", "/setting/board/pdsEditAction.do");
		$("#boardForm").submit();
	}
	
	function deleteBoard(){
		$("#boardForm").attr("action", "/setting/board/pdsDeleteAction.do");
		$("#boardForm").submit();
	}
	
	function getUploadableNum(){
		var existFileNum = ${fileCnt};
		var maxFileNum = ${maxFile};
	
		if (existFileNum=="undefined" || existFileNum ==null) {
			existFileNum = 0;
		}
		if (maxFileNum=="undefined" || maxFileNum ==null) {
			maxFileNum = 0;
		}
		var uploadableFileNum = maxFileNum - existFileNum;
		if (uploadableFileNum<0) {
			uploadableFileNum = 0;
		}
		return uploadableFileNum;
	}
	
	function makeFileAttachment(){
		var uploadableFileNum = getUploadableNum();
		multi_selector = new MultiSelector( document.getElementById( 'uploadFileList' ), uploadableFileNum );
		multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
	}
	
	function deleteFileList(e, index, fileId) {
		multi_selector.addMax();
	
		$("#boardForm").prepend('<input id="deleteOriginFileId" name="deleteOriginFileId" type="hidden" value="' + fileId + '">');
		$("#originFileList" + index).remove();
		
		var fileEl = $(".add_file").children().first();
		$(fileEl).prop("disabled", false);
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
		updateBoard();
	}
	function goPds(){
		$("#boardForm").attr("action", "/setting/board/pds.do");
		$("#boardForm").submit();
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
			<li><a href="/setting/board/notice.do">공지사항</a></li>
			<li class="on"><a href="/setting/board/pds.do">자료실</a></li>
			<li><a href="/setting/board/faq.do">FAQ</a></li>
		</ul>
	</div>
	<form id="boardForm" name="boardForm" method="post" enctype="multipart/form-data">
		<input id="boardIdx" name="boardIdx" type="hidden" value="<c:out value="${boardVO.boardIdx}" />">
		<input id="page" name="page" type="hidden" value="<c:out value="${boardVO.page}" />">
		<input id="isOriginFile" name="isOriginFile" type="hidden" value="<c:out value="${isOriginFile}" />">
		<input type='hidden' id="board_alarm" name='board_alarm'>
		<div class="table_v01">
			<table>
				<colgroup>
					<col style="width:150px">
					<col style="">
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td><input id="boardTitle" name="boardTitle" type="text" style="width:100%" value="<c:out value="${boardVO.boardTitle}" />"></td>
					</tr>
					<tr>
						<th>알림톡</th>
						<td><input id="alarmYn" name="alarmYn" type="checkbox" value="Y"><i></i><label for="">전송</label></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><c:out value="${userName}" /></td>
					</tr>
					<tr>
						<th>첨부파일 <!--a href="javascript:;" title="추가" style="margin-left:5px"><i class="fa fa-plus-circle" aria-hidden="true"></i></a--></th>
						<td>
				        	<div class="add_file_list">
				        		<ul id="uploadFileList">
				        		<c:forEach var="result" items="${fileList}" varStatus="status">
									<li id="originFileList${status.index}" ondblclick="deleteFileList(this, '${status.index}', '${result.fileId}')">
										<c:out value="${result.phyFileNm}"/>
									</li>
								</c:forEach>
				        		</ul>
				        	</div>
				        	<div class="add_file">
				        		<input name="file_1" id="egovComFileUploader" type="file" title="첨부파일명 입력"/>
				        	</div>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea id="boardContent" name="boardContent" rows="15">
								
							</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	
	<div class="tbl_btm">
		<div class="f_right">
			<a href="javascript:valueCheck();" class="btn btn-lg btn-primary"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
			<a href="javascript:deleteBoard();" class="btn btn-lg btn-red"><i class="fa fa-trash-o" aria-hidden="true"></i> 삭제</a>
			<a href="javascript:goPds()" class="btn btn-lg"><i class="fa fa-list-alt" aria-hidden="true"></i> 목록</a>
		</div>
	</div>
	
</div>
<!--+++++ /컨텐츠 +++++-->
<%@ include file="/WEB-INF/jsp/for/inc/_help.jsp" %>
</body>
</html>