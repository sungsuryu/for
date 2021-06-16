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
		
		$("#firstMnuId").val(cId);
		$("#secondMnuId").val("");
		$("#thirdMnuId").val("");
		$("#menuForm").submit();
	});
	
	$(document).on('click', '.subMnuLst li a', function(event) {
		$(this).parent().parent().find('> li').removeClass('on');
		$(this).parent().addClass('on');
		$(".dtlMnuLst").find("> li").remove();
		
		var cId = $(this).parent().find("> span").text();
		
		$("#secondMnuId").val(cId);
		$("#thirdMnuId").val("");
		$("#menuForm").submit();
	});
	
	// 소메뉴 client 이벤트
	$(document).on('click', '.dtlMnuLst li a', function(event) {
		$(this).parent().parent().find('> li').removeClass('on');
		$(this).parent().addClass('on');

		var cId = $(this).parent().find("> span").text();
		
		$("#thirdMnuId").val(cId);
		$("#menuForm").submit();
	});
	
	$(document).on('click', '.btnSave', function(event) {
		if ($("#mnuId").val() == "") {
			alert("메뉴ID를 입력해주세요.");
			return;
		}
		if ($("#mnuNm").val() == "") {
			alert("메뉴명을 입력해주세요.");
			return;
		}
		var pData = $("#saveForm").serialize();
		$.ajax({
	        type:"POST",
	        url:"/setting/updateMenu.ajax",
	        data : pData,
	        success: function(e){
	        	try {
	        		if (e.result.status == "SUCCESS") {
	        			$("#menuForm").submit();
	        		}
	        	} catch (e) {;}
	        }
    	});
	});
	
	$(document).on("click", ".btnDelete", function(event) {
		if (confirm("삭제 하시겠습니까?")) {
			var mnuId = $("#mnuId").val();
			if (mnuId == "") {
				alert("삭제할 메뉴를 선택해 주세요.");
				return;
			}
			$.ajax({
		        type:"POST",
		        url:"/setting/deleteMenu.ajax",
		        data : {
		        	"mnuId":mnuId
		        },
		        success: function(e){
		        	try {
		        		if (e.result.status == "SUCCESS") {
		        			$("#menuForm").submit();
		        		}
		        	} catch (e) {;}
		        }
	    	});
		}
	});
	
	// 대메뉴 click 이벤트
// 	$(document).on('click', '.mnuLst li a', function(event) {
// 		$(this).parent().parent().find('> li').removeClass('on');
// 		$(this).parent().addClass('on');
// 		$(".subMnuLst").find("> li").remove();
// 		$(".dtlMnuLst").find("> li").remove();
		
// 		var cId = $(this).parent().find("> span").text();
// 		var pData = {
// 				'lvl':2, 
// 				'prtMnuId':cId
// 		};
// 		reqSubMenu("subMnuLst", pData);
// 	});

	// 중메뉴 click 이벤트
// 	$(document).on('click', '.subMnuLst li a', function(event) {
// 		$(this).parent().parent().find('> li').removeClass('on');
// 		$(this).parent().addClass('on');
// 		$(".dtlMnuLst").find("> li").remove();
		
// 		var cId = $(this).parent().find("> span").text();
// 		var pData = {
// 				'lvl':3, 
// 				'prtMnuId':cId
// 		};
// 		reqSubMenu("dtlMnuLst", pData);
// 	});

	// 하위메뉴 render 이벤트
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
	        	console.log(e);
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
	        	//setMenuDesc(e.cMenu);	// 메뉴등록 및 수정화면 세팅
	        }
    	});
	};
	
	// 메뉴등록 및 수정화면 세팅
	var setMenuDesc = function(e) {
		var mnuId = e.mnuId;
		var mnuNm = e.mnuNm;
		var lvl = e.lvl;
		var srcMnuId = e.srcMnuId;
		var prtMnuId = e.prtMnuId;
		var mnuType = e.mnuType;
		var isInc = e.isInc;
		var url = e.url;
		var mnuDesc = e.mnuDesc;
		
		if ($("input[name='lvl']:checked").val() != lvl) {	// 선택한 메뉴와 등록 및 수정화면의 선택된 값(lvl)이 다른경우 상위메뉴 reload 이벤트 생성
			$("input[name='lvl']").each(function() {
				if($(this).val() == lvl)
					$(this).attr('checked', true);	
			});
			var pData = {
					"lvl":lvl--
				};
			subaction(pData);	// 상위메뉴 reload
		}
		// TODO interval 차이로 binding 안되고 있음.
		$("#prtMnuId").val(prtMnuId).prop("selected", true);
		$("#srcMnuId").val(srcMnuId).prop("selected", true);
		$("#mnuId").val(mnuId);
		$("#mnuNm").val(mnuNm);
		$("#mnuType").val(mnuType).prop("selected", true);
		$("#isInc").val(isInc).prop("selected", true);
		$("#url").val(url);
		$("#mnuDesc").val(mnuDesc);
	};
	
	// render 메뉴에 이벤트 추가
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
	
	// 메뉴위치 change 이벤트
	$(document).on("change", "input[name='lvl']:radio", function(e) {
		var getLvl = $(this).val();
		var pData = {
				"lvl":getLvl--
		};
		subaction(pData);	// 상위메뉴 reload
	});
	
	// 상위메뉴 reload 이벤트
	var subaction = function(pData) {
		$.ajax({
	        type:"POST",
	        url:"/setting/prtMenuLst.ajax",
	        data : pData,
	        success: function(e){
	        	try {
		        	if (e.result.status == "SUCCESS") {
		        		var itm = e.prtMenu;
		        		var mnuCnt = e.prtMenu.length;
		        		initOption();
		        		if (mnuCnt > 0) {
		        			initOption();
		        			for (var i in itm) {
			        			addOption(itm[i]);
			        		}
		        		}
		        	}
	        	} catch (e) {;}
	        }
    	});
	};
	
	// 메뉴 등록 및 수정화면의 상위메뉴 option 추가
	var addOption = function(itm) {
		var opt = $("<option />").val(itm.mnuId);
		$("#prtMnuId").append(
				$(opt).text(itm.mnuNm)
		);
	};
	
	// 메뉴 등록 및 수정화면의 상위메뉴 option 초기화
	var initOption = function() {
		$("#prtMnuId > option").remove();
	};

</script>
</head>

<body>
<form name="menuForm" id="menuForm" method="post" action="<c:out value="${menuInfo.url }" />">
	<input type="text" name="firstMnuId" id="firstMnuId" value="<c:out value="${menuVO.firstMnuId }" />" />
	<input type="text" name="secondMnuId" id="secondMnuId" value="<c:out value="${menuVO.secondMnuId }" />" />
	<input type="text" name="thirdMnuId" id="thirdMnuId" value="<c:out value="${menuVO.thirdMnuId }" />" />
	<input type="hidden" name="setLvl" id="setLvl" />
</form>
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

	<header class="contents_header">
		<h2>메뉴 관리</h2>
<%@ include file="/WEB-INF/jsp/for/inc/_location.jsp" %>
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
					<c:forEach var="fstList" items="${fstList }" varStatus="status">
					<li<c:if test="${fstList.mnuId eq menuVO.firstMnuId }"> class="on"</c:if>><span class="dispNon">${fstList.mnuId }</span><a href="javascript:void(0)">${fstList.mnuNm }</a></li>
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
					<c:forEach var="sndList" items="${sndList }" varStatus="status">
					<li<c:if test="${sndList.mnuId eq menuVO.secondMnuId }"> class="on"</c:if>><span class="dispNon">${sndList.mnuId }</span><a href="javascript:void(0)">${sndList.mnuNm }</a></li>
					</c:forEach>
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
					<c:forEach var="trdList" items="${trdList }" varStatus="status">
					<li<c:if test="${trdList.mnuId eq menuVO.thirdMnuId }"> class="on"</c:if>><span class="dispNon">${trdList.mnuId }</span><a href="javascript:void(0)">${trdList.mnuNm }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="table_v01">
		<table>
		<form name="saveForm" id="saveForm">
			<colgroup>
				<col style="width:180px">
				<col style="">
				<col style="width:180px">
				<col style="">
			</colgroup>
			<tbody>
				<tr>
					<th>메뉴위치-${selMenu.lvl }</th>
					<td>
					<c:choose>
						<c:when test="${empty selMenu }"><input type="radio" name="lvl" id="lvl_1" value="1" checked="checked"><i></i> <label for="lvl_1">대메뉴</label></c:when>
						<c:otherwise><input type="radio" name="lvl" id="lvl_1" value="1"<c:if test="${selMenu.lvl eq 1 }">  checked="checked"</c:if>><i></i> <label for="lvl_1">대메뉴</label></c:otherwise>
					</c:choose>
						<span class="space"></span>
						<input type="radio" name="lvl" id="lvl_2" value="2"<c:if test="${selMenu.lvl eq 2 }">  checked="checked"</c:if>><i></i> <label for="lvl_2">중메뉴</label>
						<span class="space"></span>
						<input type="radio" name="lvl" id="lvl_3" value="3"<c:if test="${selMenu.lvl eq 3 }">  checked="checked"</c:if>><i></i> <label for="lvl_3">소메뉴</label>
					</td>
					<th>상위메뉴</th>
					<td>
					<span class="styled_select" style="width:120px; min-width:inherit">
						<select name="prtMnuId" id="prtMnuId">
						<c:forEach var="prtMenu" items="${prtMenu }" varStatus="status">
							<option value="<c:out value="${prtMenu.mnuId }" />"<c:if test="${prtMenu.mnuId eq selMenu.prtMnuId }"> selected="true"</c:if>><c:out value="${prtMenu.mnuNm }"></c:out></option>
						</c:forEach>
						</select>
						<i class="fa fa-sort" aria-hidden="true"></i>
					</span>
					</td>
					
					<th>대표메뉴</th>
					<td>
					<span class="styled_select" style="width:120px; min-width:inherit">
						<select name="srcMnuId" id="srcMnuId">
							<option value="">선택</option>
						<c:forEach var="srcMnu" items="${srcMenu }" varStatus="status">
							<option value="${srcMnu.srcMnuId }"<c:if test="${srcMnu.mnuId eq selMenu.srcMnuId }"> selected="true"</c:if>>${srcMnu.mnuNm }</option>
						</c:forEach>
						</select>
						<i class="fa fa-sort" aria-hidden="true"></i>
					</span>
					</td>
					
				</tr>
				<tr>
					<th>메뉴ID</th>
					<td><input type="text" id="mnuId" name="mnuId" style="width:100%" placeholder="MID00000000000" value="<c:out value="${selMenu.mnuId }" />"></td>
					<th>메뉴유형</th>
					<td>
					<span class="styled_select" style="width:120px; min-width:inherit">
						<select name="mnuType" id="mnuType">
							<option value="">선택</option>
						<c:forEach var="mnuType" items="${mnuTypeLst}" varStatus="status">
							<option value="${mnuType.cmmCd }"<c:if test="${mnuType.cmmCd eq selMenu.mnuType }"> selected="true"</c:if>>${mnuType.cmmCdNm }</option>
						</c:forEach>
						</select>
						<i class="fa fa-sort" aria-hidden="true"></i>
					</span>
					</td>
					<th>노출여부</th>
					<td>
					<span class="styled_select" style="width:120px; min-width:inherit">
						<select name="isInc" id="isInc">
							<option value="Y"<c:if test="${selMenu.isInc eq 'Y'}"> selected="true"</c:if>>포함</option>
							<option value="N"<c:if test="${selMenu.isInc eq 'N'}"> selected="true"</c:if>>미포함</option>
						</select>
						<i class="fa fa-sort" aria-hidden="true"></i>
					</span>
					
				</tr>
				<tr>
					<th>메뉴명</th>
					<td>
						<input type="text" id="mnuNm" name="mnuNm" style="width:100%" value="<c:out value="${selMenu.mnuNm }"/>">
					</td>
					<th>URL</th>
					<td colspan="3">
					<c:choose>
						<c:when test="${empty selMenu.url }"><input type="text" name="url" id="url" style="width:100%" placeholder="URL"></c:when>
						<c:otherwise><input type="text" name="url" id="url" style="width:100%" placeholder="URL" value="<c:out value="${selMenu.url }" />"></c:otherwise>
					</c:choose>
					</td>
					
				</tr>
			</tbody>
		</form>
		</table>
	</div>
	
	<div class="tbl_btm right">
		<a href="javascript:void(0);" class="btn btn-lg btn-green"><i class="fa fa-plus-circle" aria-hidden="true"></i> 신규</a>
		<a href="javascript:void(0);" class="btn btn-lg btn-primary btnSave"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
		<a href="javascript:void(0);" class="btn btn-lg btn-red btnDelete"><i class="fa fa-trash-o" aria-hidden="true"></i> 삭제</a>
	</div>
	
</div>
<!--+++++ /컨텐츠 +++++-->
<%@ include file="/WEB-INF/jsp/for/inc/_help.jsp" %>
</body>
</html>