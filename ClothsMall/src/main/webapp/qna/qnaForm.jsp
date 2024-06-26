<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/ClothsMall/qna/css/style.css"/>
<script src="/ClothsMall/qna/write.js"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/ClothsMall/index.do">
</c:if>
<head>
    <title>Q&A Form</title>
    <script src="qnaForm.js"></script>
</head>
<body>
    <h1>Register Q&A</h1>
    <form id="registForm">
        <input type="hidden" id="cloth_id" value="${cloth_id}">
        <input type="hidden" id="cloth_category" value="${cloth_category}">
        <label for="qna_writer">Writer:</label>
        <input type="text" id="qna_writer" name="qna_writer"><br>
        <label for="cloth_title">Title:</label>
        <input type="text" id="cloth_name" name="cloth_name"><br>
        <label for="qnaCont">Content:</label>
        <textarea id="qnaCont" name="qna_content" rows="4" cols="50"></textarea><br>
        <input type="hidden" id="qora" value="Q">
        <button type="button" id="regist">Register</button>
        <button type="button" id="cancle">Cancel</button>
    </form>
</body>
<div id="writeForm" class="box">
   <ul>
      <li>[${cloth_title}]에 대한 QnA
      <li><label for="content">내용</label>
          <textarea id="qnaCont" rows="13" cols="50"></textarea>
      <li class="label2">
          <button id="regist">등록</button>
          <button id="cancle">취소</button> 
   </ul>
</div>