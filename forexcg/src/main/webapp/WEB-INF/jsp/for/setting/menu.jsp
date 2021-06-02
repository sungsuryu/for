<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/for/inc/_header.jsp" %>
<script type="text/javascript">
	$(document).on('click', '.mnuLst li a', function(event) {
		$(this).parent().parent().find('> li').removeClass('on');
		$(this).parent().addClass('on');
		$(".subMnuLst").find("> li").remove();
		$(".dtlMnuLst").find("> li").remove();
		
		var cId = $(this).parent().find("> span").text();
		var pData = {
				'lvl':2, 
				'prtMnuId':cId
		};
		reqSubMenu("subMnuLst", pData);
	});

	$(document).on('click', '.subMnuLst li a', function(event) {
		$(this).parent().parent().find('> li').removeClass('on');
		$(this).parent().addClass('on');
		$(".dtlMnuLst").find("> li").remove();
		
		var cId = $(this).parent().find("> span").text();
		var pData = {
				'lvl':3, 
				'prtMnuId':cId
		};
		reqSubMenu("dtlMnuLst", pData);
	});

	var reqSubMenu = function(uri, pData) {
		if (pData.prtMnuId == "") {
			alert("선택된 메뉴가 없습니다.");
			return;
		}
		$.ajax({
	        type:"POST",
	        url:"/setting/subMenu.ajax",
	        data : pData,
	        success: function(e){
	        	if (uri == "subMnuLst")
	        		$(".subMnuLst").find("> li").remove();
	        	$(".dtlMnuLst").find("> li").remove();
	        	
	        	var mnuLst = e.subMenuList;
	        	var mCnt = mnuLst.length;
	        	if (mCnt > 0) {
	        		for (var i in mnuLst) {
	        			addMenu(uri, mnuLst[i]);
	        		}
	        	}
	        }
    	});
	};
	
	var addMenu = function(id, itm) {
		var mnuId = itm.mnuId;
		var mnuNm = itm.mnuNm;
		
		var rId = $("<span />").text(mnuId);
		$(rId).addClass("dispNon");
		var rAt = $("<a />").text(mnuNm);
		$(rAt).attr("href", "javascript:void(0)");
		var rows = $("<li />").append(
				$(rId), 
				$(rAt)
			);
		
		$("."+id).append(rows);
	};
	
	$(document).on('click', '.dtlMnuLst li a', function(event) {
		$(this).parent().parent().find('> li').removeClass('on');
		$(this).parent().addClass('on');


	});
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
			<li class="on"><a href="for_011.htm">메뉴 관리</a></li>
			<li><a href="for_012.htm">그룹별 메뉴 관리</a></li>
			<li><a href="for_013.htm">팝업 관리</a></li>
			<li><a href="for_014.htm">게시판 관리</a></li>
			<li><a href="for_015.htm">한국은행 시스템 정보 관리</a></li>
		</ul>
	</div-->
	
	<header class="contents_header">
		<h2>메뉴 관리</h2>
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
					<option>메뉴 관리</option>	
					<option>공통코드 관리</option>
					<option>보고서 관리</option>
					<option>그룹별 메뉴 관리</option>
					<option>팝업 관리</option>
					<option>게시판 관리</option>
					<option>한국은행 시스템 정보 관리</option>
				</select>
				<i class="fa fa-chevron-down" aria-hidden="true"></i>
			</span>
		</div>
	</header>
	
	<div class="clear layout_for_011 paragraph">
		<!-- 대메뉴 -->
		<div class="basic_box">			
			<header class="basic_box_header">
				<h2>대메뉴</h2>
			</header>
			<div class="basic_box_con">
				<ul class="list mnuLst">
					<!-- 선택메뉴 addClass="on" -->
					<c:forEach var="mnuList" items="${menuList }" varStatus="status">
					<li><span class="dispNon">${mnuList.mnuId }</span><a href="javascript:void(0)">${mnuList.mnuNm }</a></li>
					</c:forEach>
					
				</ul>
			</div>
		</div>
		<!-- 중메뉴 -->
		<div class="basic_box">			
			<header class="basic_box_header">
				<h2>중메뉴</h2>
			</header>
			<div class="basic_box_con">
				<ul class="list subMnuLst">
				</ul>
			</div>
		</div>
		<!-- 소메뉴 -->
		<div class="basic_box">			
			<header class="basic_box_header">
				<h2>소메뉴</h2>
			</header>
			<div class="basic_box_con">
				<ul class="list dtlMnuLst">
				</ul>
			</div>
		</div>
	</div>
	
	<div class="table_v01">
		<table>
			<colgroup>
				<col style="width:180px">
				<col style="">
				<col style="width:180px">
				<col style="">
			</colgroup>
			<tbody>
				<tr>
					<th>메뉴위치</th>
					<td>
						<input type="radio" name="lvl" id="lvl_1" value="1" checked="checked"><i></i> <label for="lvl_1">대메뉴</label>
						<span class="space"></span>
						<input type="radio" name="lvl" id="lvl_2" value="2"><i></i> <label for="lvl_2">중메뉴</label>
						<span class="space"></span>
						<input type="radio" name="lvl" id="lvl_3" value="3"><i></i> <label for="lvl_3">소메뉴</label>
					</td>
					<th>메뉴유형</th>
					<td>
					<span class="styled_select" style="width:120px; min-width:inherit">
						<select name="mnuType" id="mnuType">
							<option value="">선택</option>
						<c:forEach var="mnuType" items="${mnuTypeLst}" varStatus="status">
							<option value="${mnuType.cmmCd }">${mnuType.cmmCdNm }</option>
						</c:forEach>
						</select>
						<i class="fa fa-sort" aria-hidden="true"></i>
					</span>
					</td>
					<th>설명</th>
					<td>
						<input type="text" id="mnuDesc" name="mnuDesc" style="width:100%">
					</td>
				</tr>
				<tr>
					<th>메뉴ID</th>
					<td><input type="text" id="mnuId" name="mnuId" style="width:100%" placeholder="MID00000000000"></td>
					<th>포함여부</th>
					<td>
						<select name="isInc" id="isInc">
							<option value="Y">포함</option>
							<option value="N">미포함</option>
						</select>
						<i class="fa fa-sort" aria-hidden="true"></i>
					</td>
					<th>URL</th>
					<td><input type="text" name="url" id="url" style="width:100%" placeholder="URL"></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="tbl_btm right">
		<a href="javascript:;" class="btn btn-lg btn-green"><i class="fa fa-plus-circle" aria-hidden="true"></i> 신규</a>
		<a href="javascript:;" class="btn btn-lg btn-primary"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
		<a href="javascript:;" class="btn btn-lg btn-red"><i class="fa fa-trash-o" aria-hidden="true"></i> 삭제</a>
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



</body>
</html>