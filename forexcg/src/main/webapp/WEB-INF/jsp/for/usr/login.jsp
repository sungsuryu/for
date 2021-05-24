<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/for/inc/_header.jsp" %>
<script type="text/javascript" src="<c:url value='/js/join.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		var loginstatus = false;
		var authstatus = false;
		
		$("#btnLogin").click(function(event) {
			var loginid = $("#loginId").val();
			var password = $("#password").val();
			var uuid = $("#viewUid").text();
			
			if (loginid == "") {
				alert("아이디를 입력해주세요.");
				$("#loginId").focus();
				event.preventDefault();
				return;
			}
			if (password == "") {
				alert("비밀번호를 입력해주세요.");
				$("#password").focus();
				event.preventDefault();
				return;
			}

			$.ajax({
		        type:"POST",
		        url:"/loginAction.ajax",
		        data : {
		        	"loginId":loginid, 
		        	"password":password, 
		        	"uuId":uuid, 
		        },
		        beforeSend: function(xhr, opts) {
		        	if (loginstatus)
		        		xhr.abort();
		        	
		        	loginstatus = true;
		        }, 
		        success: function(e){
		        	try {
			            if (e.result.status == 'SUCCESS') {
			            	requestOTP(e.result.loginId);
			            } else {
			            	alert("계정을 찾을 수 없습니다.");
			            	loginFormClear();
			            }
		        	} catch (e) {
		        		;
		        	}
		        },
		        error: function(xhr, status, error) {
		            alert(error);
		        }, 
		        complete : function() {
		        	loginstatus = false;
		        }
		    });
		});

		$("#linkOTPlogin").click(function(event) {
			var returnId = $("#returnLoginId").text();
			var authNum = $("#authNum").val();
			console.log(returnId);
			console.log('-----------');
			if (authNum == "") {
				alert('인증번호를 입려해주세요.');
				return;
			}
	 		$.ajax({
		        type:"POST",
		        url:"/otpAction.ajax",
		        data : {
		        	"loginId":returnId, 
		        	"authNum":authNum
		        },
		        beforeSend: function(xhr, opts) {
		        	if (authstatus)
		        		xhr.abort();
		        	
		        	authstatus = true;
		        },
		        success: function(e){
		        	authResult(e);
		        },
		        complete : function() {
		        	authstatus = false;
		        }
	    	});
		});
		// OTP취소
		$(".linkOTPcancel").click(function(event) {
			expireOTP($("#returnLoginId").text());
		});
		
		var findidstatus = false;
		$("#btnFindId").click(function(event) {
			var cellNumTxt = $("#findIdCellNum").val();

	 		if (!PhNumber('휴대폰', cellNumTxt)) {
	 			$("#findIdCellNum").focus();
	 			return;
	 		}
	 		if ($("#findIdUserNm").val() == "") {
				alert("이름을 입력해주세요.");
				$("#findIdUserNm").focus();
				return;
			}
	 		
	 		$.ajax({
		        type:"POST",
		        url:"/findId.ajax",
		        data : {
		        	"cellNum":cellNumTxt, 
		        	"userNm":$("#findIdUserNm").val()
		        },
		        beforeSend: function(xhr, opts) {
		        	if (findidstatus)
		        		xhr.abort();
		        	
		        	findidstatus = true;
		        },
		        success: function(e){
		        	findIdResult(e);
		        },
		        complete : function() {
		        	findIdDisplay('none');
		        	
		        	findidstatus = false;
		        }
	    	});
		});
		
		var findpwdstatus = false;
		$("#btnFindPwd").click(function(event) {
			var cellNumTxt = $("#findPwdCellNum").val();

	 		if (!PhNumber('휴대폰', cellNumTxt)) {
	 			$("#findPwdCellNum").focus();
	 			return;
	 		}
	 		if ($("#findPwdUserNm").val() == "") {
				alert("이름을 입력해주세요.");
				$("#findPwdUserNm").focus();
				return;
			}
	 		if ($("#findPwdLoginId").val() == "") {
				alert("아이디를 입력해주세요.");
				$("#findPwdLoginId").focus();
				return;
			}
	 		
	 		$.ajax({
		        type:"POST",
		        url:"/findPwd.ajax",
		        data : {
		        	"cellNum":cellNumTxt, 
		        	"userNm":$("#findPwdUserNm").val(), 
		        	"loginId":$("#findPwdLoginId").val()
		        },
		        beforeSend: function(xhr, opts) {
		        	if (findpwdstatus)
		        		xhr.abort();
		        	
		        	findpwdstatus = true;
		        },
		        success: function(e){
		        	findPwdResult(e);
		        },
		        complete : function() {
		        	findpwdstatus = false;
		        }
	    	});
		});
		
		// ID찾기
		$("#linkFindId").click(function() {
			$("#findIdCellNum").val("");
			$("#findIdUserNm").val("");
			findIdDisplay('block');
		});
		// ID찾기 취소
		$(".linkFindIdcancel").click(function(event) {
			findIdDisplay('none');
		});
		// 비밀번호찾기
		$("#linkFindPwd").click(function() {
			$("#findPwdCellNum").val("");
			$("#findPwdUserNm").val("");
			$("#findPwdLoginId").val("");
			findPwdDisplay('block');
		});
		// 비밀번호찾기 취소
		$(".linkFindPwdcancel").click(function(event) {
			findPwdDisplay('none');
		});
	});

	var findPwdResult = function(e) {
    	try {
    		var status = e.result.status;
    		var findPwd = e.result.findPwd;
    		
            if (status == 'SUCCESS' && findPwd == 'T') {
            	alert("회원님의 휴대폰번호로 임시비밀번호를 발송 했습니다.");
            } else {
            	alert("회원정보를 찾을 수 없습니다.");
            }
    	} catch (e) {
    		alert('오류가 발생했습니다.\n처음부터 다시 시해도 주시기 바랍니다.');
    		location.href = "/login.do";
    	}
	};
	
	var findIdResult = function(e) {
    	try {
    		var status = e.result.status;
    		var findId = e.result.findId;
    		
            if (status == 'SUCCESS' && findId == 'T') {
            	alert("회원님의 휴대폰번호로 아이디를 발송 했습니다.");
            } else {
            	alert("회원정보를 찾을 수 없습니다.");
            }
    	} catch (e) {
    		alert('오류가 발생했습니다.\n처음부터 다시 시해도 주시기 바랍니다.');
    		location.href = "/login.do";
    	}
	};
	
	var authResult = function(e) {
    	try {
    		var status = e.result.status;
    		var auth = e.result.auth;
    		
            if (status == 'SUCCESS' && auth == 'T') {
            	clearInterval(interval);
            	
            	authNumDisplay('none');
               	loginFormClear();
               	
            	location.href = "/setting/code.do";
            } else {
            	alert("인증번호가 틀립니다.");
            	$("#authNum").val("");
            	$("#authNum").focus();
            }
    	} catch (e) {
    		alert('오류가 발생했습니다.\n처음부터 다시 시해도 주시기 바랍니다.');
    		location.href = "/login.do";
    	}
	};
	
	var expireOTP = function(e) {
		$.ajax({
	        type:"POST",
	        url:"/otpExpire.ajax",
	        data : {
	        	"loginId":e
	        },
	        complete : function() {
	        	$("#optExpTime").text(<c:out value="${authInterval}"/>);
	        	authNumDisplay('none');
	     		loginFormClear();
	        }
    	});
	};
	
	// 인증번호 interval
	var interval;
	var requestOTP = function(loginid, timestamp) {
		var start = <c:out value="${authInterval}"/>;
		$("#returnLoginId").text(loginid);
		
		interval = setInterval(function() {
			if (start < 1) {
				expireOTP(loginid);
				clearInterval(interval);
			} else {
				$("#optExpTime").text(start--);
			}	
		}, 1000);
		
		authNumDisplay('block');
	};

	// 로그인 입력form Clear
	var loginFormClear = function() {
		$("#loginId").val("");
		$("#password").val("");
	};
	
	// 인증번호 입력form Clear
	var authNumDisplay = function(e) {
		$('#popLayerBg').css('display',e);
       	$('.pop_otp').css('display',e);
	};
	// 아이디찾기
	var findIdDisplay = function(e) {
		$('#popLayerBg').css('display',e);
 		$('.pop_idsearch').css('display',e);
	};
	// 비밀번호찾기
	var findPwdDisplay = function(e) {
		$('#popLayerBg').css('display',e);
		$('.pop_pwdsearch').css('display',e);
	};
</script>
</head>

<body>

<div id="popLayerBg"></div>

<div class="login_bg">
	<div id="login">	
		<h1><img src="img/ci_s.png" alt="ci"><small>외환정보시스템</small></h1>
			<form id="formLoginin" name="formLoginin" method="post" action="loginAction.do">
			
			<fieldset>
				<div id="viewUid" class="dispNon"><c:out value="${UUID}"/></div>
				<div id="returnLoginId" class="dispNon"></div>
				<input type="text" class="line" maxlength="20" name="loginId" id="loginId"  placeholder="아이디" />
				<input type="password" class="line" maxlength="25" name="password" id="password" placeholder="비밀번호" />
				
				<button type="button" id="btnLogin" name="btnLogin" class="btn btn_submit">login</button>
			</fieldset>
			</form>
			<div class="btn_area">
				<a href="javascript:void(0)" class="btn_idsearch" id="linkFindId"><i class="fa fa-user-o" aria-hidden="true"></i> 아이디 찾기</a>
				<a href="javascript:void(0)" class="btn_pwdsearch" id="linkFindPwd"><i class="fa fa-lock" aria-hidden="true"></i> 비밀번호 찾기</a>
				<a href="/join.do" class="btn_join"><i class="fa fa-sign-in" aria-hidden="true"></i> 회원가입</a>
			</div>
		</div>	
	</div>
</body>

<!--+++ OTP 팝업 +++--->
<div class="pop_layer pop_otp">
	<header class="pop_header">
		<h2>2차 인증번호(OTP) 입력</h2>
		<a href="javascript:void(0)" class="btn_close linkOTPcancel">창닫기</a>
	</header>
	<div class="pop_con">
		<input type="text" placeholder="인증번호" class="line" maxlength="6" id="authNum" name="authNum" />
		<p>*카카오알림톡으로 전송한 인증번호를 입력하세요.</p>
		<dl>
			<dt>입력 가능 시간</dt>
			<dd><strong id="optExpTime">90</strong>초</dd>
		</dl>
		<div class="btn_area">
			<a href="javascript:void(0)" class="btn btn-primary" id="linkOTPlogin">확인</a>
			<a href="javascript:void(0)" class="btn btn-default linkOTPcancel">취소</a>
		</div>
	</div>
</div>
<!--+++ /OTP 팝업 +++--->

<!--+++ ID 찾기 팝업 +++--->
<div class="pop_layer pop_idsearch">
	<header class="pop_header">
		<h2>아이디 찾기</h2>
		<a href="javascript:void(0)" class="btn_close linkFindIdcancel">창닫기</a>
	</header>
	<div class="pop_con">
		<input type="text" name="findIdCellNum" id="findIdCellNum" placeholder="휴대폰 번호" class="line">
		<input type="text" name="findIdUserNm" id="findIdUserNm" placeholder="성명" class="line">
		<div class="btn_area">
			<a href="javascript:void(0)" class="btn btn-primary" id="btnFindId">확인</a>
			<a href="javascript:void(0)" class="btn btn-default linkFindIdcancel">취소</a>
		</div>
	</div>
</div>
<!--+++ /ID 찾기 팝업 +++--->

<!--+++ 비밀번호 찾기 팝업 +++--->
<div class="pop_layer pop_pwdsearch">
	<header class="pop_header">
		<h2>비밀번호 찾기</h2>
		<a href="javascript:void(0)" class="btn_close linkFindPwdcancel">창닫기</a>
	</header>
	<div class="pop_con">
		<input type="text" name="findPwdCellNum" id="findPwdCellNum" placeholder="휴대폰 번호" class="line">
		<input type="text" name="findPwdUserNm" id="findPwdUserNm" placeholder="성명" class="line">
		<input type="text" name="findPwdLoginId" id="findPwdLoginId" placeholder="ID" class="line">
		<div class="btn_area">
			<a href="javascript:void(0)" class="btn btn-primary" id="btnFindPwd">확인</a>
			<a href="javascript:void(0)" class="btn btn-default linkFindPwdcancel">취소</a>
		</div>
	</div>
</div>
<!--+++ /비밀번호 찾기 팝업 +++--->

<script>
$(function(){
// 	$('#login .btn_submit').click(function(){ // OTP 팝업
// 		$('#popLayerBg').css('display','block');
// 		$('.pop_otp').css('display','block');
// 	});
	$('.pop_otp .btn_close').click(function(){
		$('#popLayerBg').css('display','none');
		$('.pop_otp').css('display','none');
	});
	$('.pop_otp .btn-default').click(function(){
		$('#popLayerBg').css('display','none');
		$('.pop_otp').css('display','none');
	});
	
	$('.pop_idsearch .btn_close').click(function(){
		$('#popLayerBg').css('display','none');
		$('.pop_idsearch').css('display','none');
	});
// 	$('#login .btn_idsearch').click(function(){ // ID찾기 팝업
//		$('#popLayerBg').css('display','block');
//		$('.pop_idsearch').css('display','block');
//	});
// 	$('.pop_idsearch .btn-default').click(function(){
// 		$('#popLayerBg').css('display','none');
// 		$('.pop_idsearch').css('display','none');
// 	});
// 	$('#login .btn_pwdsearch').click(function(){ // 비밀번호찾기 팝업
// 		$('#popLayerBg').css('display','block');
// 		$('.pop_pwdsearch').css('display','block');
// 	});
// 	$('.pop_pwdsearch .btn_close').click(function(){
// 		$('#popLayerBg').css('display','none');
// 		$('.pop_pwdsearch').css('display','none');
// 	});
	$('.pop_pwdsearch .btn-default').click(function(){
		$('#popLayerBg').css('display','none');
		$('.pop_pwdsearch').css('display','none');
	})
});
</script>

</body>
</html>