<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
		$.ajax({
	        type:"POST",
	        url:"/setting/noticelist.ajax",
	        success: function(e){
	            if (e.result.status == 'SUCCESS') {
	            	alert("공지사항을 불러왔습니다");
	            	console.log(e.result);
	            	$("#notice_list").empty();
	            	 $.each(e.result.notice_list, function (i, item) {
	            		 $("#notice_list").append('<tr><td>1</td><td class="left"><a href="for_014_write.htm">제목이 들어갑니다.</a></td><td><a href="javascript:;" class="link01">abc.doc</a></td><td>관리자</td><td>2021-01-11</td><td>999</td></tr>');
	                 });
	            }
	            else if(e.result.status == 'EMPTY'){
	            	alert("공지사항 목록이  없습니다.");
	            	$("#notice_list").empty();
	            } 
	            else {
	            	alert("공지사항을 불러올 수  없습니다.");
	            	$("#notice_list").empty();
	            	
	            }
	        },
	        error: function(xhr, status, error) {
	            alert(error);
	        }, 
	        complete : function() {
	        	console.log('complete');
	        }
	    });
	});
	
	var getBoardList = function(pagenum) {
		$.ajax({
	        type:"POST",
	        url:"/setting/ajax/noticelist.do",
	        data: {"page_num": 1 },
	        success: function(e){
	            if (e.result.status == 'SUCCESS') {
	            	alert("공지사항을 불러왔습니다");
	            	console.log(result);
	            } else {
	            	alert("공지사항을 불러울 수  없습니다.");
	            }
	        },
	        error: function(xhr, status, error) {
	            alert(error);
	        }, 
	        complete : function() {
	        	console.log('complete');
	        }
	    });
	};
	
	
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
					<li><a href="for_007.htm">사용자 등록관리</a></li>
					<li><a href="for_008.htm">내정보 관리</a></li>
					<li><a href="for_009.htm">시스템 설정관리</a></li>
					<li><a href="for_017.htm">통계 및 조회</a></li>
				</ul>				
			</li>
			<li class="depth1">
				<a href="javascript:;">게시판</a>
				<ul>
					<li><a href="for_020.htm">FAQ</a></li>
					<li><a href="for_021.htm">공지사항</a></li>
					<li><a href="for_022.htm">보고관련 자료실</a></li>
				</ul>				
			</li>
			<li class="depth1">
				<a href="for_023.htm">보고서 전송 및 결과확인</a>
			</li>
			<li class="depth1">
				<a href="javascript:;">조회</a>
				<ul>
					<li><a href="for_024.htm">보고서 입수현황 점검표</a></li>
					<li><a href="for_025.htm">주요계수현황</a></li>
					<li><a href="for_026.htm">차액상세표</a></li>
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
					<li><a href="for_007.htm">사용자 등록관리</a></li>
					<li><a href="for_008.htm">내정보 관리</a></li>
					<li class="on"><a href="for_009.htm">시스템 설정관리</a></li>
					<li><a href="for_017.htm">통계 및 조회</a></li>
				</ul>				
			</li>
			<li class="depth1">
				<a href="javascript:;">게시판</a>
				<ul>
					<li><a href="for_020.htm">FAQ</a></li>
					<li><a href="for_021.htm">공지사항</a></li>
					<li><a href="for_022.htm">보고관련 자료실</a></li>
				</ul>				
			</li>
			<li class="depth1">
				<a href="for_023.htm">보고서</a>
			</li>
			<li class="depth1">
				<a href="javascript:;">조회</a>
				<ul>
					<li><a href="for_024.htm">보고서 입수현황 점검표</a></li>
					<li><a href="for_025.htm">주요계수현황</a></li>
					<li><a href="for_026.htm">차액상세표</a></li>
				</ul>				
			</li>
		</ul>
	</nav>
</aside>
<!--+++++ /좌측 레이어 +++++-->

<!--+++++ 컨텐츠 +++++-->
<div id="contents">
	
	<div class="tab_menu col7">
		<ul>
			<!-- 현재 메뉴 addClass="on" -->
			<li><a href="for_009.htm">공통코드 관리</a></li>
			<li><a href="for_010.htm">보고서 관리</a></li>
			<li><a href="for_011.htm">메뉴 관리</a></li>
			<li><a href="for_012.htm">그룹별 메뉴 관리</a></li>
			<li><a href="for_013.htm">팝업 관리</a></li>
			<li class="on"><a href="for_014.htm">게시판 관리</a></li>
			<li><a href="for_015.htm">한국은행 시스템 정보 관리</a></li>
		</ul>
	</div>
	
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
	
	<div class="tbl_top">
		<p class="result"><i class="fa fa-check-circle" aria-hidden="true"></i> 조회건 수 - 총 <strong>10</strong>건</p>
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
			<tbody id="notice_list">
				<tr>
					<td>1</td>
					<td class="left"><a href="for_014_write.htm">제목이 들어갑니다.</a></td>
					<td><a href="javascript:;" class="link01">abc.doc</a></td>
					<td>관리자</td>
					<td>2021-01-11</td>
					<td>999</td>
				</tr>
				<tr>
					<td>2</td>
					<td class="left"><a href="for_014_write.htm">제목이 들어갑니다.</a></td>
					<td><a href="javascript:;" class="link01">abc.doc</a></td>
					<td>관리자</td>
					<td>2021-01-11</td>
					<td>999</td>
				</tr>
				<tr>
					<td>3</td>
					<td class="left"><a href="for_014_write.htm">제목이 들어갑니다.</a></td>
					<td><a href="javascript:;" class="link01">abc.doc</a></td>
					<td>관리자</td>
					<td>2021-01-11</td>
					<td>999</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="tbl_btm">
		<div class="f_left">
			<div class="pagenum">
				<a href="javascript:" title="prev"><i class="fa fa-angle-double-left" aria-hidden="true"></i></a>
				<a href="javascript:" title="prev"><i class="fa fa-angle-left" aria-hidden="true"></i></a>
				<!-- 현재위치 addClass="on" -->
				<a href="javascript:" class="on">1</a>
				<a href="javascript:">2</a>
				<a href="javascript:">3</a>
				<a href="javascript:">4</a>
				<a href="javascript:">5</a>
				<a href="javascript:">6</a>
				<a href="javascript:">7</a>
				<a href="javascript:">8</a>
				<a href="javascript:">9</a>
				<a href="javascript:" title="next"><i class="fa fa-angle-right" aria-hidden="true"></i></a>
				<a href="javascript:" title="prev"><i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="f_right">
			<!-- <a href="for_014_write.htm" class="btn btn-lg btn-primary"><i class="fa fa-pencil" aria-hidden="true"></i> 글쓰기</a> -->
			<a href="#" class="btn btn-lg btn-primary" onclick="javascript:searchList();"><i class="fa fa-pencil" aria-hidden="true"></i> 글쓰기</a>
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
	//getBoardList();
});

function getBoardList(){
	let formData = new FormData(document.getElementById('uploadForm'));
	$.ajax({
		url: "/xxx/aaa/upload", //컨트롤러 URL
        data: formData,
        dataType: 'json',
        processData: false, // 비동기 파일 업로드시 꼭 설정해줘야 하는 속성
        contentType: false, // 비동기 파일 업로드시 꼭 설정해줘야 하는 속성
        type: 'POST',
        success: function (res) {
        	alert("success");
        },error: function (xhr) {
            alert(xhr.responseText);
        } 
    });
}
</script>

</body>
</html>