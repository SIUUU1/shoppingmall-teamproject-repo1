<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/cart/css/style.css" />
<script src="<%=request.getContextPath()%>/cart/cartList.js"></script>

<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=<%=request.getContextPath()%>/index.do">
</c:if>
<div id="cata" class="box2">
	<ul>
		<li><a href="<%=request.getContextPath()%>/list.do?cloth_category=1000">상의</a>
		<li><a href="<%=request.getContextPath()%>/list.do?cloth_category=2000">하의</a>
		<li><a href="<%=request.getContextPath()%>/list.do?cloth_category=3000">아우터</a>
		<li><a href="<%=request.getContextPath()%>/list.do?cloth_category=4000">신발</a>
		<li><a href="<%=request.getContextPath()%>/list.do?cloth_category=5000">패션소품</a>
		<li><a href="<%=request.getContextPath()%>/list.do?cloth_category=all">전체</a>
	</ul>
</div>
<div id="goShop">
	<button id="conShopping">쇼핑계속</button>
	<button id="shopMain">메인으로</button>
</div>
<div id="cartList">
	<c:if test="${quantity == 0}">
		<ul>
			<li>장바구니에 담긴 물품이 없습니다.
		</ul>
	</c:if>
	<c:if test="${quantity > 0}">
		<table>
			<tr>
				<td width="350">상품명</td>
				<td width="100">카테고리</td>
				<td width="100">브랜드</td>
				<td width="80">M/W</td>
				<td width="80">사이즈</td>
				<td width="100">금액</td>
				<td width="150">수량</td>
				<td width="150">총액</td>
			</tr>
			<c:set var="total" value="0" />
			<c:forEach var="cart" items="${cartLists}">
				<tr>
					<td width="350">
						<img src="<%=request.getContextPath()%>/clothImage/${cart.getCloth_image()}" class="cartimage">${cart.getCloth_name()}</td>
					<td width="100">${cart.getCloth_category()}</td>
					<td width="100">${cart.getCloth_brand()}</td>
					<td width="80">${cart.getCloth_gender()}</td>
					<td width="80">${cart.getCloth_size()}</td>
					<td width="100">
						<c:set var="price" value="${cloth.getBuy_price()}" />
						<c:set var="rate" value="${cloth.getDiscount_rate()}" />
						<c:set var="cal" value="${price*(100.0-rate)/100}" />
						<fmt:formatNumber var="vPrice" value="${cal}" type="currency" />
						<!-- ㄴ view Price 판매가 원화 표시 -->
						<fmt:formatNumber var="rPrice" value="${cal}" maxFractionDigits="0" />
						<!-- ㄴ real Price 판매가 number 값 표시 -->
						정가&nbsp;
						<fmt:formatNumber value="${price}" type="currency" />
						<br>
						<span style="color: red">-${rate}%</span>
						<br>
						<b>판매가&nbsp;${vPrice}</b>
					</td>
					<td width="150">
						<input type="text" name="quantity" size="5" value="${cart.getQuantity()}">
						<button id="updateSu" name="${cart.getCart_id()},${cart.getQuantity()}" onclick="editSu(this)">수정</button>
					</td>
					<td align="center" width="150">
						<c:set var="amount" value="${cart.getQuantity()*rPrice}" />
						<c:set var="total" value="${total+amount}" />
						<fmt:formatNumber value="${amount}" type="number" pattern="#,##0" />
						원
						<button id="deleteList" name="${cart.getCart_id()}" onclick="delList(this)">삭제</button>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="right" class="b">
					총 금액&nbsp;
					<fmt:formatNumber value="${total}" type="currency" />
				</td>
			</tr>
			<tr height="10">
				<td colspan="5" align="center">
					<div id="cinfo">
						<table>
							<tr>
								<td>
									<form id="cartForm" method="post" action="<%=request.getContextPath()%>/buyForm.do">
										<input type="hidden" name="buyer" value="${sessionScope.id}">
										<input type="submit" value="구매하기">
									</form>
								</td>
								<td>
									<form id="cartClearForm" method="post" action="<%=request.getContextPath()%>/deleteCart.do">
										<input type="hidden" name="list" value="all">
										<input type="hidden" name="buyer" value="${sessionScope.id}">
										<input type="submit" value="장바구니비우기">
									</form>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</c:if>
</div>
