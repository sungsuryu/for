<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/for/inc/_header.jsp" %>
<script type="text/javascript">

	$(function(){
	});
	//페이지 전환 함수
	function fn_egov_link_page(page){
		$("#page").val(page);
		$("#boardForm").attr("action", "/setting/board/notice.do");
		$("#boardForm").submit();
	}
	function goBoardView(boardIdx){
		$("#boardIdx").val(boardIdx);
		$("#boardForm").attr("action", "/setting/board/noticeView.do");
		$("#boardForm").submit();
	}
	
	function doDownloadZip(boardIdx){
		$("#boardIdx").val(boardIdx);
		$("#boardForm").attr("action", "/board/downloadZipFile.do");
		$("#boardForm").submit();
	}
	
	function selectGuide(){
		var formData = $("#guideForm").serialize();
		$.ajax({
	        type:"POST",
	        url:"/help/guide.ajax",
	        data : formData,
	        success: function(data){
	        	if(data.result.status == "SUCCESS"){
	        		if(data.result.levelCheck == "N"){
	        			$("#updateGuide").css("display", "none");
	        			$("#guideContent").attr("readonly", true);
	        			$("#guideContent").attr("disable", true);
	        		}
	        		if(data.result.isYn == "Y"){
	        			$("#guideContent").val(data.result.guideVO.guideContent);
	        		}
	        	}
	        	else{
	        		alert("도움말 불러오기 실패");
	        	}
	        },
			error: function(e){
				alert("도움말 불러오기 실패");
			}			
    	});
	}
	
	function updateGuide(){
		var formData = $("#guideForm").serialize();
		$.ajax({
	        type:"POST",
	        url:"/help/updateGuide.ajax",
	        data : formData,
	        success: function(data){
	        },
			error: function(e){
				alert("도움말 수정 실패");
			}			
    	});
	}
	
</script>
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
			<a href="javascript:selectGuide();" class="btn btn-primary btn_help"><i class="fa fa-question-circle" aria-hidden="true"></i> 도움말</a>
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
	
	<div class="tbl_top">
		<p class="result"><i class="fa fa-check-circle" aria-hidden="true"></i> 조회건 수 - 총 <strong><c:out value="${total_cnt}" /></strong>건</p>
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
			<tbody>
				<c:forEach var="result" items="${boardList}" varStatus="status">
				<tr>
					<td><c:out value="${result.listNum}" /></td>
					<td class="left"><a href="javascript:goBoardView(<c:out value="${result.boardIdx}" />)"><c:out value="${result.boardTitle}" /></a></td>
					<td>
						<c:if test = "${result.fileCnt > 0}">
							<a href="javascript:doDownloadZip(<c:out value="${result.boardIdx}" />)" title="다운로드"><i class="fa fa-download" aria-hidden="true"></i></a>
						</c:if>
					</td>
					<td><c:out value="${result.userName}" /></td>
					<fmt:parseDate value="${result.insrtDate}" var="dateValue" pattern="yyyy-MM-dd"/>
					<fmt:formatDate var="insrtDate" value="${dateValue}" pattern="yyyy-MM-dd" />
					<td><c:out value="${insrtDate}"/></td>
					<td><c:out value="${result.viewCnt}" /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="tbl_btm">
		<div class="f_left">
			<div class="pagenum">
				<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
			</div>
		</div>
		<div class="f_right">
			<a href="/setting/board/noticeWrite.do" class="btn btn-lg btn-primary"><i class="fa fa-pencil" aria-hidden="true"></i> 글쓰기</a>
		</div>
	</div>
	<form id="boardForm" name="boardForm" method="post" action="/setting/board/notice.do">
		<input type="hidden" id="page" name="page" value="${page}" />
		<input type='hidden' id="boardIdx" name='boardIdx' value="0" />
		<input type='hidden' id="boardType" name="boardType" value="NOTICE" />
	</form>
</div>
<!--+++++ /컨텐츠 +++++-->

<!--+++++ 우측 레이어(도움말) +++++-->
<aside id="aside_right">
	<header class="aside_right_header">
		<h2>도움말</h2>
		<a href="javascript:;" class="btn_close">창닫기</a>
	</header>
	<div class="aside_right_con">
		<form id="guideForm" name="guideForm" method="post">
			<input id="uiId" name="uiId" type="hidden" value="MID03003006000" />
			<textarea id="guideContent" name="guideContent" rows="40" style="resize:none;"></textarea>
			<a id="updateGuide" href="javascript:updateGuide();" class="btn"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
		</form>
	</div>
</aside>
<!--+++++ /우측 레이어(도움말) +++++-->

<script>
$(function(){
});
</script>

</body>
</html>