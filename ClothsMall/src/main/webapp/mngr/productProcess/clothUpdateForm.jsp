<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/mngr/css/style.css" />
<script src="<%=request.getContextPath()%>/mngr/productProcess/clothUpdate.js"></script>

<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=<%=request.getContextPath()%>/mg/managerMain.do">
</c:if>

<div id="header">
	<button id="clothMain">관리자 메인으로</button>
	<button id="clothList">목록으로</button>
</div>
<form id="upForm1" action="<%=request.getContextPath()%>/mg/clothUpdatePro.do" method="post" enctype="multipart/form-data">
	<div id="clothUpdateForm" class="box">
		<ul>
			<li><label for="cloth_category">분류선택</label> 
			<select id="cloth_category" name="cloth_category">
					<option value="1000" <c:if test="${cloth_category == '1000'}">selected</c:if>>상의</option>
					<option value="2000" <c:if test="${cloth_category == '2000'}">selected</c:if>>하의</option>
					<option value="3000" <c:if test="${cloth_category == '3000'}">selected</c:if>>아우터</option>
					<option value="4000" <c:if test="${cloth_category == '4000'}">selected</c:if>>신발</option>
					<option value="5000" <c:if test="${cloth_category == '5000'}">selected</c:if>>패션소품</option>
			</select>
			<li><label for="cloth_gender">성별선택</label> 
			<select id="cloth_gender" name="cloth_gender">
					<option disabled hidden <c:if test="${cloth_gender == null}">selected</c:if>>선택</option>
					<option value="F" <c:if test="${cloth_gender == 'F'}">selected</c:if>>F</option>
					<option value="M" <c:if test="${cloth_gender == 'M'}">selected</c:if>>M</option>
			</select>
			<li><label for="cloth_size">사이즈선택</label> 
			<select id="cloth_size" name="cloth_size">
					<option value="S" <c:if test="${cloth_size == 'S'}">selected</c:if>>S</option>
					<option value="M" <c:if test="${cloth_size == 'M'}">selected</c:if>>M</option>
					<option value="L" <c:if test="${cloth_size == 'L'}">selected</c:if>>L</option>
					<option value="XL" <c:if test="${cloth_size == 'XL'}">selected</c:if>>XL</option>
			</select>
			<li><label for="cloth_name">옷이름</label> 
			<input id="cloth_name" name="cloth_name" type="text" size="50" placeholder="옷이름"	maxlength="50" 
			value="${cloth.cloth_name}">
			<li><label for="cloth_price">가격</label> 
			<input id="cloth_price" name="cloth_price" type="text" size="10" placeholder="가격" maxlength="9"
			value="${cloth.cloth_price}">원
			<li><label for="cloth_count">수량</label> 
			<input id="cloth_count" name="cloth_count" type="text" size="10" placeholder="수량" maxlength="5"
			value="${cloth.cloth_count}">개
			<li><label for="cloth_brand">브랜드</label> 
			<input id="cloth_brand" name="cloth_brand" type="text" size="50" placeholder="브랜드" maxlength="50"
			value="${cloth.cloth_brand}">
			<li><label for="cloth_image">책 이미지</label> 
			<input id="cloth_image" name="cloth_image" type="file" value="${cloth.cloth_image}">
			<li><label for="cloth_content">내용</label>
			<textarea id="cloth_content" name="cloth_content" rows="13" cols="50">${cloth.cloth_content}</textarea>
			<li><label for="discount_rate">할인율</label> 
			<input id="discount_rate" name="discount_rate" type="text" size="5" placeholder="10" maxlength="2" 
			value="${cloth.discount_rate}">
			<li class="label2"> 
			<input type="submit" id="updateCloth" value="옷수정">
		</ul>
	</div>
</form>