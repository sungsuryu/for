<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%
	request.setCharacterEncoding("utf-8");
	Map<String, Object> dataMap = (Map) request.getAttribute("dataMap");
	String board_title = dataMap.get("board_title").toString();
	String board_content = dataMap.get("board_content").toString();
	String board_usernm = dataMap.get("board_usernm").toString();
	String board_idx = dataMap.get("board_idx").toString();

%>
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
<link rel="stylesheet" type="text/css" href="css/design.css" />
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/design.js"></script>
</head>
<script>
$(document).ready(function() {
});

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
	<form id="boardForm" name="boardForm" method="post">
		<input id="board_idx" name="board_idx" type="hidden"/>
		<div class="table_v01">
			<table>
				<colgroup>
					<col style="width:150px">
					<col style="">
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td><input id="board_title" name="board_title" type="text" style="width:100%"></td>
					</tr>
					<tr>
						<th>알림톡</th>
						<td><input id="board_alarm" name="board_alarm" type="checkbox"><i></i> <label for="">전송</label></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input id="board_usernm" name="board_usernm" type="text"></td>
					</tr>
					<tr>
						<th>첨부파일 <!--a href="javascript:;" title="추가" style="margin-left:5px"><i class="fa fa-plus-circle" aria-hidden="true"></i></a--></th>
						<td>
							<div class="add_file_list">
								<ul>
									<!-- 선택한 파일 addClass="on" -->
									<li class="on">업로드파일명.doc</li>
									<li>업로드파일명.doc</li>
									<li>업로드파일명.doc</li>
									<li>업로드파일명.doc</li>
									<li>업로드파일명.doc</li>
									<li>업로드파일명.doc</li>
									<li>업로드파일명.doc</li>
									<li>업로드파일명.doc</li>
									<li>업로드파일명.doc</li>
								</ul>
								<a href="javascript:;" class="btn btn-sm">삭제</a>
							</div>
							<div class="add_file">
								<input type="file">
								<a href="javascript:;" class="btn btn-sm btn-info">업로드</a>
							</div>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea id="board_content" name="board_content" rows="15"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	
	<div class="tbl_btm">
		<div class="f_right">
			<a href="javascript:updateBoard();" class="btn btn-lg btn-primary"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
			<a href="javascript:deleteBoard();" class="btn btn-lg btn-red"><i class="fa fa-trash-o" aria-hidden="true"></i> 삭제</a>
			<a href="javascript:history.back();" class="btn btn-lg"><i class="fa fa-list-alt" aria-hidden="true"></i> 목록</a>
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
function setView(){
	$("#board_idx").val(<%=board_idx%>);
	$("#board_title").val(<%=board_title%>);
	$("#board_content").val(<%=board_content%>);
	$("#board_usernm").val(<%=board_usernm%>);
}
function updateBoard(){
	var formdata = $("#boardForm").serialize();
	if(!$("#board_alarm").is(':checked')){
		formdata.push({
			name : $("#board_alarm").attr('name'),
			value : "N"
		});
	}
	$.ajax({
        type:"POST",
        url:"/setting/boardUpdate.ajax",
        cache:false,
        data:formdata,
        success: function(e){
            if (e.result.status == 'SUCCESS') {
            	console.log(e.result);
            	history.back();
            }
            else {
            	alert("공지사항을 불러올 수  없습니다.");
            }
        },
        error: function(xhr, status, error) {
            alert(error);
        }, 
        complete : function() {
        	console.log('complete');
        }
    });
}

function deleteBoard(){
	var formdata = $("#boardForm").serialize();
	$.ajax({
        type:"POST",
        url:"/setting/boardDelete.ajax",
        cache:false,
        data:formdata,
        success: function(e){
            if (e.result.status == 'SUCCESS') {
            	console.log(e.result);
            	history.back();
            }
            else {
            	alert("공지사항을 불러올 수  없습니다.");
            }
        },
        error: function(xhr, status, error) {
            alert(error);
        }, 
        complete : function() {
        	console.log('complete');
        }
    });
}

</script>

</body>
</html>