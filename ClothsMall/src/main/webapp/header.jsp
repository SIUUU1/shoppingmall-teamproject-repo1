<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<!-- header -->
<div id="headfix"> </div>
<header>
<div class="top">
  <a href="${pageContext.request.contextPath}/index.do"><h1 class="title">Cruella</h1></a>
  <div class="userIcon">
  <div class="search">
   <!--검색기능 넣기-->
  <form id="search_form" action="${pageContext.request.contextPath}/list.do" method="post">
  <input type="text" name="search" id="search" size="40" maxlength="40" />
	<div id="button-search">
	        <button id="searchBtn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
	 </div>
  </form>  
  </div>
  <a href="${pageContext.request.contextPath}/cartList.do?member_id=${sessionScope.id}"><i class="fa-solid fa-cart-shopping"></i></a>
  <a href="${pageContext.request.contextPath}/buyList.do?member_id=${sessionScope.id}"><i class="fa-solid fa-truck"></i></a>
  <a href="${pageContext.request.contextPath}/increasePoint.do?member_id=${sessionScope.id}"><i class="fa-solid fa-coins"></i></a>
  <a href="${pageContext.request.contextPath}/loginForm.do?member_id=${sessionScope.id}"><i class="fa-solid fa-user"></i></a>
  </div>
</div>
  <!-- main-menu -->
		<nav id="categoryGridTitle">
			<ul onmouseover="displayMenu('mouseover')" onmouseout="displayMenu('mouseout')">
				<a href="${pageContext.request.contextPath}/index.do"><li>HOME</li></a>
				<a href="#"><li>CATEGORY</li></a>
				<a href="#"><li>CART</li></a>
				<a href="#"><li>MY PAGE</li></a>
				<!--자주하는 질문-->
				<a href="${pageContext.request.contextPath}/faqList.do"><li>CUSTOMER SERVICE</li></a>
			</ul>
		</nav>
  <!-- main-menu-Content -->		
		<nav id="categoryGridContent" onmouseover="displayMenu('mouseover')" onmouseout="displayMenu('mouseout')">
			<ul>
			</ul>
			<ul>
			<a href="${pageContext.request.contextPath}/list.do?cloth_category=all"><li>ALL</li></a>
			<a href="${pageContext.request.contextPath}/list.do?cloth_category=1000"><li>TOP</li></a>
            <a href="${pageContext.request.contextPath}/list.do?cloth_category=3000"><li>OUTER</li></a>
            <a href="${pageContext.request.contextPath}/list.do?cloth_category=2000"><li>BOTTOM</li></a>
            <a href="${pageContext.request.contextPath}/list.do?cloth_category=4000"><li>SHOE</li></a>
            <a href="${pageContext.request.contextPath}/list.do?cloth_category=5000"><li>ACCESSORY</li></a>
			</ul>
			<ul>
			<a href="${pageContext.request.contextPath}/cartList.do?member_id=${sessionScope.id}"><li>MY CART</li></a>
            <a href="${pageContext.request.contextPath}/buyList.do?member_id=${sessionScope.id}"><li>MY RECEIPT</li></a>
			</ul>
			<ul>
			<a href="${pageContext.request.contextPath}/loginForm.do?member_id=${sessionScope.id}"><li>MY INFO</li></a>
			
			<c:if test="${!empty sessionScope.id}">
			<a href="#" onclick="uLogout(event)"><li>LOGOUT</li></a>
			</c:if>
			
			</ul>
			<ul>
			</ul>
		</nav>
</header>
</body>
</html>