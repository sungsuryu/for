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
			<li class="on"><a href="for_009.htm">공통코드 관리</a></li>
			<li><a href="for_010.htm">보고서 관리</a></li>
			<li><a href="for_011.htm">메뉴 관리</a></li>
			<li><a href="for_012.htm">그룹별 메뉴 관리</a></li>
			<li><a href="for_013.htm">팝업 관리</a></li>
			<li><a href="for_014.htm">게시판 관리</a></li>
			<li><a href="for_015.htm">한국은행 시스템 정보 관리</a></li>
		</ul>
	</div>
	
	<header class="contents_header">
		<h2>공통코드 관리</h2>
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
					<option>내정보 관리</option>
					<option>사용자 등록관리</option>					
					<option>통계 및 조회</option>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
			<span class="styled_select">
				<select>					
					<option>공통코드 관리</option>
					<option>보고서 관리</option>
					<option>메뉴 관리</option>					
					<option>그룹별 메뉴 관리</option>
					<option>팝업 관리</option>
					<option>게시판 관리</option>
					<option>한국은행 시스템 정보 관리</option>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
		</div>
	</header>
	
	<div class="clear layout_for009">
		<!-- 코드그룹 -->
		<section>
			<div class="top_btn right">
				<a href="javascript:;" class="btn btn-primary"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
				<a href="javascript:;" class="btn btn-info"><i class="fa fa-search" aria-hidden="true"></i> 조회</a>
			</div>
			<div class="basic_box">			
				<header class="basic_box_header">
					<h2>코드그룹</h2>
					<a href="javascript:;" class="btn btn-sm f_right">추가 <i class="fa fa-plus-circle" aria-hidden="true"></i></a>
				</header>
				<div class="basic_box_con">
					<div class="table_h01">
						<table>
							<colgroup>
								<col style="">
								<col style="">
								<col style="">
								<col style="width:70px">
								<col style="width:60px">
							</colgroup>
							<thead>
								<tr>
									<th>코드</th>
									<th>코드명</th>
									<th>코드설명</th>
									<th>사용여부</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
								<tr class="on">
									<td><strong>ORG_CD</strong></td>
									<td>회사코드</td>
									<td>회원사코드</td>
									<td>Y</td>
									<td><a href="javascript:;" class="btn btn-sm" title="삭제"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>
								</tr>
								<tr>
									<td><strong>STS_CD</strong></td>
									<td>보고서 상태코드</td>
									<td>보험서 진행상태</td>
									<td>Y</td>
									<td><a href="javascript:;" class="btn btn-sm" title="삭제"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>
								</tr>
								<tr>
									<td><strong>REP_CD</strong></td>
									<td>보고서코드</td>
									<td>보고서종류</td>
									<td>Y</td>
									<td><a href="javascript:;" class="btn btn-sm" title="삭제"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>
								</tr>
								<tr>
									<td><input type="text" placeholder="코드" style="width:100%"></td>
									<td><input type="text" placeholder="코드명" style="width:100%"></td>
									<td><input type="text" placeholder="코드설명" style="width:100%"></td>
									<td>
										<span class="styled_select" style="width:54px; min-width:inherit">
											<select>
												<option>Y</option>
												<option>N</option>
											</select>
											<i class="fa fa-sort" aria-hidden="true"></i>
										</span>
									</td>
									<td><a href="javascript:;" class="btn btn-sm" title="추가"><i class="fa fa-plus" aria-hidden="true"></i></a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</section>
		<!-- 코드목록 -->
		<section>
			<div class="top_btn right">
				<a href="javascript:;" class="btn btn-primary"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
				<a href="javascript:;" class="btn btn-info"><i class="fa fa-search" aria-hidden="true"></i> 조회</a>
			</div>
			<div class="basic_box">			
				<header class="basic_box_header">
					<h2>코드목록</h2>
					<a href="javascript:;" class="btn btn-sm f_right">추가 <i class="fa fa-plus-circle" aria-hidden="true"></i></a>
				</header>
				<div class="basic_box_con">
					<div class="table_h01">
						<table>
							<colgroup>
								<col style="">
								<col style="">
								<col style="">
								<col style="width:70px">
								<col style="width:60px">
								<col style="width:60px">
							</colgroup>
							<thead>
								<tr>
									<th>코드</th>
									<th>코드명</th>
									<th>설명</th>
									<th>사용여부</th>
									<th>순서</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><strong>N01</strong></td>
									<td>메리츠화재</td>
									<td>4301</td>
									<td>Y</td>
									<td>1</td>
									<td><a href="javascript:;" class="btn btn-sm" title="삭제"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>
								</tr>
								<tr>
									<td><strong>N02</strong></td>
									<td>한화손보</td>
									<td>4302</td>
									<td>Y</td>
									<td>2</td>
									<td><a href="javascript:;" class="btn btn-sm" title="삭제"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>
								</tr>
								<tr>
									<td><strong>N03</strong></td>
									<td>롯데손보</td>
									<td>4303</td>
									<td>Y</td>
									<td>3</td>
									<td><a href="javascript:;" class="btn btn-sm" title="삭제"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>
								</tr>
								<tr>
									<td><input type="text" placeholder="코드" style="width:100%"></td>
									<td><input type="text" placeholder="코드명" style="width:100%"></td>
									<td><input type="text" placeholder="설명" style="width:100%"></td>
									<td>
										<span class="styled_select" style="width:54px; min-width:inherit">
											<select>
												<option>Y</option>
												<option>N</option>
											</select>
											<i class="fa fa-sort" aria-hidden="true"></i>
										</span>
									</td>
									<td>
										<span class="styled_select" style="width:50px; min-width:inherit">
											<select>
												<option>1</option>
												<option>2</option>
											</select>
											<i class="fa fa-sort" aria-hidden="true"></i>
										</span>
									</td>
									<td><a href="javascript:;" class="btn btn-sm" title="추가"><i class="fa fa-plus" aria-hidden="true"></i></a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</section>
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