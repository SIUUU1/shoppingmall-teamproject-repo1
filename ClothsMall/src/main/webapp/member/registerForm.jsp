<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="stylesheet" href="/ClothsMall/member/css/style.css"/>
<script src="/ClothsMall/member/register.js?ver=1"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<div id="regForm" class="box">
    <ul>
        <li>
            <label for="member_id">아이디</label>
            <input id="member_id" name="member_id" type="text" size="20" maxlength="10" placeholder="example" autofocus>
            <button id="checkId">ID 사용가능 여부 확인</button>
        </li>
        <li>
            <label for="member_passwd">비밀번호</label>
            <input id="member_passwd" name="member_passwd" type="password" size="20" placeholder="영문자와 숫자,특수기호 조합 6~16자" maxlength="16">
        </li>
        <li>
            <label for="repass">비밀번호 재입력</label>
            <input id="repass" name="repass" type="password" size="20" placeholder="비밀번호 재입력" maxlength="16">
        </li>
        <li>
            <label for="member_name">이름</label>
            <input id="member_name" name="member_name" type="text" size="20" placeholder="홍길동" maxlength="5">
        </li>
        <li>
            <label for="member_postal_code">우편번호</label>
            <input id="member_postal_code" name="member_postal_code" type="text" size="10" placeholder="우편번호 입력" maxlength="6" readonly>
            <button type="button" id="searchAddress" onclick ="openDaumPostcode()">주소검색</button>
        </li>
        <li>
            <label for="member_address">주소</label>
            <input id="member_address" name="member_address" type="text" size="30" placeholder="주소 입력" maxlength="50" readonly>
        </li>
        <li>
            <label for="member_detailed_address">상세주소</label>
            <input id="member_detailed_address" name="member_detailed_address" type="text" size="30" placeholder="상세주소 입력" maxlength="50">
        </li>
        <li>
            <label>성별</label>
            <input type="radio" id="male" name="member_gender" value="M" checked>
            <label for="male">남성</label>
            <input type="radio" id="female" name="member_gender" value="F">
            <label for="female">여성</label>
        </li>
        <li>
            <label for="member_tel">전화번호</label>
            <input id="member_tel" name="member_tel" type="tel" size="20" placeholder="전화번호 입력" maxlength="13">
        </li>
        <li class="label2">
            <button id="process" onclick="process()">가입하기</button>
            <button id="cancel">취소</button>
        </li>
    </ul>
</div>