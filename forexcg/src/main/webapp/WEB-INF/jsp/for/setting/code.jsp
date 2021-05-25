<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/for/inc/_header.jsp" %>
<script type="text/javascript">
	$(function() {
		$(".btn_grp_del").click(function(e) {
			alert('ok');
		});
		
		$(".group-code-tr").click(function(event) {
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
		
		var subCodeList = function(pData) {
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
							addSubCode(cdlist[i]);
						}		        		
		        	}
		        	
		        }
	    	});
		}
		
		var addSubCode = function(sCode) {
			var cmmCd = sCode.cmmCd;
			var cmmCdNm = sCode.cmmCdNm;
			var cdDesc = sCode.cdDesc;
			var sortNum = sCode.sortNum;
			var useYn = sCode.useYn;
			
			var cells = $("<strong />").text(cmmCd);
			
			var rows = $("<tr />").append(
					$("<td />").append(cells), 
					$("<td />").text(cmmCdNm),
					$("<td />").text(cdDesc),
					$("<td />").text(useYn),
					$("<td />").text(sortNum),
					$("<td />")
			);
			
			$(rows).addClass('subCode');

			$(".subCodeInput").before(rows);

		};
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
<%@ include file="/WEB-INF/jsp/for/inc/_lnb.jsp" %>
</aside>
<!--+++++ /좌측 레이어 +++++-->

<!--+++++ 컨텐츠 +++++-->
<div id="contents">

	<header class="contents_header">
		<h2>공통코드 관리</h2>
<%@ include file="/WEB-INF/jsp/for/inc/setting/_location.jsp" %>
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
								<c:forEach var="groupCode" items="${groupCodeList }" varStatus="status">
								<tr class="group-code-tr">
									<td><strong>${groupCode.cmmCd }</strong></td>
									<td>${groupCode.cmmCdNm }</td>
									<td>${groupCode.cdDesc }</td>
									<td>${groupCode.useYn }</td>
									<td><a href="javascript:void(0)" class="btn btn-sm btn_grp_del" title="삭제"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>
								</tr>
								</c:forEach>

								<tr class="grpCodeInput">
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
							<tbody id="subCodeList">
								<tr class="subCodeInput">
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