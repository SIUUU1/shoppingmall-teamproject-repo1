<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/ClothsMall/qna/css/style.css"/>
<script src="/ClothsMall/qna/write.js"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/ClothsMall/index.do">
</c:if>

        <input type="text" id="qna_writer" value="${sessionScope.id}">
        <input type="hidden" id="cloth_category" value="${cloth_category}">
        <input type="hidden" id="cloth_id" value="${cloth_id}">
        <input type="text" id="cloth_name" value="${cloth_name}">
        <input type="hidden" id="qora" value="${qora}">
        
<div id="writeForm" class="box">
	<table>
	<tr>
	<td colspan="2">[${cloth_name}]에 대한 QnA</td>
	</tr>
	<tr>
	<td><label for="content">내용</label></td>
	<td><textarea id="qnaCont" rows="13" cols="75"></textarea></td>
	</tr>
	</table>
	<div id="writeBDiv">
    <button id="cancle">취소</button> 
    <button id="regist">등록</button>
	</div>
</div>