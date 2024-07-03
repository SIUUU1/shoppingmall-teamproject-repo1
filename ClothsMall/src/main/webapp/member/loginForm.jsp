<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/ClothsMall/member/login.css" />
<script src="/ClothsMall/member/login.js?ver=5"></script>

<c:if test="${empty sessionScope.id}">
<div class="header-fix">&nbsp;</div>
	<div id="lStatus">
		<label for="member_id">아이디</label> <input id="member_id"
			name="member_id" type="text" size="20" maxlength="50"> <label
			for="member_passwd">비밀번호</label> <input id="member_passwd"
			name="member_passwd" type="password" size="20" maxlength="16">
		<button id="uLogin">로그인</button>
		<button id="uRes">회원가입</button>
	</div>
</c:if>

<c:if test="${!empty sessionScope.id}">
<div class="header-fix">&nbsp;</div>
	<div id="lStatus">
		<div class="user-actions">
			<div id="logout-box">
				<button id="uLogout" onclick="uLogout()">로그아웃</button>
				<button id="uUpdate" onclick="uUpdate()">내 정보</button>
			</div>
			<div>
				<form id="cartForm" method="post" action="/ClothsMall/cartList.do">
					<input type="hidden" name="member_id" value="${sessionScope.id}">
					<button type="submit" name="cart">장바구니</button>
				</form>
			</div>
			<div>
				<form id="buyForm" method="post" action="/ClothsMall/buyList.do">
					<input type="hidden" name="member_id" value="${sessionScope.id}">
					<button type="submit" name="buy">구매내역</button>
				</form>
			</div>
			<div>
				<form id="point" method="post" action="/ClothsMall/increasePoint.do">
                   <input type="hidden" name="member_id" value="${sessionScope.id}">
                   <button type="submit" name="buy" value="포인트충전"></form>
			</div>
		</div>
	</div>
</c:if>
