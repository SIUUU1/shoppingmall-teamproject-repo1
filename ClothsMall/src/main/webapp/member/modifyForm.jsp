<%@page import="java.util.Date"%>
<%@page import="clothshop.bean.LogonDataBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="/ClothsMall/member/modify.js?ver=4"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=/ClothsMall/index.do">
</c:if>
<div id="modForm" class="box">
	<h3>내 정보</h3>
	<table>
		<tr>
			<td><label for="member_id">아이디</label></td>
			<td><input id="member_id" name="member_id" type="text" size="20" maxlength="10" value="${id}" readonly></td>
		</tr>
		<tr>
			<td><label for="member_passwd">비밀번호</label></td>
			<td><input id="member_passwd" name="member_passwd" type="password" size="20" maxlength="16"></td>
		</tr>
		<tr>
			<td><label for="repass">비밀번호 재입력</label></td>
			<td><input id="repass" name="repass" type="password" size="20" maxlength="16"></td>
		</tr>
		<tr>
			<td><label for="member_name">이름</label></td>
			<td><input id="member_name" name="member_name" type="text" size="20" maxlength="5" value="${m.getMember_name()}"></td>
		</tr>
		<tr>
			<td><label for="member_postal_code">우편번호</label></td>
			<td>
				<div class="input-group">
					<input id="member_postal_code" name="member_postal_code" type="text" size="10" maxlength="6" value="${m.getMember_postal_code()}" readonly onclick="openDaumPostcode()">
					<button type="button" id="searchAddressButton" onclick="openDaumPostcode()">주소검색</button>
				</div>
			</td>
		</tr>
		<tr>
			<td><label for="member_address">주소</label></td>
			<td><input id="member_address" name="member_address" type="text" size="30" maxlength="50" value="${m.getMember_address()}" readonly></td>
		</tr>
		<tr>
			<td><label for="member_detailed_address">상세주소</label></td>
			<td><input id="member_detailed_address" name="member_detailed_address" type="text" size="30" maxlength="50" value="${m.getMember_detailed_address()}"></td>
		</tr>
		<tr>
			<td><label for="member_gender">성별</label></td>
			<td><select id="member_gender" name="member_gender">
					<option value="M"
						<c:if test="${m.getMember_gender() == 'M'}">selected</c:if>>남성</option>
					<option value="F"
						<c:if test="${m.getMember_gender() == 'F'}">selected</c:if>>여성</option>
			</select></td>
		</tr>
		<tr>
			<td><label for="member_tel">전화번호</label></td>
			<td><input id="member_tel" name="member_tel" type="tel"
				size="20" maxlength="13" value="${m.getMember_tel()}"></td>
		</tr>
	</table >
		<div class="label2">
			<button id="delete" onclick="onDelete()">회원 탈퇴</button>
			<button id="modifyProcess">수정</button>
			<button id="cancel">취소</button>
		</div>
</div>
