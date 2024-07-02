<%@page import="clothshop.bean.LogonDataBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="stylesheet" href="/ClothsMall/member/css/style.css"/>
<script src="/ClothsMall/member/modify.js?ver=4"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<c:if test="${empty sessionScope.id}">
    <meta http-equiv="Refresh" content="0;url=/ClothsMall/index.do">
</c:if>
<div id="regForm" class="box">
    <ul>
        <li>
            <p class="center">회원 정보 수정</p>
        </li>
        <li>
            <label for="member_id">아이디</label>
            <input id="member_id" name="member_id" type="text" size="20" maxlength="10" value="${id}" readonly>
        </li>
        <li>
            <label for="member_passwd">비밀번호</label>
            <input id="member_passwd" name="member_passwd" type="password" size="20" maxlength="16"">
        </li>
        <li>
            <label for="repass">비밀번호 재입력</label>
            <input id="repass" name="repass" type="password" size="20" maxlength="16"">
        </li>
        <li>
            <label for="member_name">이름</label>
            <input id="member_name" name="member_name" type="text" size="20" maxlength="5" value="${m.getMember_name()}">
        </li>
        <li>
            <label for="member_postal_code">우편번호</label>
            <input id="member_postal_code" name="member_postal_code" type="text" size="10" maxlength="6" value="${m.getMember_postal_code()}">
            <button type="button" onclick="openDaumPostcode()">주소검색</button>
        </li>
        <li>
            <label for="member_address">주소</label>
            <input id="member_address" name="member_address" type="text" size="30" maxlength="50" value="${m.getMember_address()}">
        </li>
        <li>
            <label for="member_detailed_address">상세주소</label>
            <input id="member_detailed_address" name="member_detailed_address" type="text" size="30" maxlength="50" value="${m.getMember_detailed_address()}">
        </li>
        <li>
            <label for="member_gender">성별</label>
            <select id="member_gender" name="member_gender">
           		<option value="M" <c:if test="${m.getMember_gender() == 'M'}">selected</c:if>>남성</option>
            	<option value="F" <c:if test="${m.getMember_gender() == 'F'}">selected</c:if>>여성</option>
            </select>
        </li>
        <li>
            <label for="member_tel">전화번호</label>
            <input id="member_tel" name="member_tel" type="tel" size="20" maxlength="13" value="${m.getMember_tel()}">
        </li>
        <li class="label2">
            <button id="modifyProcess">수정</button>
            <button id="delete" onclick="onDelete()">회원 탈퇴</button>
            <button id="cancel">취소</button>
        </li>
    </ul>
</div>
