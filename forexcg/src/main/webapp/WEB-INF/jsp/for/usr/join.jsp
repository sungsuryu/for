<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/for/inc/_header.jsp" %>
<script type="text/javascript" src="<c:url value='/js/cmmFunc.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/join.js'/>"></script>
<script type="text/javascript">

	var authrequest = false;
	// 휴대폰번호 인증 받기
 	var btnReqAuth = function(){
 		var cellNumTxt = $("#cellNumTxt").val();

 		if (!PhNumber('휴대폰', cellNumTxt)) {
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
	        	try {
	        		if (e.result.status == 'SUCCESS') {
	        			$("#cellNum").val($("#cellNumTxt").val());
			        	
			        	$("#btnReqAuth").hide();
			        	$("#cellNumTxt").attr("disabled", true);
			        	
			        	$("#authInputNum").show();
			    		$("#btnAuthCfm").show();
	        		}
	        	} catch (e) {;}
	        },
	        complete : function() {
	        	authrequest = false;
	        }
    	});
	};
	
	var authresponse = false;
	// 인증번호 확인요청
	var btnAuthCfm = function() {
		var cellNum = $("#cellNum").val();		var authNum = $("#authInputNum").val();
		
		if (authNum == "") {
 			alert("인증번호를 입력해주세요.");
 			$("#authInputNum").focus();
 			return;
 		}
 		var chkStyle = /[0-9]{6}$/;
 		if (!chkStyle.test(authNum)) {
 			alert("인증번호는6자리  숫자만 입력해주세요.");
 			$("#authInputNum").focus();
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
	        	if (authresponse)
	        		xhr.abort();
	        	
	        	authresponse = true;
	        },
	        success: function(e){
	        	console.log(e);
	        	try {
		            if (e.result.authResult == 'SUCCESS') {
		            	$("#authNum").val(authNum);
		            	$("#authKey").val(e.result.authKey);
		            	
		            	$("#authInputNum").hide();
			    		$("#btnAuthCfm").hide();
		            }
	        	} catch(e) {;}
	        },
	        complete : function() {
	        	authresponse = false;
	        }
    	});
	};

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
			var pwd = $("#password").val(); 
			if (pwd == "") {
				alert("비밀번호를 입력해주세요.");
				$("#password").focus();
				return;
			} else {
				var regexNo = /(\w)\1\1\1/;
				if (!regexNo.test(pwd)) {
					if (!stck(pwd, 4)) {
						alert("4자리 이상 연속된 문자를 사용할 수 없습니다.");
						return;
					}
				} else {
					alert("4자리 이상 동일한 문자를 사용할 수 없습니다.");
					return;
				}
			}
			if (!ChkPwd(pwd)) {
				alert("비밀번호는 영소ㆍ대, 숫자, 특수문자 3종류 이상으로 8자이상 20자 이하로 구성되어야 합니다.");
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
			if ($("#userNm").val() == "") {
				alert("이름을 입력해주세요.");
				$("#userNm").focus();
				return;
			}
			if ($("#dptNm").val() == "") {
				alert("부서명을 입력해주세요.");
				$("#dptNm").focus();
				return;
			}
			if (!PhNumber('사무실 전화', $("#officeTelNum").val())) {
				$("#officeTelNum").focus();
	 			return;
	 		}
			if (!CnkEmail($("#emlAddr").val())) {
				alert("올바른 이메일 주소를 입력해주세요.");
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

	var checkstatus = false;
	// 아이디 중복확인
	var checkUserId = function(e) {
		var getId = $("#userIdTxt").val();
		
		if (getId == "") {
			alert("사용할 아이디를 입력해주세요.");
			$("#userIdTxt").focus();
			return;
		}
		if (!ChkId(getId)) {
			alert("아이디는 영문/숫자 조합으로 8자이상 20자이하로 입력해주세요.");
			$("#userIdTxt").focus();
			return;
		}

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
<input type="hidden" name="authNum" id="authNum" />
<input type="hidden" name="authKey" id="authKey" />
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
					<th>이름</th>
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
						<a href="javascript:void(0)" class="btn btn-sm btn-info" onclick="btnReqAuth()" id="btnReqAuth">인증받기</a>
						<input type="text" id="authInputNum" name="authInputNum" maxlength="6" placeholder="인증번호"/>
						<a href="javascript:void(0)" class="btn btn-sm btn-info" onclick="btnAuthCfm()" id="btnAuthCfm">확인</a>
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