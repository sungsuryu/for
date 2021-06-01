<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/for/inc/_header.jsp"%>
<script type="text/javascript">

	// 그룹코드 클릭 이벤트
	$(document).on("click", ".group-code-tr", function(event) {
		$(".group-code-tr").removeClass("on");
		
		var thisEl = $(event.currentTarget);
		thisEl.addClass("on");
		
		var cdEl = thisEl.children("td").children("strong");
		var cmmCd = $(cdEl).text();
		var pData = {
			"cmmCd":cmmCd
		};
		subCodeList(pData);
	});
	
	// 세부코드 목록 조회
	var subCodeList = function(pData) {
		$("#prtCmmCd").text(pData.cmmCd);	// 선태한 그룹코드 세팅
		
		$.ajax({
	        type:"POST",
	        url:"/setting/code/subCode.ajax",
	        data : pData,
	        success: function(e){
	        	var cdlist = e.subCodeList;
	        	var cdSiz = cdlist.length;
	        	
	        	$("#subCodeList .subCode").remove();
	        	
	        	if (cdSiz > 0) {
					for (var i in cdlist) {
						addSubCode(cdlist[i]);	// 세부코드 화면생성 요청
					}		        		
	        	}
	        }
    	});
	};
	
	// 코드추가
	var addCode = function(pData) {
		$.ajax({
	        type:"POST",
	        url:"/setting/code/addCodeAction.ajax",
	        data : pData,
	        success: function(e){
	        	var getData = e.commonCodeVO;
	        	
	        	if (pData.uri == "addSubCode") {
	        		addSubCode(getData);
	        	} else {
	        		addGrpCode(getData);
	        	}
	        }
    	});
	};
	
	// 코드추가 - UI
	var initCode = function(sCode) {
		var cmmCd = sCode.cmmCd;
		var cmmCdNm = sCode.cmmCdNm;
		var cdDesc = sCode.cdDesc;
		var useYn = sCode.useYn;
		
		var cells = $("<strong />").text(cmmCd);
		var rows = $("<tr />").append(
				$("<td />").append(cells), 
				$("<td />").text(cmmCdNm),
				$("<td />").text(cdDesc),
				$("<td />").text(useYn)
		);
		return rows;
	};

	// 그룹코드 생성 - UI
	var addGrpCode = function(sCode) {
		var rows = initCode(sCode);	// 공통코드 목록항목 생성
		var liCell = $("<i />");
		$(liCell).addClass("fa");
		$(liCell).addClass("fa-trash-o");
		$(liCell).attr("aria-hidden", true);
		var jsCell = $("<a />").append($(liCell));
		$(jsCell).addClass("btn btn-sm btnGrpDel");
		$(jsCell).attr("href", "javascript:void(0)");
		$(rows).append(
				$("<td />").append(jsCell)
		);
		$(rows).addClass('group-code-tr');
		
		$(".grpCodeInput").before(rows);
	};
	
	// 세부코드 생성 - UI
	var addSubCode = function(sCode) {
		var rows = initCode(sCode);	// 공통코드 목록항목 생성
		var liCell = $("<i />");
		$(liCell).addClass("fa");
		$(liCell).addClass("fa-trash-o");
		$(liCell).attr("aria-hidden", true);
		var jsCell = $("<a />").append($(liCell));
		$(jsCell).addClass("btn btn-sm btnSubDel");
		$(jsCell).attr("href", "javascript:void(0)");
		$(rows).append(
				$("<td />").text(sCode.sortNum),
				$("<td />").append(jsCell)
		);
		$(rows).addClass('subCode');
		
		$(".subCodeInput").before(rows);
	};
	
	var initGrpCdArea = function() {
		$("#grpCmmCd").val("");
		$("#grpCmmCdNm").val("");
		$("#grpCdDesc").val("");
		$("#grpUseYn option:eq(0)").prop("selected", true);
	};
	
	var initSubCdArea = function() {
		$("#subCmmCd").val("");
		$("#subCmmCdNm").val("");
		$("#subCdDesc").val("");
		$("#subSortNum option:eq(0)").prop("selected", true);
		$("#subUseYn option:eq(0)").prop("selected", true);
	};
	
	// 그룹코드 추가 이벤트
	$(document).on("click", "#btnGrpAdd", function(event) {
		var grpCmmCd = $("#grpCmmCd").val();
		var grpCmmCdNm = $("#grpCmmCdNm").val();
		var grpCdDesc = $("#grpCdDesc").val();
		
		if (grpCmmCd == "") {
			alert("코드를 입력해주세요.");
			$("#grpCmmCd").focus();
			return;
		}
		if (grpCmmCdNm == "") {
			alert("코드명을 입력해주세요.");
			$("#grpCmmCdNm").focus();
			return;
		}
		
		var pDate = {
				"uri":"addGrpCode", 
				"cmmCd":grpCmmCd, 
				"cmmCdNm":grpCmmCdNm,
				"cdDesc":grpCdDesc,
				"useYn":$("#grpUseYn").val()
		};
		
		addCode(pDate);
		initGrpCdArea();
	});
	
	// 그룹코드 삭제
	$(document).on("click", ".btnGrpDel", function(event) {
		if (confirm("삭제 하시겠습니까?")) {
			var thisEl = $(event.currentTarget);
			var targetEl = $(thisEl).parent().parent();
			var cdEl = targetEl.children("td").children("strong");
			var cmmCd = $(cdEl).text();

			$.ajax({
		        type:"POST",
		        url:"/setting/code/delCodeAction.ajax",
		        data : {
		        	"uri":"addGrpCode",
					"cmmCd":cmmCd	
				},
		        success: function(e){
		        	$("#prtCmmCd").text("");
		        	$("#subCodeList .subCode").remove();
		        	$(targetEl).remove();
		        }
	    	});
		}
	});
	
	// 세부코드 추가 이벤트
	$(document).on("click", "#btnSubAdd", function(event) {
		var subCmmCd = $("#subCmmCd").val();
		var subCmmCdNm = $("#subCmmCdNm").val();
		var subCdDesc = $("#subCdDesc").val();
		
		if (subCmmCd == "") {
			alert("코드를 입력해주세요.");
			$("#subCmmCd").focus();
			return;
		}
		if (subCmmCdNm == "") {
			alert("코드명을 입력해주세요.");
			$("#subCmmCdNm").focus();
			return;
		}
		
		var pDate = {
				"uri":"addSubCode", 
				"cmmCd":subCmmCd, 
				"prtCmmCd":$("#prtCmmCd").text(),
				"cmmCdNm":subCmmCdNm,
				"cdDesc":subCdDesc,
				"sortNum":$("#subSortNum").val(),
				"useYn":$("#grpUseYn").val()
		};
		
		addCode(pDate);
		initSubCdArea();
	});
	
	// 세부코드 삭제
	$(document).on("click", ".btnSubDel", function(event) {
		if (confirm("삭제 하시겠습니까?")) {
			var thisEl = $(event.currentTarget);
			var targetEl = $(thisEl).parent().parent();
			var cdEl = targetEl.children("td").children("strong");
			var cmmCd = $(cdEl).text();

			$.ajax({
		        type:"POST",
		        url:"/setting/code/delCodeAction.ajax",
		        data : {
		        	"uri":"addSubCode",
					"cmmCd":cmmCd, 
					"prtCmmCd":$("#prtCmmCd").text()
				},
		        success: function(e){
// 		        	$("#prtCmmCd").text("");
// 		        	$("#subCodeList .subCode").remove();
 		        	$(targetEl).remove();
		        }
	    	});
		}
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

	<header class="contents_header">
		<h2>공통코드 관리</h2>
<%@ include file="/WEB-INF/jsp/for/inc/_location.jsp" %>
	</header>
	
	<div class="clear layout_for009">
		<!-- 코드그룹 -->
		<section>
			<!-- div class="top_btn right">
				<a href="javascript:;" class="btn btn-primary"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
				<a href="javascript:;" class="btn btn-info"><i class="fa fa-search" aria-hidden="true"></i> 조회</a>
			</div -->
			<div class="basic_box">			
				<header class="basic_box_header">
					<h2>코드그룹</h2>
					<!-- a href="javascript:;" class="btn btn-sm f_right">추가 <i class="fa fa-plus-circle" aria-hidden="true"></i></a -->
				</header>
				<div class="basic_box_con">
					<div class="table_h01">
						<span id="prtCmmCd" style="display:none"></span>
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
								<c:forEach var="groupCode" items="${groupCodeList }" varStatus="status">
								<tr class="group-code-tr">
									<td><strong>${groupCode.cmmCd }</strong></td>
									<td>${groupCode.cmmCdNm }</td>
									<td>${groupCode.cdDesc }</td>
									<td>${groupCode.useYn }</td>
									<td><a href="javascript:void(0)" class="btn btn-sm btnGrpDel" title="삭제"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>
								</tr>
								</c:forEach>

								<tr class="grpCodeInput">
									<td><input type="text" id="grpCmmCd" name="grpCmmCd" placeholder="코드" style="width:100%"></td>
									<td><input type="text" id="grpCmmCdNm" name="grpCmmCdNm" placeholder="코드명" style="width:100%"></td>
									<td><input type="text" id="grpCdDesc" name="grpCdDesc" placeholder="코드설명" style="width:100%"></td>
									<td>
										<span class="styled_select" style="width:54px; min-width:inherit">
											<select id="grpUseYn" name="grpUseYn">
												<option value="Y">Y</option>
												<option value="N">N</option>
											</select>
											<i class="fa fa-sort" aria-hidden="true"></i>
										</span>
									</td>
									<td><a href="javascript:void(0)" class="btn btn-sm" id="btnGrpAdd" title="추가"><i class="fa fa-plus" aria-hidden="true"></i></a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</section>
		<!-- 코드목록 -->
		<section>
			<!-- div class="top_btn right">
				<a href="javascript:;" class="btn btn-primary"><i class="fa fa-check-circle" aria-hidden="true"></i> 저장</a>
				<a href="javascript:;" class="btn btn-info"><i class="fa fa-search" aria-hidden="true"></i> 조회</a>
			</div -->
			<div class="basic_box">			
				<header class="basic_box_header">
					<h2>코드목록</h2>
					<!-- a href="javascript:;" class="btn btn-sm f_right">추가 <i class="fa fa-plus-circle" aria-hidden="true"></i></a -->
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
							<tbody id="subCodeList">
								<!--  subcode area -->
								<tr class="subCodeInput">
									<td><input id="subCmmCd" name="subCmmCd" type="text" placeholder="코드" style="width:100%"></td>
									<td><input id="subCmmCdNm" name="subCmmCdNm" type="text" placeholder="코드명" style="width:100%"></td>
									<td><input id="subCdDesc" name="subCdDesc" type="text" placeholder="설명" style="width:100%"></td>
									<td>
										<span class="styled_select" style="width:54px; min-width:inherit">
											<select id="subUseYn" name="subUseYn">
												<option value="Y">Y</option>
												<option value="N">N</option>
											</select>
											<i class="fa fa-sort" aria-hidden="true"></i>
										</span>
									</td>
									<td>
										<span class="styled_select" style="width:50px; min-width:inherit">
											<select id="subSortNum" name="subSortNum">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
												<option value="9">9</option>
											</select>
											<i class="fa fa-sort" aria-hidden="true"></i>
										</span>
									</td>
									<td><a href="javascript:;" class="btn btn-sm" id="btnSubAdd" title="추가"><i class="fa fa-plus" aria-hidden="true"></i></a></td>
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