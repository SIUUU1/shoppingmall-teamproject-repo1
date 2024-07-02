<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/shop/content.css"/>
<script src="<%=request.getContextPath()%>/shop/clothContent.js"></script>
<title>Cloth Detail</title>
</head>
<body>



<div id="showCloth" class="cloth-details">
	<table class="vhcenter">
	<tr height="30">
		<td rowspan="6" width="150">
		<img src="<%=request.getContextPath()%>/clothImage/${cloth.getCloth_image()}" class="contentimage"></td>
		<td width="500"><b>${cloth.getCloth_name()}</b></td>
	</tr>
	<tr><td width="500">카테고리 : ${cloth.getCloth_category()}</td></tr>
	<tr><td width="500">브랜드 : ${cloth.getCloth_brand()}</td></tr>
	<tr><td width="500">사이즈 선택 : 
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
		<strong class="bred">판매가 : <c:set var="rPrice" value="${price*((100.0-rate)/100)}"/>
		<fmt:formatNumber value="${rPrice}" type="number" pattern="#,##0"/>원</strong>
	</td></tr>
	<tr>
		<td width="500">
		<c:if test="${!empty sessionScope.id}">
			<c:if test="${cloth.getCloth_count()==0}">
				<p class="sold-out">일시품절</p>
			</c:if>
			<c:if test="${cloth.getCloth_count()>=1}">
				수량 : <input type="text" size="5" id="quantity" value="1"> 개
			</c:if>
			<input type="hidden" id="cloth_id" value="${cloth_id}">
			<input type="hidden" id="cloth_image" value="${cloth.getCloth_image()}">
			<input type="hidden" id="cloth_name" value="${cloth.getCloth_name()}">
			<input type="hidden" id="cloth_price" value="${rPrice}">
			<input type="hidden" id="cloth_category" value="${cloth_category}">
			<input type="hidden" id="cloth_gender" value="${cloth.getCloth_gender()}">
			<input type="hidden" id="member_id" value="${sessionScope.id}">
			<button id="insertCart" class="btn">장바구니에 담기</button>
		</c:if>
		<c:if test="${empty sessionScope.id}">
			<c:if test="${cloth.getCloth_count()==0}"><p class="sold-out">일시품절</p></c:if>
			<p class="login-reminder">제품을 구매하시려면 로그인 하세요.</p>
		</c:if>
		<button id="list" onclick="list()" class="btn">목록으로</button>
		<button id="shopMain" onclick="shopMain()" class="btn">메인으로</button>
		</td>
	</tr>
	<section class="images">
    <div class="images-explain">
        <p><span><b>상품 설명</b></span></p>
    </div>
    <div class="images-explain">
            <img src="shop/images/?????.jpg" width="200" height="200">
        <hr>
     <label for="exp-1"></label>
     <div>
      <p></p>
    </div>
            
    </div>
	<tr class="ch">
	<td colspan="2" class="hleft">${cloth.getCloth_content()}</td>
	</tr>
	</table>
	<!-- qna -->
</div>

<div id="showQna" class="qna-section">
<p class="b">상품 QnA 
	<c:if test="${!empty sessionScope.id}">
		<button id="writeQna" class="btn">상품 QnA 쓰기</button>
	</c:if>
	<c:if test="${empty sessionScope.id}">
		<p class="login-reminder">상품 QnA 를 쓰실려면 로그인 하세요.</p>
	</c:if>
</p>
<c:if test="${count == 0}">
	<ul>
		<li>등록된 상품 QnA 가 없습니다.</li>
	</ul>
</c:if>
<c:if test="${count > 0}">
	<c:forEach var="qna" items="${qnaLists}">
		<ul class="qna-list">
			<li>
				<c:set var="writer" value="${qna.getQna_writer()}"/>
				${fn:substring(writer, 0, 4)}****
				<small class="date">(${qna.getReg_date()})</small>
			</li>
			<li>${qna.getQna_content()}</li>
			<li>
				<c:if test="${sessionScope.id==writer}">
					<button id="edit" name="${qna.getQna_id()},${cloth_category}" onclick="edit(this)" class="btn btn-edit">수정</button>
					<button id="delete" name="${qna.getQna_id()},${cloth_id},${cloth_category},${qna.getGroup_id()},${qna.getQora()}" onclick="del(this)" class="btn btn-delete">삭제</button>
				</c:if> 
			</li>
		</ul>
	</c:forEach>
</c:if>
</div>

</body>
</html>
