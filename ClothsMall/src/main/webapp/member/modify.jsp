<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<script src="/ClothsMall/member/login.js?ver=5"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/ClothsMall/loginForm.do">
</c:if>

<c:if test="${check==0}">
  <script>
  alert('비밀번호를 잘못 입력하셨습니다.');
  </script>
</c:if>

<div id="mStatus">
<h3>${m.getMember_name()}님 고객 정보</h3>
	<table>
		<tr>
			<td>등급</td>
			<td>마일리지</td>
			<td>포인트</td>
		</tr>
		<tr>
			<td>${m.getMember_grade()}</td>
			<td>${m.getMileage()}</td>
			<td>${m.getPoint()}</td>
		</tr>
	</table>
<h3>내 정보수정</h3>	
   <form id="uForm" method="post" action="/ClothsMall/modifyForm.do">
            <input id="member_id" name="member_id" type="hidden" value="${sessionScope.id}">
            <input id="member_passwd" name="member_passwd" type="password" 
              size="20" maxlength="16" placeholder="패스워드 입력">
            <input type="submit" id="modify" value="정보수정">
   </form>
  <button id="uLogout" onclick="uLogout()">로그아웃</button> 
  <button id="shopMain" onclick="window.location.href='/ClothsMall/index.do'">메인으로</button>
</div>
