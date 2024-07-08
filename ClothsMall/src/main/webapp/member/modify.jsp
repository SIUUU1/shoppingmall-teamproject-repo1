<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<script src="/ClothsMall/member/login.js?ver=5"></script>

<c:if test="${empty sessionScope.id}">
  <script>alert('로그인이 필요한 페이지입니다.');</script>
  <meta http-equiv="Refresh" content="0;url=/ClothsMall/loginForm.do">
</c:if>

<c:if test="${check==0}">
  <script>
  alert('비밀번호를 잘못 입력하셨습니다.');
  </script>
</c:if>

<div id="mStatus">
<h3>재인증</h3>
<p>개인정보를 안전하게 보호하기 위해<br> <span>인증 절차</span>가 필요해요.</p>	
   <form id="uForm" method="post" action="/ClothsMall/modifyForm.do">
            <input id="member_id" name="member_id" type="hidden" value="${sessionScope.id}">
            <input id="member_passwd" name="member_passwd" type="password" size="20" maxlength="16" placeholder=" 패스워드 입력">
            <button type="submit" id="modify">정보수정</button>  
   </form>
</div>
