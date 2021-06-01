<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<div class="info_area">
			<div class="user"><i class="fa fa-user-o" aria-hidden="true"></i> <c:out value="${loginVo.userNm }"></c:out> <a href="javascript:;" class="btn btn-xs">정보수정</a></div>
			<div class="timer"><i class="fa fa-clock-o" aria-hidden="true"></i> 남은시간 : 54분 41초 <a href="javascript:;" class="btn btn-xs">시간연장</a></div>
			<a href="javascript:;" class="btn btn-primary btn_help"><i class="fa fa-question-circle" aria-hidden="true"></i> 도움말</a>
			<a href="/logout.do" class="btn btn-info"><i class="fa fa-sign-out" aria-hidden="true"></i> 로그아웃</a>
		</div>