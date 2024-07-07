<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />

<c:if test="${empty sessionScope.id}">
	<script>alert('로그인이 필요한 페이지입니다.');</script>
  	<meta http-equiv="Refresh" content="0;url=/ClothsMall/loginForm.do?member_id=${sessionScope.id}">
</c:if>

<div id="buyList">
<c:if test="${count == 0}">
<p id="emptyList">구매 목록이 없습니다.</p>
</c:if>

<c:if test="${count > 0}">
	<c:set var="total" value="0" />
	<c:forEach var="i" begin="0" end="${buyLists.size()-1}">
		<c:set var="buylist" value="${buyLists.get(i)}" />
		<c:set var="thisId" value="${buylist.receipt_id}" />
		<c:if test="${i+1 > buyLists.size()-1 }">
			<c:set var="nowId" value="" />
		</c:if>
		<c:if test="${i+1 <= buyLists.size()-1 }">
			<c:set var="nowId" value="${buyLists.get(i+1).receipt_id}" />
		</c:if>
		<hr>
		<p>주문 상품</p>
		<div class="buyList">
			<table class="buylist">
				<tr>
					<th width="150">주문번호</th>
					<th width="300">상품명</th>
					<th width="200">판매가격</th>
					<th width="200">사이즈</th>
					<th width="50">수량</th>
					<th width="100">금액</th>
				</tr>
				<tr>
					<td width="150">${buylist.receipt_id}</td>
					<td width="300"><img
						src="/ClothsMall/clothImage/${buylist.cloth_image}"
						class="cartimage" width="80px" height="120px">${buylist.cloth_name}</td>
					<td width="200"><c:set var="price"
							value="${buylist.cloth_price}" /> <c:set var="rate"
							value="${buylist.discount_rate}" /> <fmt:parseNumber
							var="rPrice" value="${price*(100.0-rate)/100}" /> <!-- ㄴ real Price 판매가 number 값 표시 -->
						정가&nbsp; <fmt:formatNumber value="${price}" type="currency" /> <br>
						<span style="color: red">-${rate}%</span> <br> <b>판매가&nbsp;<fmt:formatNumber
								value="${rPrice}" type="currency" /></b> <!-- ㄴ view Price 판매가 원화 표시 -->
					<td width="50">${buylist.cloth_size}</td>
					<td width="50">${buylist.quantity}</td>
					<td width="100"><c:set var="amount"
							value="${buylist.quantity * rPrice}" /> <c:set var="total"
							value="${total+amount}" /> <fmt:formatNumber value="${amount}"
							type="number" pattern="#,##0" />원</td>
				</tr>
			</table>
		</div>
		<c:if test="${thisId != nowId}">
			<c:forEach var="i" begin="0" end="${receiptLists.size()-1}">
				<c:set var="receiptList" value="${receiptLists.get(i)}" />
				<c:if test="${receiptList.receipt_id eq thisId}">
					<div class="receiptList">
						<p>배송지</p>
						<table class="address buylist">
							<tr>
								<th width="150">주문일</th>
								<th width="150">주문자 ID</th>
								<th width="150">받는 이</th>
								<th width="150">연락처</th>
								<th width="150">우편번호</th>
								<th width="150">주소</th>
								<th width="150">상세주소</th>
								<th width="150">배송 상태</th>
							</tr>
							<tr>
								<td width="150">${receiptList.buy_date}</td>
								<td width="150">${receiptList.member_id}</td>
								<td width="150">${receiptList.delivery_name}</td>
								<td width="150">${receiptList.delivery_tel}</td>
								<td width="150">${receiptList.delivery_postal_code}</td>
								<td width="150">${receiptList.delivery_address}</td>
								<td width="150">${receiptList.delivery_detailed_address}</td>
								<td width="150">${receiptList.state}</td>
							</tr>
						</table>
					</div>
					<br>
					<p>결제 상세</p>
					<table id="receipt" class="buy">
						<tr>
							<th width="150">주문 금액</th>
							<th width="150">등급 할인</th>
							<th width="150">사용 마일리지</th>
							<th width="150">최종 금액</th>
						</tr>
						<tr>
							<td width="150">${receiptList.price}</td>
							<td width="150">${receiptList.grade_discount}</td>
							<td width="150">${receiptList.use_mileage}</td>
							<td width="150">${receiptList.total_price}</td>
						</tr>
					</table>
					<br>
					<br>
				</c:if>
			</c:forEach>
		</c:if>
	</c:forEach>
</c:if>
<br><br>
</div>