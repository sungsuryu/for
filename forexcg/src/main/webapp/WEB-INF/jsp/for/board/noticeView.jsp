<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ include file="/WEB-INF/jsp/for/inc/_header.jsp" %>
</head>
<script>
var editors = [];
$(document).ready(function() {
});

function fn_egov_downFile(fileId){
	window.open("<c:url value='/board/downloadFile.do?fileId="+fileId+"'/>");
	//location.href = "/board/downloadFile.do?fileId=" + fileId;
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
			<li class="depth1">
				<a href="javascript:;">공통관리</a>
				<ul>
					<!-- 현재 메뉴 addClass="on" -->
					<li class="depth2"><a href="for_007.htm">사용자 등록관리</a></li>
					<li class="depth2"><a href="for_008.htm">내정보 관리</a></li>
					<li class="depth2"><a href="javascript:;">시스템 설정관리</a>
						<ul>
							<!-- 현재 메뉴 addClass="on" -->
							<li class="depth3"><a href="for_009.htm">공통코드 관리</a></li>
							<li class="depth3"><a href="for_010.htm">보고서 관리</a></li>
							<li class="depth3"><a href="for_011.htm">메뉴 관리</a></li>
							<li class="depth3"><a href="for_012.htm">그룹별 메뉴 관리</a></li>
							<li class="depth3"><a href="for_013.htm">팝업 관리</a></li>
							<li class="depth3"><a href="for_014.htm">게시판 관리</a></li>
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
			<li class="depth1 on">
				<a href="javascript:;">게시판</a>
				<ul>
					<li class="depth2"><a href="for_020.htm">FAQ</a></li>
					<li class="depth2 on"><a href="for_021.htm">공지사항</a></li>
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
	
	<header class="contents_header">
		<h2>공지사항</h2>
		<div class="navi">
			<a href="main.htm" class="btn_home" title="홈"><i class="fa fa-home" aria-hidden="true"></i></a>
			<span class="styled_select">
				<select>
					<option>게시판</option>
					<option>공통관리</option>					
					<option>보고서 전송 및 결과확인</option>
					<option>조회</option>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
			<span class="styled_select">
				<select>
					<option>공지사항</option>
					<option>FAQ</option>
					<option>보고관련 자료실</option>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
		</div>
	</header>
	
	<div class="table_v01">
		<table>
			<colgroup>
				<col style="width:150px">
				<col style="">
			</colgroup>
			<tbody>
				<tr>
					<th>제목</th>
					<td><c:out value="${boardVO.boardTitle}" /></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><c:out value="${boardVO.userName}" /></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<c:forEach var="result" items="${fileList}" varStatus="status">
							<a href="javascript:fn_egov_downFile('<c:out value="${result.fileId}"/>')" class="link01">
								<c:out value="${result.phyFileNm}"/>&nbsp;[<c:out value="${result.fileSize}"/>&nbsp;byte]</br>
							</a>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<div>
							<c:out value="${boardVO.boardContent}" escapeXml="false" />
						<div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="tbl_btm">
		<div class="f_right">
			<a href="/board/notice.do" class="btn btn-lg"><i class="fa fa-list-alt" aria-hidden="true"></i> 목록</a>
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