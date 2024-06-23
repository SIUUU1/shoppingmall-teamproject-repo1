<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<script src="<%=request.getContextPath()%>/shop/clothContent.js"></script>

<div id="cata" class="box2">
	<ul>
		<li><a
			href="<%=request.getContextPath()%>/list.do?cloth_category=1000">상의</a>
		<li><a
			href="<%=request.getContextPath()%>/list.do?cloth_category=2000">하의</a>
		<li><a
			href="<%=request.getContextPath()%>/list.do?cloth_category=3000">아우터</a>
		<li><a
			href="<%=request.getContextPath()%>/list.do?cloth_category=4000">신발</a>
		<li><a
			href="<%=request.getContextPath()%>/list.do?cloth_category=5000">패션소품</a>
		<li><a
			href="<%=request.getContextPath()%>/list.do?cloth_category=all">전체</a>
	</ul>
</div>

<div id="showCloth">
	<table class="vhcenter">
	<tr height="30">
		<td rowspan="6" width="150">
		<img src="<%=request.getContextPath()%>/clothImage/${cloth.getCloth_image()}" class="contentimage"></td>
		<td width="500"><b>${cloth.getCloth_name()}</b></td>
	</tr>
	<tr><td width="500">카테고리 : ${cloth.getAuthor()}</td></tr>
	<tr><td width="500">브랜드 : ${cloth.getCloth_brand()}</td></tr>
	<tr><td width="500">사이즈선택 : 
	<select id="cloth_size">
					<option value="S">S</option>
					<option value="M">M</option>
					<option value="L">L</option>
					<option value="XL">XL</option>
	</select></td>
	</tr>
	
	<tr><td width="500">
	<c:set var="price" value="${cloth.getCloth_price()}"/>
	<c:set var="rate" value="${cloth.getDiscount_rate()}"/>
	정가 : <fmt:formatNumber value="${price}" type="number" pattern="#,##0"/>원<br>
	<strong class="bred">판매가:<c:set var="rPrice" value="${price*((100.0-rate)/100)}"/>
	<fmt:formatNumber value="${rPrice}" type="number" pattern="#,##0"/>원</strong>
	<tr>
		<td width="500">
	
		<c:if test="${!empty sessionScope.id}">
		<c:if test="${cloth.getCloth_count()==0}">
		<p>일시품절
		</c:if>
		<c:if test="${cloth.getCloth_count()>=1}">
		수량 : <input type="text" size="5" id="buy_count" value="1"> 개
		</c:if>
		<input type="hidden" id="cloth_id" value="${cloth_id}">
		<input type="hidden" id="cloth_image" value="${cloth.getBook_image()}">
		<input type="hidden" id="cloth_name" value="${cloth.getCloth_name()}">
		<input type="hidden" id="buy_price" value="${rPrice}">
		<input type="hidden" id="cloth_category" value="${cloth_category}">
		<input type="hidden" id="cloth_gender" value="${cloth.getCloth_gender()}">
		<input type="hidden" id="buyer" value="${sessionScope.id}">
		<button id="insertCart">장바구니에 담기</button>
		</c:if>
		<c:if test="${empty sessionScope.id}">
		<c:if test="${cloth.getCloth_count()==0}"><p>일시품절</c:if>
		<p>제품을 구매하시려면 로그인 하세요.
		</c:if>
		<button id="list" onclick="list()" >목록으로</button>
		<button id="shopMain" onclick="shopMain()" >메인으로</button>
		</td>
	</tr>
	<tr class="ch">
	<td colspan="2" class="hleft">${cloth.getCloth_content()}</td>
	</tr>
</table>
</div>
<!--qna  -->
	