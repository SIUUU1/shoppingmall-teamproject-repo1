<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<script src="/ClothsMall/member/login.js?ver=5"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>

<c:if test="${empty sessionScope.id}">
	<div id="login-box-back">
		<div id="login-box">
			<div id="lStatus">
				<h1 id="title">로그인</h1>
				<div class="middle-top">
					<input id="member_id" name="member_id" type="text" size="20"
						maxlength="50" placeholder="아이디"> <input
						id="member_passwd" name="member_passwd" type="password" size="20"
						maxlength="16" placeholder="패스워드">
				</div>
				<div class="bottom">
					<button id="uLogin">로그인</button>
					<button id="uRes">회원가입</button>
				</div>
			</div>
		</div>
	</div>
</c:if>

<c:if test="${!empty sessionScope.id}">
<div id="mypage">
	<h1 id="title">마이페이지</h1>
	<!--customerInfo-->
	<h3>${m.getMember_name()}님 고객 정보</h3>
	<table>
		<tr>
			<td>등급</td>
			<td>마일리지</td>
			<td>포인트</td>
		</tr>
		<tr>
			<td>${m.getMember_grade()}</td>
			<td>${m.getMileage()}</td>
			<td>${m.getPoint()}</td>
		</tr>
	</table>
	<!--middle-->
	<div id="mypageBox">
	<div>
	 <a href="${pageContext.request.contextPath}/modify.do?member_id=${sessionScope.id}"><i class="fa-solid fa-user"></i><br><span>내 정보수정</span></a>
	</div>
	<div>
	 <a href="${pageContext.request.contextPath}/cartList.do?member_id=${sessionScope.id}"><i class="fa-solid fa-cart-shopping"></i><br><span>장바구니</span></a>
	</div>
	<div>
	 <a href="${pageContext.request.contextPath}/buyList.do?member_id=${sessionScope.id}"><i class="fa-solid fa-truck"></i><br><span>구매내역</span></a>
	</div>
	<div>
	 <a href="${pageContext.request.contextPath}/increasePoint.do?member_id=${sessionScope.id}"><i class="fa-solid fa-coins"></i><br><span>포인트충전</span></a>
	</div>
	</div>
</div>
</c:if>
