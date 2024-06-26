<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/mngr/css/style.css"/>
<script src="<%=request.getContextPath()%>/mngr/logon/mlogin.js?ver=1"></script>

<c:if test="${empty sessionScope.id}">
<div id="status">
<input id="id" name="id" type="text" size="20" maxlength="50" placeholder="아이디">
<input id="passwd" name="passwd" type="password" size="20" placeholder="비밀번호" maxlength="16">
<button id="login" onclick="login()">로그인</button>
</div>
</c:if>

<c:if test="${!empty sessionScope.id}">
<div id="status">
<p>관리자님 환영합니다. <br>
오늘도 <span>CRUELLA</span>에서 일해주시는 관리자님께 감사드리며 좋은 하루 보내세요.</p>
<button id="logout" onclick="logout()">로그아웃</button>
</div>
</c:if>