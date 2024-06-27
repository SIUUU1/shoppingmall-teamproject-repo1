<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/ClothsMall/mngr/css/style.css"/>
<script src="/ClothsMall/mngr/qnaProcess/qnawrite.js?ver=1"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/ClothsMall/mg/managerMain.do" >
</c:if>

<input type="hidden" id="qna_writer" value="manager">
<input type="hidden" id="qna_id" value="${qna_id}">
<input type="hidden" id="cloth_id" value="${cloth_id}">
<input type="hidden" id="cloth_name" value="${cloth_name}">
<input type="hidden" id="qora" value="${qora}">

<div id="writeForm" class="box">
   <ul>
      <li><p>[${cloth_name}] 의 QnA </p>
          <p>QnA내용:${qna_content}</p>
      <li><label for="rContent">답변</label>
          <textarea id="rContent" rows="13" cols="50"></textarea>
      <li class="label2">
          <button id="replyPro">답변하기</button>
          <button id="cancle">취소</button> 
   </ul>
</div>