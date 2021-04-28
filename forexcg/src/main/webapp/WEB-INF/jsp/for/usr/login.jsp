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
	$(document).ready(function() {
		$("form").submit(function(event) {
			var loginid = $("#loginId").val();
			var password = $("#password").val();
			
			if (loginid == "") {
				alert("아이디를 입력해주세요.");
				event.preventDefault();
				return;
			}
			if (password == "") {
				alert("비밀번호를 입력해주세요.");
				event.preventDefault();
				return;
			}
			
			$.ajax({
		        type:"POST",
		        url:"/loginAction.do",
		        data : {
		        	"loginId":loginid, 
		        	"password":password
		        },
		        success: function(e){
		            console.log(e);
		        },
		        error: function(xhr, status, error) {
		            alert(error);
		        }  
		    });
		})
	});
	
</script>
</head>

<body>

<div id="popLayerBg"></div>

<div class="login_bg">
	<div id="login">	
		<h1><img src="img/ci_s.png" alt="ci"><small>외환정보시스템</small></h1>
			<form id="formLoginin" name="formLoginin" method="post" action="loginAction.do">
			<fieldset>
				<input type="text" class="line" maxlength="20" name="loginId" id="loginId"  placeholder="아이디" />
				<input type="password" class="line" maxlength="25" name="password" id="password" placeholder="비밀번호" />
				
				<button type="button" id="btnLogin" name="btnLogin" class="btn btn_submit">login</button>
			</fieldset>
			</form>
			<div class="btn_area">
				<a href="javascript:;" class="btn_idsearch"><i class="fa fa-user-o" aria-hidden="true"></i> 아이디 찾기</a>
				<a href="javascript:;" class="btn_pwdsearch"><i class="fa fa-lock" aria-hidden="true"></i> 비밀번호 찾기</a>
				<a href="join.htm" class="btn_join"><i class="fa fa-sign-in" aria-hidden="true"></i> 회원가입</a>
			</div>
		</div>	
	</div>
</body>

<!--+++ OTP 팝업 +++--->
<div class="pop_layer pop_otp">
	<header class="pop_header">
		<h2>2차 인증번호(OTP) 입력</h2>
		<a href="javascript:;" class="btn_close">창닫기</a>
	</header>
	<div class="pop_con">
		<input type="text" placeholder="인증번호" class="line">
		<p>*카카오알림톡으로 전송한 인증번호를 입력하세요.</p>
		<dl>
			<dt>입력 가능 시간</dt>
			<dd><strong>90</strong>초</dd>
		</dl>
		<div class="btn_area">
			<a href="index.htm" class="btn btn-primary">확인</a>
			<a href="javascript:;" class="btn btn-default">취소</a>
		</div>
	</div>
</div>
<!--+++ /OTP 팝업 +++--->

<!--+++ ID 찾기 팝업 +++--->
<div class="pop_layer pop_idsearch">
	<header class="pop_header">
		<h2>아이디 찾기</h2>
		<a href="javascript:;" class="btn_close">창닫기</a>
	</header>
	<div class="pop_con">
		<input type="text" placeholder="휴대폰 번호" class="line">
		<input type="text" placeholder="성명" class="line">
		<div class="btn_area">
			<a href="javascript:;" class="btn btn-primary">확인</a>
			<a href="javascript:;" class="btn btn-default">취소</a>
		</div>
	</div>
</div>
<!--+++ /ID 찾기 팝업 +++--->

<!--+++ 비밀번호 찾기 팝업 +++--->
<div class="pop_layer pop_pwdsearch">
	<header class="pop_header">
		<h2>비밀번호 찾기</h2>
		<a href="javascript:;" class="btn_close">창닫기</a>
	</header>
	<div class="pop_con">
		<input type="text" placeholder="휴대폰 번호" class="line">
		<input type="text" placeholder="성명" class="line">
		<input type="text" placeholder="ID" class="line">
		<div class="btn_area">
			<a href="javascript:;" class="btn btn-primary">확인</a>
			<a href="javascript:;" class="btn btn-default">취소</a>
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
	$('#login .btn_idsearch').click(function(){ // ID찾기 팝업
		$('#popLayerBg').css('display','block');
		$('.pop_idsearch').css('display','block');
	});
	$('.pop_idsearch .btn_close').click(function(){
		$('#popLayerBg').css('display','none');
		$('.pop_idsearch').css('display','none');
	});
	$('.pop_idsearch .btn-default').click(function(){
		$('#popLayerBg').css('display','none');
		$('.pop_idsearch').css('display','none');
	});
	$('#login .btn_pwdsearch').click(function(){ // 비밀번호찾기 팝업
		$('#popLayerBg').css('display','block');
		$('.pop_pwdsearch').css('display','block');
	});
	$('.pop_pwdsearch .btn_close').click(function(){
		$('#popLayerBg').css('display','none');
		$('.pop_pwdsearch').css('display','none');
	});
	$('.pop_pwdsearch .btn-default').click(function(){
		$('#popLayerBg').css('display','none');
		$('.pop_pwdsearch').css('display','none');
	})
});
</script>

</body>
</html>