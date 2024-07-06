<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<a href="#"><img src="${pageContext.request.contextPath}/images/woman.jpg" alt="slide1"></a>
			<a href="#"><img src="${pageContext.request.contextPath}/images/shoe.jpg" alt="slide2"></a>
			<a href="#"><img src="${pageContext.request.contextPath}/images/accessory.jpg" alt="slide3"></a>
			<a href="#"><img src="${pageContext.request.contextPath}/images/cloth.jpg" alt="slide4"></a>
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
    <section class="featured-products">
      <h2>신상 모아보기</h2>
      <div class="product-grid">
        <!-- 인기 상품 목록 -->
      </div>
    </section>
 <!-- 카테고리 목록 -->
    <section class="categories">
      <h2></h2>
      <div class="category-grid">
        <!-- 카테고리 목록 -->
      </div>
    </section>
</body>
</html>
