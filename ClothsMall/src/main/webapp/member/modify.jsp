<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/ClothsMall/member/css/style.css"/>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/ClothsMall/index.do">
</c:if>

<c:if test="${check==0}">
  <script>
  alert('비밀번호를 잘못 입력하셨습니다.');
  </script>
</c:if>

<div id="mStatus">
   <form id="uForm" method="post" action="/ClothsMall/modifyForm.do">
    <ul>
        <li><label for="member_passwd">비밀번호</label>
            <input id="member_passwd" name="member_passwd" type="password" 
              size="20" maxlength="16">
            <input id="member_id" name="member_id" type="hidden" value="${sessionScope.id}">
            <input type="submit" id="modify" value="정보수정">
     </ul>
   </form>
  <button id="shopMain" 
  onclick="window.location.href='/ClothsMall/index.do'">메인으로</button>
</div>
