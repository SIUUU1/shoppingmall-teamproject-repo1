<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/css/style.css" /><!-- 관련 css 추가후 경로 확인해주세요.-->
<div id="header">
	<div id="logo" class="box">
		<img class="noborder" id="logo"
			src="</images/logo.png" /><!--images 폴더 생성및 아미지 추가하고 경로 확인해주세요.-->
	</div>
	<div id="auth" class="box">
		<%-- <c:if test="${type == 0}">
			<jsp:include page="mngr/logon/mLoginForm.jsp" />
		</c:if>
		<c:if test="${type == 1}">
			<jsp:include page="member/loginForm.jsp" />
		</c:if> --%>
	</div>
</div>
<div id="content" class="box2">
	<jsp:include page="${cont}" />