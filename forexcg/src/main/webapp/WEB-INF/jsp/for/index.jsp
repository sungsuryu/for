<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!DOCTYPE html>
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
</head>

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
			<li class="depth1">
				<a href="javascript:;">공통관리</a>
				<ul>
					<!-- 현재 메뉴 addClass="on" -->
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
<div id="contents" class="main">
	
	<div class="clear">
		<div class="basic_box">
			<header class="basic_box_header">
				<h2>미입수율 TOP5 (2021.01)</h2>
			</header>
			<div class="basic_box_con">
				<div class="table_h01">
					<table>
						<colgroup>
							<col style="width:80px">
							<col style="">
							<col style="width:120px">
						</colgroup>
						<thead>
							<tr>
								<th>순번</th>
								<th>회사명</th>
								<th>미입수율(%)</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>OO화재</td>
								<td><strong>5.5</strong></td>
							</tr>
							<tr>
								<td>2</td>
								<td>OO화재</td>
								<td><strong>4.5</strong></td>
							</tr>
							<tr>
								<td>3</td>
								<td>OO화재</td>
								<td><strong>3.5</strong></td>
							</tr>
							<tr>
								<td>4</td>
								<td>OO화재</td>
								<td><strong>2.5</strong></td>
							</tr>
							<tr>
								<td>5</td>
								<td>OO화재</td>
								<td><strong>1.5</strong></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="basic_box">
			<header class="basic_box_header">
				<h2>미입수율 TOP5 (2021.02)</h2>
			</header>
			<div class="basic_box_con">
				<div class="table_h01">
					<table>
						<colgroup>
							<col style="width:80px">
							<col style="">
							<col style="width:120px">
						</colgroup>
						<thead>
							<tr>
								<th>순번</th>
								<th>회사명</th>
								<th>미입수율(%)</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>OO화재</td>
								<td><strong>5.5</strong></td>
							</tr>
							<tr>
								<td>2</td>
								<td>OO화재</td>
								<td><strong>4.5</strong></td>
							</tr>
							<tr>
								<td>3</td>
								<td>OO화재</td>
								<td><strong>3.5</strong></td>
							</tr>
							<tr>
								<td>4</td>
								<td>OO화재</td>
								<td><strong>2.5</strong></td>
							</tr>
							<tr>
								<td>5</td>
								<td>OO화재</td>
								<td><strong>1.5</strong></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="clear clear02">
		<div class="basic_box">
			<header class="basic_box_header">
				<h2>회원(신규/변경)신청</h2>
			</header>
			<div class="basic_box_con">
				<div class="table_h01">
					<table>
						<colgroup>
							<col style="">
							<col style="">
							<col style="width:120px">
						</colgroup>
						<thead>
							<tr>
								<th>회사명</th>
								<th>신청구분</th>
								<th>신청일자</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_blue">신규</strong></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_blue">신규</strong></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_brown">변경</strong></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_brown">변경</strong></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_blue">신규</strong></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_blue">신규</strong></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_blue">신규</strong></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_blue">신규</strong></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_blue">신규</strong></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_blue">신규</strong></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_blue">신규</strong></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_blue">신규</strong></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_blue">신규</strong></td>
								<td>2021.1.1</td>
							</tr><tr>
								<td>OO화재</td>
								<td><strong class="txt_blue">신규</strong></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>OO화재</td>
								<td><strong class="txt_blue">신규</strong></td>
								<td>2021.1.1</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="basic_box">
			<header class="basic_box_header">
				<h2>공지사항</h2>
			</header>
			<div class="basic_box_con">
				<div class="table_h01">
					<table>
						<colgroup>
							<col style="width:80px">
							<col style="">
							<col style="width:120px">
						</colgroup>
						<thead>
							<tr>
								<th>순번</th>
								<th>제목</th>
								<th>게시일자</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>2</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>3</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>4</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>5</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>0</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>0</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>0</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>0</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>0</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>0</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>0</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>0</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>0</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
							<tr>
								<td>0</td>
								<td class="left"><a href="#">공지사항 제목이 들어갑니다.</a></td>
								<td>2021.1.1</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
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