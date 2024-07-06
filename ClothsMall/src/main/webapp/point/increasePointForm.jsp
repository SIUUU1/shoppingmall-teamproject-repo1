<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<script src="/ClothsMall/point/increasePoint.js"></script>
<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=/shoppingmall/index.do">
</c:if>

<form name="buyForm" method="post" action="/ClothsMall/increasePointPro.do">
	<p>쇼핑 포인트 충전결제</p>
	<table>
		<tr>
			<th>보유 포인트</th>
			<td>${point}</td>
		</tr>
		<tr>
			<th>충전 금액</th>
			<td><select id="point" name="point" >
					<option value="1000">1000</option>
					<option value="5000">5000</option>
					<option value="10000">10000</option>
					<option value="50000">50000</option>
					<option value="100000">100000</option>
					<option value="200000">200000</option>
			</select></td>
		</tr>
		<tr>
			<th>충전 계좌</th>
			<td><input placeholder="은행 명" id="bank" name="bank" onchange="accountCheck()"/></td>
			<td><input placeholder="계좌번호 (-기호 제외)" id="account" name="account" onchange="accountCheck()"/></td>
			<td>
				<button onclick="allCheck()">충전</button>
			</td>
		</tr>
		<tr height="30px">
			<td colspan="3"><span id="accountInfo"></span></td>
		</tr>
		<input type="hidden" name="member_id" value="${sessionScope.id}">
	</table>
</form>

