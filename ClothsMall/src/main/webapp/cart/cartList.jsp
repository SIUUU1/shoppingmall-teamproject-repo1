<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<script src="${pageContext.request.contextPath}/cart/cartList.js"></script>
	
	<c:if test="${empty sessionScope.id}">
		<script>
			alert('로그인이 필요한 페이지입니다.');
		</script>
		<meta http-equiv="Refresh"
			content="0;url=/ClothsMall/loginForm.do?member_id=${sessionScope.id}">
	</c:if>
	
		<div id="cartList">
		<h3 id="title">장바구니</h3>
			<c:if test="${count == 0}">
				<p id="emptyList">장바구니에 담긴 물품이 없습니다.</p>
			</c:if>
			
			<c:if test="${count > 0}">
			<div class="clear-box">
				<button onclick="clearList(this)" name="${sessionScope.id}">전체삭제</button>
			</div>	
				<c:set var="total" value="0" />
				<c:forEach var="cart" items="${cartLists}">
					<div class="cartItem">
						<div class="clothBrand">
						<b>${cart.getCloth_brand()}</b>
							<button id="deleteList" name="${cart.getCart_id()}" onclick="delList(this)"><i class="fa-solid fa-x"></i></button>
						</div>
						<div class="cartItem-content">
							<img src="${pageContext.request.contextPath}/clothImage/${cart.getCloth_image()}" class="cartimage">
							<div class="cartItem-content-info">
								<span class="clothName">${cart.getCloth_name()}</span><br> <br>
								사이즈: ${cart.getCloth_size()}<br><br>
								<c:set var="price" value="${cart.getCloth_price()}" />
								<c:set var="rate" value="${cart.getDiscount_rate()}" />
								<fmt:parseNumber var="rPrice" value="${price*(100.0-rate)/100}" />
								<c:choose>
									<c:when test="${rate>0}">
									<span style="color: red">${rate}%</span>
									<b><fmt:formatNumber value="${rPrice}" type="number" pattern="#,##0" />원</b>
									<span class="origin"><fmt:formatNumber value="${price}" type="number" pattern="#,##0" />원</span>
									</c:when>
									<c:otherwise>
									<b><fmt:formatNumber value="${price}" type="number" pattern="#,##0" />원</b>
									</c:otherwise>
								</c:choose>
								<!-- count 수정 -->
								<input type="hidden" id="cart_id" value="${cart.getCart_id()}"> 
								<input type="hidden" id="member_id" value="${sessionScope.id}"> 
								<br><br>
								<table>
						        <tr><td onclick="minus()"> - </td>
						        <td><input type="text" id="quantity" size="5" value="${cart.getQuantity()}"></td>
						        <td onclick="plus()"> + </td></tr>
						        </table>
						        
								<c:set var="amount" value="${cart.getQuantity()*rPrice}" />
								<c:set var="total" value="${total+amount}" />
							</div>
						</div>
					<div class="amountT">
						상품&nbsp;<fmt:formatNumber value="${amount}" type="number" pattern="#,##0" />원
					</div>
					</div>
			</c:forEach>
				<div id="cartInteract">
					<button name="${sessionScope.id}" onclick="cartBuy(this)">총 &nbsp;<fmt:formatNumber value="${total}" type="number" pattern="#,##0" />원 구매하기</button>
				</div>
			</c:if>
		</div>