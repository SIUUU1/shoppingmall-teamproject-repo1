<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<script src="/ClothsMall/buy/buyForm.js?ver=32"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=/ClothsMall/index.do">
</c:if>

<div>
	<form name="buyForm" method="post" action="/ClothsMall/buyPro.do">
		<div id="cartArea">
			<table class="buylist">
				<tr class="cen">
					<th width="100">상품</th>
					<th width="200">상품명</th>
					<th width="100">판매가격</th>
					<th width="50">수량</th>
					<th width="100">금액</th>
				</tr>
				<c:set var="total" value="0" />
				<c:forEach var="cart" items="${cartLists}">
					<c:set var="price" value="${cart.getCloth_price()}" />
					<c:set var="rate" value="${cart.getDiscount_rate()}" />
					<fmt:parseNumber var="rPrice" value="${price*(100.0-rate)/100}" />

					<tr>
						<td><img
							src="/ClothsMall/clothImage/${cart.getCloth_image()}"
							class="cartimage" width="80px" height="120px"></td>
						<td width="300">${cart.getCloth_name()}</td>
						<td width="100" class="cen"><fmt:formatNumber
								value="${rPrice}" type="number" pattern="#,##0" />원</td>
						<td width="50" class="cen">${cart.getQuantity()}</td>
						<td width="100" class="cen"><c:set var="amount"
								value="${cart.getQuantity()*rPrice}" /> <c:set var="total"
								value="${total+amount}" /> <fmt:formatNumber value="${amount}"
								type="number" pattern="#,##0" />원</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5" align="right" class="b">총 금액 : <fmt:formatNumber
							value="${total}" type="number" pattern="#,##0" />원
					</td>
				</tr>
			</table>
			<br> <br>
			<p>배송지</p>
			<div class="button">
				<input type="button" value="회원정보와 동일" onclick="addressDefault()">
			</div>
			<table class="adress buy">
				<tr>
					<th>받는 이</th>
					<td>
						<div>
							<span class="info" id="member_name_Info" style="color: red;"></span> 
							<input type="text" name="member_name" id="member_name" onchange="member_name_Check()">
						</div>
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
						<div>
							<span class="info" id="member_tel_Info" style="color: red;"></span>
							<input type="text" name="member_tel" id="member_tel" onchange="member_tel_Check()"> 
						</div>
					</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>
							<button type="button" onclick="searchNum()">우편번호 검색</button>
							<span class="info" id="member_postal_code_Info" style="color: red;"></span>
							<input type="text" name="member_postal_code" id="member_postal_code" onchange="member_postal_code_Check"> 
					</td>
				</tr>
				<tr class="adress">
					<th>주소</th>
					<td>
						<div>
							<input type="text" name="member_address" id="member_address" onchange="member_address_Check()">
						</div>
						<div>
							<span class="info" id="adress_Info" style="color: red;"></span>
							<input type="text" name="member_detailed_address" id="member_detailed_address" placeholder="상세주소"> 
						</div>
					</td>
				</tr>
			</table>
		</div>
		<br> <br>
		
		<div id="buyArea">
			<p>주문자 정보</p>
			<table class="buy">
				<tr>
					<th width="200">성명</th>
					<td width="400">${member.getMember_name()}</td>
				</tr>
				<tr>
					<th width="200">전화번호</th>
					<td width="400">${member.getMember_tel()}</td>
				</tr>
			</table>
			<br> <br> <br>
			<P>마일리지 사용</P>
			<table class="mileage buy">
				<tr>
					<th>보유 마일리지</th>
					<td id="mileage">${member.getMileage()}</td>
				</tr>
				<tr>
					<th>사용할 마일리지</th>
					<td>
						<div class="mileage">
							<input type="number" name="useMileage" id="useMileage"
								onchange="useMileageCheck()" value="0"> 
								<span class="info" id="mileageInfo" style="color: red;"></span>
						</div>
					</td>
				</tr>
			</table>
			<br> <br> <br>
			<p>결제 상세</p>
			<table id="recreipt" class="buy">
				<tr height="30">
					<th>주문 금액</th>
					<td colspan="2" align="right">${total}</td>
				</tr>
				<tr height="30" name="gadeDiscount">
					<th>등급 할인</th>
					<td colspan="2" align="right"><span id="gadeDiscountSpan">${Math.floor(total *discount)}</span></td>
				</tr>
				<tr height="30">
					<th>사용 마일리지</th>
					<td><span id="useMileageSpan">0</span></td>
				</tr>
				<tr height="30">
					<th>최종 금액</th>
					<td colspan="2" align="right"><span id="totalPriceSpan">${Math.floor(total-total *discount)}</span></td>
				</tr>

			</table>
			<input type="hidden" name="price" id="price" value="${total}">
			<input type="hidden" name="usePoint" id="usePoint" value="">
			<input type="hidden" name="gadeDiscount" id="gadeDiscount"
				value="${total *discount}"> <input type="hidden"
				name="totalPrice" id="totalPrice"
				value="${Math.floor(total-total*discount)}"> <input
				type="hidden" name="member_id" value="${sessionScope.id}"> <br>
			<br>
			<div>
				<table class="buy">
					<tr>
						<th>보유 포인트</th>
						<td>${member.getPoint()}</td>
					</tr>
					<tr>
						<th>사용할 포인트</th>
						<td id="usePointSpan">${Math.floor(total - total * discount)}</td>
					</tr>
					<tr>
						<th>남을 포인트</th>
						<td id="calPoint">${Math.floor(member.getPoint() - (total - total * discount))}</td>
					</tr>
				</table>
				<p id="calPointInfo"></p>
			</div>

			<div class="button">
				<input type="button" id="cancle" value="취소">
				<button onclick="allCheck(event)">결제 하기</button>
			</div>

			<input type="hidden" id="member_name_h"
				value="${member.getMember_name()}"> <input type="hidden"
				id="member_tel_h" value="${member.getMember_tel()}"> <input
				type="hidden" id="member_postal_code_h"
				value="${member.getMember_postal_code()}"> <input
				type="hidden" id="member_address_h"
				value="${member.getMember_address()}"> <input type="hidden"
				id="member_detailed_address_h"
				value="${member.getMember_detailed_address()}"> <input
				type="hidden" id="point_h" value="${member.getPoint()}"> <input
				type="hidden" id="mileage_h" value="${member.getMileage()}">
			<input type="hidden" id="gradeDiscount_h" value="${discount}">
			<input type="hidden" id="total_h"
				value="${Math.floor(total-total*discount)}">
			</div>
			
	</form>
</div>
