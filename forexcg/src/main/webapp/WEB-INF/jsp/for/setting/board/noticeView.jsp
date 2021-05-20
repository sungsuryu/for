<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=0,maximum-scale=10,user-scalable=yes">
<meta name="HandheldFriendly" content="true">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="imagetoolbar" content="no">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>손해보험협회 외환정보시스템</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/design.css'/>" />
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.4.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/design.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/smartEditor/js/service/HuskyEZCreator.js'/>" ></script>
</head>
<script>
var editors = [];
$(document).ready(function() {
	//fileDropDown();
	makeFileAttachment();
	nhn.husky.EZCreator.createInIFrame({
 		oAppRef: editors,
 		elPlaceHolder: 'board_content',
 		sSkinURI: "/smartEditor/SmartEditor2Skin.html",
 		fOnAppLoad : function(){
            //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
            editors.getById["board_content"].exec("PASTE_HTML", ['<c:out value="${board_content}"/>']);
            editors.exec("DISABLE_WYSIWYG");
            editors.exec("DISABLE_ALL_UI");
        },
 		fCreator: "createSEditor2"
 	});
});

function fn_egov_downFile(fileId){
	//window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
	location.href = "/board/downloadFile.do?fileId=" + fileId;
}

function insertBoard(){
	if(!$("#board_alarmYn").is(':checked')){
		$("#board_alarm").val("N");
	}
	else{
		$("#board_alarm").val("Y");
	}
	$("#boardForm").attr("action", "/setting/board/noticeWriteAction.ajax");
	$("#boardForm").submit();
}

function valueCheck(){
	editors.getById["board_content"].exec("UPDATE_CONTENTS_FIELD", []);
	var boardTitle = $("#board_title").val();
	var boardUserNm = $("#board_usernm").val();
	var boardContent = $("#board_content").val();
	if(boardTitle == "" || boardTitle == null){
		alert("제목을 적어주세요.");
		return
	}
	if(boardUserNm == "" || boardUserNm == null){
		alert("작성자를 적어주세요.");
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
	 var maxFileNum = 10;

	 var multi_selector = new MultiSelector( document.getElementById('uploadFileList'), maxFileNum );
	 multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
}

</script>
<body>

<div id="popLayerBg"></div>

<!--+++++ 상단 +++++-->
<header id="header">
	<nav class="gnb">
		<ul>
			<li class="depth1">
				<a href="javascript:;">공통관리</a>
				<ul>
					<li class="depth2"><a href="for_007.htm">사용자 등록관리</a></li>
					<li class="depth2"><a href="for_008.htm">내정보 관리</a></li>
					<li class="depth2"><a href="for_009.htm">시스템 설정관리</a>
						<ul>
							<li class="depth3"><a href="for_009.htm">공통코드 관리</a></li>
							<li class="depth3"><a href="for_010.htm">보고서 관리</a></li>
							<li class="depth3"><a href="for_011.htm">메뉴 관리</a></li>
							<li class="depth3"><a href="for_012.htm">그룹별 메뉴 관리</a></li>
							<li class="depth3"><a href="for_013.htm">팝업 관리</a></li>
							<li class="depth3"><a href="for_014.htm">게시판 관리</a></li>
							<li class="depth3"><a href="for_015.htm">한국은행 시스템 정보 관리</a></li>
						</ul>
					</li>
					<li class="depth2"><a href="for_017.htm">통계 및 조회</a>
						<ul>
							<li class="depth3"><a href="for_017.htm">사용자 알림 조회</a></li>
							<li class="depth3"><a href="for_018.htm">시스템 사용내역 조회</a></li>
							<li class="depth3"><a href="for_019.htm">일괄배치 실행내역 조회</a></li>
						</ul>
					</li>
				</ul>				
			</li>
			<li class="depth1">
				<a href="javascript:;">게시판</a>
				<ul>
					<li class="depth2"><a href="for_020.htm">FAQ</a></li>
					<li class="depth2"><a href="for_021.htm">공지사항</a></li>
					<li class="depth2"><a href="for_022.htm">보고관련 자료실</a></li>
				</ul>				
			</li>
			<li class="depth1">
				<a href="for_023.htm">보고서 전송 및 결과확인</a>
			</li>
			<li class="depth1">
				<a href="javascript:;">조회</a>
				<ul>
					<li class="depth2"><a href="for_024.htm">보고서 입수현황 점검표</a></li>
					<li class="depth2"><a href="for_025.htm">주요계수현황</a></li>
					<li class="depth2"><a href="for_026.htm">차액상세표</a></li>
				</ul>				
			</li>
		</ul>
	</nav>
	<div class="top_con">
		<a href="javascript:" class="btn_sh" title="상단메뉴 열기/닫기">
			<i class="fa fa-angle-down" aria-hidden="true"></i>
			<i class="fa fa-angle-up" aria-hidden="true"></i>
		</a>
		<div class="page_area">
			<a href="javascript:;" class="btn_move btn_prev" title="이전"><i class="fa fa-angle-left" aria-hidden="true"></i></a>
			<a href="javascript:;" class="btn_move btn_next" title="다음"><i class="fa fa-angle-right" aria-hidden="true"></i></a>
			<!-- 활성화 된 페이지 addClass="on" -->
			<div class="page_unit on">
				<h3>페이지명1</h3>
				<a href="javascript:;" class="btn_page_close" title="페이지 닫기"><i class="fa fa-times-circle" aria-hidden="true"></i></a>
			</div>
			<div class="page_unit">
				<h3>페이지명2</h3>
				<a href="javascript:;" class="btn_page_close" title="페이지 닫기"><i class="fa fa-times-circle" aria-hidden="true"></i></a>
			</div>
			<div class="page_unit">
				<h3>페이지명3</h3>
				<a href="javascript:;" class="btn_page_close" title="페이지 닫기"><i class="fa fa-times-circle" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="info_area">
			<div class="user"><i class="fa fa-user-o" aria-hidden="true"></i> 관리자 <a href="javascript:;" class="btn btn-xs">정보수정</a></div>
			<div class="timer"><i class="fa fa-clock-o" aria-hidden="true"></i> 남은시간 : 54분 41초 <a href="javascript:;" class="btn btn-xs">시간연장</a></div>
			<a href="javascript:;" class="btn btn-primary btn_help"><i class="fa fa-question-circle" aria-hidden="true"></i> 도움말</a>
			<a href="javascript:;" class="btn btn-info"><i class="fa fa-sign-out" aria-hidden="true"></i> 로그아웃</a>
		</div>		
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
	<nav class="gnb">
		<ul>
			<!-- 현재 메뉴 addClass="on" -->
			<li class="depth1 on">
				<a href="javascript:;">공통관리</a>
				<ul>
					<!-- 현재 메뉴 addClass="on" -->
					<li class="depth2"><a href="for_007.htm">사용자 등록관리</a></li>
					<li class="depth2"><a href="for_008.htm">내정보 관리</a></li>
					<li class="depth2 on"><a href="javascript:;">시스템 설정관리</a>
						<ul>
							<!-- 현재 메뉴 addClass="on" -->
							<li class="depth3"><a href="for_009.htm">공통코드 관리</a></li>
							<li class="depth3"><a href="for_010.htm">보고서 관리</a></li>
							<li class="depth3"><a href="for_011.htm">메뉴 관리</a></li>
							<li class="depth3"><a href="for_012.htm">그룹별 메뉴 관리</a></li>
							<li class="depth3"><a href="for_013.htm">팝업 관리</a></li>
							<li class="depth3 on"><a href="for_014.htm">게시판 관리</a></li>
							<li class="depth3"><a href="for_015.htm">한국은행 시스템 정보 관리</a></li>
						</ul>
					</li>
					<li class="depth2"><a href="javascript:;">통계 및 조회</a>
						<ul>
							<!-- 현재 메뉴 addClass="on" -->
							<li class="depth3"><a href="for_017.htm">사용자 알림 조회</a></li>
							<li class="depth3"><a href="for_018.htm">시스템 사용내역 조회</a></li>
							<li class="depth3"><a href="for_019.htm">일괄배치 실행내역 조회</a></li>
						</ul>
					</li>
				</ul>				
			</li>
			<li class="depth1">
				<a href="javascript:;">게시판</a>
				<ul>
					<li class="depth2"><a href="for_020.htm">FAQ</a></li>
					<li class="depth2"><a href="for_021.htm">공지사항</a></li>
					<li class="depth2"><a href="for_022.htm">보고관련 자료실</a></li>
				</ul>				
			</li>
			<li class="depth1">
				<a href="for_023.htm">보고서</a>
			</li>
			<li class="depth1">
				<a href="javascript:;">조회</a>
				<ul>
					<li class="depth2"><a href="for_024.htm">보고서 입수현황 점검표</a></li>
					<li class="depth2"><a href="for_025.htm">주요계수현황</a></li>
					<li class="depth2"><a href="for_026.htm">차액상세표</a></li>
				</ul>				
			</li>
		</ul>
	</nav>
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
		<div class="navi">
			<a href="main.htm" class="btn_home" title="홈"><i class="fa fa-home" aria-hidden="true"></i></a>
			<span class="styled_select">
				<select>
					<option>공통관리</option>
					<option>게시판</option>
					<option>보고서 전송 및 결과확인</option>
					<option>조회</option>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
			<span class="styled_select">
				<select>
					<option>시스템 설정관리</option>
					<option>사용자 등록관리</option>
					<option>내정보 관리</option>					
					<option>통계 및 조회</option>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
			<span class="styled_select">
				<select>					
					<option>게시판 관리</option>											
					<option>공통코드 관리</option>
					<option>보고서 관리</option>
					<option>메뉴 관리</option>	
					<option>그룹별 메뉴 관리</option>		
					<option>팝업 관리</option>		
					<option>한국은행 시스템 정보 관리</option>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
		</div>
	</header>
	
	<div class="catg_area">
		<ul>
			<li class="on"><a href="javascript:;">공지사항</a></li>
			<li><a href="javascript:;">자료실</a></li>
			<li><a href="for_014_faq.htm">FAQ</a></li>
		</ul>
	</div>
	<form id="boardForm" name="boardForm" method="post" enctype="multipart/form-data">
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
						<td><c:out value="${board_title}" /></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><c:out value="${board_usernm}" /></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<c:forEach var="result" items="${fileList}" varStatus="status">
								<a href="javascript:fn_egov_downFile('<c:out value="${result.fileId}"/>')">
									<c:out value="${result.phyFileNm}"/>&nbsp;[<c:out value="${result.fileSize}"/>&nbsp;byte]
								</a>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea id="board_content" name="board_content" rows="15">
								
							</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	
	<div class="tbl_btm">
		<div class="f_right">
			<a href="javascript:valueCheck();" class="btn btn-lg btn-primary"><i class="fa fa-check-circle" aria-hidden="true"></i> 수정</a>
			<a href="/setting/board/notice.do" class="btn btn-lg"><i class="fa fa-list-alt" aria-hidden="true"></i> 목록</a>
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