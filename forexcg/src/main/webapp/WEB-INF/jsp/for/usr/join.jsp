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
<script type="text/javascript">
	$(function() {
		var authrequest = false;
		// 휴대폰번호 인증 받기
	 	$('#btnReqAuth').click(function(){
	 		var cellNumTxt = $("#cellNumTxt").val();
	 		if (cellNumTxt == "") {
	 			alert("휴대폰번호를 입력해주세요.");
	 			$("#cellNumTxt").focus();
	 			return;
	 		}
	 		var chkStyle = /[0-9]{9,12}$/;
	 		if (!chkStyle.test(cellNumTxt)) {
	 			alert("휴대폰번호는 숫자만 입력해주세요.");
	 			$("#cellNumTxt").focus();
	 			return;
	 		}
	 		if (cellNumTxt.length < 9) {
	 			alert("휴대폰번호를 확인해주세요.");
	 			$("#cellNumTxt").focus();
	 			return;
	 		}

	 		$.ajax({
		        type:"POST",
		        url:"/reqCellAuth.ajax",
		        data : {
		        	"cellNum":cellNumTxt
		        },
		        beforeSend: function(xhr, opts) {
		        	if (authrequest)
		        		xhr.abort();
		        	
		        	authrequest = true;
		        },
		        success: function(e){
		        	console.log(e);	
		        	
		        	$("#cellNum").val($("#cellNumTxt").val());
		        	
		        	$("#btnReqAuth").hide();
		        	$("#cellNumTxt").attr("disabled", true);
		        	
		        	$("#authInputNum").show();
		    		$("#btnAuthCfm").show();
		        },
		        complete : function() {
		        	authrequest = false;
		        }
	    	});
		});
		
		var authresponse = false;
		$("#btnAuthCfm").click(function() {
			var authNum = $("#authInputNum").val();
	 		if (authNum == "") {
	 			alert("인증번호를 입력해주세요.");
	 			$("#authNum").focus();
	 			return;
	 		}
	 		var chkStyle = /[0-9]{6}$/;
	 		if (!chkStyle.test(authNum)) {
	 			alert("인증번호는6자리  숫자만 입력해주세요.");
	 			$("#authNum").focus();
	 			return;
	 		}
	 		
	 		$.ajax({
		        type:"POST",
		        url:"/resCellAuth.ajax",
		        data : {
		        	"cellNum":cellNum, 
		        	"authNum":authNum
		        },
		        beforeSend: function(xhr, opts) {
		        	if (authrequest)
		        		xhr.abort();
		        	
		        	authrequest = true;
		        },
		        success: function(e){
		        	console.log(e);
		        	$("#btnReqAuth").hide();
		        	$("#cellNumTxt").attr("disabled", true);
		        	
		        	$("#btnAuthCfm").show();
		        },
		        complete : function() {
		        	authrequest = false;
		        }
	    	});
		})
	});

	$(document).ready(function() {
		$("#authInputNum").hide();
		$("#btnAuthCfm").hide();
		
		// 회원가입
		$(".btn-primary").click(function() {

			var getId = $("#userIdTxt").val();
			
			if (getId == "") {
				alert("사용할 아이디를 입력해주세요.");
				$("#userIdTxt").focus();
				return;
			}
			if ($("#userId").val() == "") {
				alert("아이디 중복확인을 해주세요.");
				return;
			}
			if ($("#password").val() == "") {
				alert("비밀번호를 입력해주세요.");
				$("#password").focus();
				return;
			}
			if ($("#passwordRe").val() == "") {
				alert("비밀번호를 한번 더 입력해주세요.");
				$("#passwordRe").focus();
				return;
			}
			if ($("#password").val() != $("#passwordRe").val()) {
				alert("비밀번호가 일치하지 않습니다.");
				$("#passwordRe").focus();
				return;
			}
			if ($("#insurCd").val() == "") {
				alert("소속회사를 선택해주세요.");
				return;
			}
			if ($("#acsIp").val() == "") {
				alert("접속 IP를 입력해주세요.");
				$("#acsIp").focus();
				return;
			}
			if ($("#acsIp").val() == "") {
				alert("접속 IP를 입력해주세요.");
				$("#acsIp").focus();
				return;
			}
			if ($("#emlAddr").val() == "") {
				alert("이메일 주소를 입력해주세요.");
				$("#emlAddr").focus();
				return;
			}
			if ($("#cellNum").val() == "") {
				alert("휴대폰번호를 입려해주세요.");
				return;
			}
			if ($("input[name=joinAgree]:checked").val() != "Y") {
				alert("개인정보 수집(이용)에 동의 해주세요.");
				return;
			}
			if ($("#file_1").val() == "") {
				alert("계정등록신청서를 선택해주세요.");
				return;
			}
			
			$("#joinForm").submit();
		});
	});
	
	var gotoLogin = function() {
		location.href = "login.do";
	};
	
	var checkstatus = false;
	// 아이디 중복확인
	var checkUserId = function(e) {
		var getId = $("#userIdTxt").val();
		
		if (getId == "") {
			alert("사용할 아이디를 입력해주세요.");
			$("#userIdTxt").focus();
			return;
		}
		// TODO 아이디 생성 rule 적용해야함. 6자이상 20자이하 영문/숫자만 허용
		$.ajax({
	        type:"POST",
	        url:"/checkUserId.ajax",
	        data : {
	        	"userId":getId
	        },
	        beforeSend: function(xhr, opts) {
	        	if (checkstatus)
	        		xhr.abort();
	        	
	        	checkstatus = true;
	        }, 
	        success: function(e){
	        	try {
		            if (e.result.status == 'SUCCESS' && e.result.countUserId < 1) {
		            	alert('사용가능한 아이디 입니다.');
		            	
		            	$("#userId").val(getId);
		            	$("#userIdTxt").attr("disabled", true);
		            	$("#userIdTxt").css("background", "#ccc");
		            	$("#linkCheckUserId").css("display", "none");
		            } else {
		            	alert('이미 사용중인 아이디 입니다.');
		            }
	        	} catch (e) {
	        		;
	        	}
	        },
	        error: function(xhr, status, error) {
	        	console.log(xhr);
	        	console.log(status);
	        	console.log(error);
	        }, 
	        complete : function() {
	        	checkstatus = false;
	        }
	    });
	};
</script>
</head>
<body>

<div id="popLayerBg"></div>

<div id="join">
<form name="joinForm" id="joinForm" method="post" action="joinAction.do" enctype="multipart/form-data">
<input type="hidden" name="userId" id="userId" />
<input type="hidden" name="cellNum" id="cellNum" />
	<header class="contents_header">
		<h2>회원가입</h2>
	</header>
	<div class="table_v01">
		<table>
			
			<colgroup>
				<col style="width:180px">
				<col style="">
			</colgroup>
			<tbody>
				<tr>
					<th>ID</th>
					<td>
						<input type="text" id="userIdTxt" name="userIdTxt" maxlength="30" />
						<a href="javascript:void(0)" class="btn btn-sm btn-info" onclick="checkUserId(this)" id="linkCheckUserId">중복확인</a>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" id="password" name="password" maxlength="30" />
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" id="passwordRe" name="passwordRe" maxlength="30" />
					</td>
				</tr>
				<tr>
					<th>소속회사</th>
					<td>
						<span class="styled_select" style="width:120px; min-width:inherit">
							<select name="insurCd" id="insurCd">
								<option value="">선택</option>
							<c:forEach var="insureVO" items="${insureList}" varStatus="status">
								<option value="<c:out value="${insureVO.insurCd}"/>"><c:out value="${insureVO.insurNm}"/></option>
							</c:forEach>
							</select>
							<i class="fa fa-sort" aria-hidden="true"></i>
						</span>
					</td>
				</tr>
				<tr>
					<th>IP(본인PC)</th>
					<td>
						<input type="text" id="acsIp" name="acsIp" maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>성명</th>
					<td>
						<input type="text" id="userNm" name="userNm" maxlength="30" />
					</td>
				</tr>
				<tr>
					<th>부서명</th>
					<td>
						<input type="text" id="dptNm" name="dptNm" maxlength="60" />
					</td>
				</tr>
				<tr>
					<th>사무실 전화번호</th>
					<td>
						<input type="text" id="officeTelNum" name="officeTelNum" maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>e-mail</th>
					<td>
						<input type="text" id="emlAddr" name="emlAddr" maxlength="60" />
						<span class="space"></span>
						<input type="checkbox" name="isRcvEml" id="isRcvEml" value="Y"><i></i> <label for="isRcvEml">이메일 수신여부</label>
					</td>
				</tr>
				<tr>
					<th>휴대폰번호</th>
					<td>
						<input type="text" id="cellNumTxt" name="cellNumTxt" maxlength="12" />
						<a href="javascript:void(0)" class="btn btn-sm btn-info" id="btnReqAuth">인증받기</a>
						<input type="text" id="authInputNum" name="authInputNum" maxlength="6" placeholder="인증번호"/>
						<a href="javascript:void(0)" class="btn btn-sm btn-info" id="btnAuthCfm">확인</a>
						<span class="space"></span>
						<input type="checkbox" name="isRcvCell" id="isRcvCell" value="Y"><i></i> <label for="isRcvCell">문자 수신여부</label>
					</td>
				</tr>
				<tr>
					<th>알림톡 설정</th>
					<td>
						<input type="checkbox" name="alrmType" id="alrmType1" value="ARM_NOTICE"><i></i> <label for="alrmType1">공지사항등록</label>
						<span class="space"></span>
						<input type="checkbox" name="alrmType" id="alrmType2" value="ARM_OBT"><i></i> <label for="alrmType2">입수현황점검</label>
					</td>
				</tr>
			</tbody>

		</table>
	</div>
	<p class="succession paragraph">※ 협회 공지사항 등록여부(수시) 및 보고서 누락여부(주1회)를 알림톡으로 수신할 수 있습니다.</p>
	<div class="box01">
		<h3>개인정보 수집(이용)동의</h3>
		<p>상기 본인은 손해보험협회에서 상기 신청내용을 처리하기 위한 목적으로 본인의 개인정보를 수집·이용하며, 동 목적 달성 후 증빙을 위하여 1년간 보유 및 이용하는 것에 동의합니다. </p>
	</div>
	<div class="succession right paragraph">
		<input type="radio" name="joinAgree" id="joinAgree1" value="Y"><i></i> <label for="joinAgree1">동의함</label>
		<span class="space"></span>
		<input type="radio" name="joinAgree" id="joinAgree2" value="N" checked><i></i> <label for="joinAgree2">동의하지 않음</label>
	</div>
	<div class="table_v01">
		<table>
			<colgroup>
				<col style="width:180px">
				<col style="">
				<col style="width:360px">
			</colgroup>
			<tbody>
				<tr>
					<th>계정등록신청서</th>
					<td>
						<input type="file" name="file_1" id="file_1" />
					</td>
					<td class="right"><a href="javascript:;" class="btn btn-sm btn-default"><i class="fa fa-download" aria-hidden="true"></i> 신청서 다운로드</a></td>
				</tr>
			</tbody>
		</table>
	</div>
	<p class="succession">※ 계정등록신청서를 다운로드 받아 작성 후 스캔하여 파일을 첨부해주세요.</p>
	<div class="contents_btn_area">
		<a href="javascript:void(0)" class="btn btn-primary"><i class="fa fa-check-circle" aria-hidden="true"></i> 승인신청</a>
		<a href="javascript:void(0)" class="btn btn-default" onclick="gotoLogin()"><i class="fa fa-ban" aria-hidden="true"></i> 취소</a>
	</div>
</form>
</div>

</body>


</html>