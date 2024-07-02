<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/cart/css/style.css" />
<script src="<%=request.getContextPath()%>/cart/cartList.js"></script>

<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh"
		content="0;url=<%=request.getContextPath()%>/index.do">
</c:if>
<div id="category" class="box2">
	<ul>
		<li><a
			href="<%=request.getContextPath()%>/list.do?cloth_category=1000">TOP</a>
		<li><a
			href="<%=request.getContextPath()%>/list.do?cloth_category=2000">BOTTOM</a>
		<li><a
			href="<%=request.getContextPath()%>/list.do?cloth_category=3000">OUTER</a>
		<li><a
			href="<%=request.getContextPath()%>/list.do?cloth_category=4000">SHOE</a>
		<li><a
			href="<%=request.getContextPath()%>/list.do?cloth_category=5000">ACCESSORY</a>
		<li><a
			href="<%=request.getContextPath()%>/list.do?cloth_category=all">ALL</a>
	</ul>
	<hr>
</div>
<div id="goShop">
	<button id="shopMain">&lt; 메인으로</button>
	<button id="conShopping">쇼핑계속 &gt;</button>
</div>
<div id="cartList">
	<c:if test="${count == 0}">
		<p id="emptyList">장바구니에 담긴 물품이 없습니다</p>
	</c:if>
	<c:if test="${count > 0}">
		<table>
			<tr id="listMenu">
				<td width="350">상품명</td>
				<td width="100">카테고리</td>
				<td width="100">브랜드</td>
				<td width="80">사이즈</td>
				<td width="100">금액</td>
				<td width="150">수량</td>
				<td width="150">총액</td>
			</tr>
			<c:set var="total" value="0" />
			<c:forEach var="cart" items="${cartLists}">
				<tr id="listView">
					<td width="350"><img
						src="<%=request.getContextPath()%>/clothImage/${cart.getCloth_image()}"
						class="cartimage"><br>${cart.getCloth_name()}</td>
					<td width="100"><c:choose>
							<c:when test="${cart.getCloth_category()==1000}">TOP</c:when>
							<c:when test="${cart.getCloth_category()==2000}">BOTTOM</c:when>
							<c:when test="${cart.getCloth_category()==3000}">OUTER</c:when>
							<c:when test="${cart.getCloth_category()==4000}">SHOE</c:when>
							<c:when test="${cart.getCloth_category()==5000}">ACCESSORY</c:when>
						</c:choose></td>
					<td width="100">${cart.getCloth_brand()}</td>
					<td width="80">${cart.getCloth_size()}</td>
					<td width="100"><c:set var="price"
							value="${cart.getCloth_price()}" /> <c:set var="rate"
							value="${cart.getDiscount_rate()}" /> <fmt:parseNumber
							var="rPrice" value="${price*(100.0-rate)/100}" /> <!-- ㄴ real Price 판매가 number 값 표시 -->
						<c:choose>
							<c:when test="${rate>0}">
						정가&nbsp;<del>
									<fmt:formatNumber value="${price}" type="currency" />
								</del>
								<br>
								<span style="color: red">-${rate}%</span>
								<br>
								<b>판매가&nbsp;<fmt:formatNumber value="${rPrice}"
										type="currency" /></b>
							</c:when>
							<c:otherwise>
						정가&nbsp;<fmt:formatNumber value="${price}" type="currency" />
								<br>
								<b>판매가&nbsp;<fmt:formatNumber value="${rPrice}"
										type="currency" /></b>
							</c:otherwise>
						</c:choose> <!-- ㄴ view Price 판매가 원화 표시 --></td>
					<td width="150">
						<!-- 수정 -->
						<form action="<%=request.getContextPath()%>/cartUpdatePro.do">
							<input type="hidden" name="cart_id" value="${cart.getCart_id()}">
							<input type="text" name="quantity" size="5"
								value="${cart.getQuantity()}">
							<button type="submit">수정</button>
						</form>
					</td>
					<td align="center" width="150"><c:set var="amount"
							value="${cart.getQuantity()*rPrice}" /> <c:set var="total"
							value="${total+amount}" /> <!-- 합계 / 총액 연산 --> 합계&nbsp; <fmt:formatNumber
							value="${amount}" type="currency" />
						<button id="deleteList" name="${cart.getCart_id()}"
							onclick="delList(this)">삭제</button></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" id="totalPrice">총 금액&nbsp;<fmt:formatNumber
						value="${total}" type="currency" />
				</td>
			</tr>
		</table>
		<div id="cartInteract">
			<form id="cartForm" method="post"
				action="<%=request.getContextPath()%>/buyForm.do">
				<input type="hidden" name="member_id" value="${sessionScope.id}">
				<input type="submit" value="구매하기">
			</form>
			<form id="cartClearForm" method="post"
				action="<%=request.getContextPath()%>/deleteCart.do">
				<input type="hidden" name="list" value="all"> <input
					type="hidden" name="member_id" value="${sessionScope.id}">
				<input type="submit" value="장바구니 비우기">
			</form>
		</div>
	</c:if>
</div>
