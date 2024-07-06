<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cruella</title>
  <script src="${pageContext.request.contextPath}/shop/clothContent.js"></script>
  <script src="https://kit.fontawesome.com/3842b33e0f.js" crossorigin="anonymous"></script>
</head>
<body onload="call_js()">
<!-- sildshow -->
<article class="slideshow-box">
	<div class="sildshow">
		<div class="slidshow_imgs">
			<a href="#"><img src="${pageContext.request.contextPath}/images/cloth1.jpg" alt="slide1"></a>
			<a href="#"><img src="${pageContext.request.contextPath}/images/cloth2.jpg" alt="slide2"></a>
			<a href="#"><img src="${pageContext.request.contextPath}/images/cloth3.jpg" alt="slide3"></a>
			<a href="#"><img src="${pageContext.request.contextPath}/images/cloth4.jpg" alt="slide4"></a>
		</div>
		<div class="slidshow_nav">
			<a href="#" id="prev"><i class="fa-solid fa-angles-left"></i></a> 
			<a href="#" id="next"><i class="fa-solid fa-angles-right"></i></a>
		</div>
		<div class="indicator">
			<a href="#" class="active"><i class="fa-solid fa-circle"></i></a> 
			<a href="#"><i class="fa-solid fa-circle"></i></a> 
			<a href="#"><i class="fa-solid fa-circle"></i></a> 
			<a href="#"><i class="fa-solid fa-circle"></i></a>
		</div>
	</div>
</article>
 <!-- middle-->  
<!-- 신상 상품 목록 -->
<div class="featured-products">
<c:forEach var="clothList" items="${clothLists}">
<c:set var="cloth_category" value="${clothList[0].getCloth_category()}"/>
		<c:if test="${cloth_category=='1000'}">
			<c:set var="cloth_categoryName" value="상의" />
		</c:if>
		<c:if test="${cloth_category=='2000'}">
			<c:set var="cloth_categoryName" value="하의" />
		</c:if>
		<c:if test="${cloth_category=='3000'}">
			<c:set var="cloth_categoryName" value="아우터" />
		</c:if>
		<c:if test="${cloth_category=='4000'}">
			<c:set var="cloth_categoryName" value="신발" />
		</c:if>
		<c:if test="${cloth_category=='5000'}">
			<c:set var="cloth_categoryName" value="패션소품" />
		</c:if>
		<h3 class="b">${cloth_categoryName} 신상 보기</h3>
		
		<!-- 신상 상품 정보  -->
		<div class="vhcenter">
		<c:forEach var="cloth" items="${clothList}">
		<div class="vhcenter-content">
			<a href="${pageContext.request.contextPath}/clothContent.do?cloth_id=${cloth.getCloth_id()}&cloth_category=${cloth.getCloth_category()}">
			<img src="${pageContext.request.contextPath}/clothImage/${cloth.getCloth_image()}" class="listimage"></a><br>
			<div class="vhcenter-info">
			<span>${cloth.getCloth_brand()}</span><br>
			<a href="${pageContext.request.contextPath}/clothContent.do?cloth_id=${cloth.getCloth_id()}&cloth_category=${cloth.getCloth_category()}">
			${cloth.getCloth_name()}</a><br>
			<c:set var="price" value="${cloth.getCloth_price()}"/>
		<c:set var="rate" value="${cloth.getDiscount_rate()}"/>
		<span class="ratio">${rate}% </span>
		<strong class="bred">
		<fmt:formatNumber value="${price*((100.0-rate)/100)}" type="number" pattern="#,##0"/>원</strong>
		</div>
		</div>
		</c:forEach>
		</div>
		
</c:forEach>
</div>
</body>
</html>