<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- header -->
<div id="headfix"> </div>
<header>
<div class="top">
  <a href="${pageContext.request.contextPath}/index.do"><h1 class="title">Cruella</h1></a>
  <div class="userIcon">
  <div class="search">
  <input type="text">
  <!--검색기능 넣기-->
  <a href="#"><i class="fa-solid fa-magnifying-glass"></i></a>
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
				<a href="${pageContext.request.contextPath}/loginForm.do?member_id=${sessionScope.id}"><li>MY PAGE</li></a>
				<!--자주하는 질문-->
				<a href="#"><li>CUSTOMER SERVICE</li></a>
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
			</ul>
			<ul>
			</ul>
		</nav>
</header>
</body>
</html>