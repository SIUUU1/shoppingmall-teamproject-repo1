<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/ClothsMall/qna/css/style.css" />
<script src="/ClothsMall/qna/update.js"></script>

<c:if test="${empty sessionScope.id}">
 <meta http-equiv="Refresh" content="0;url=/ClothsMall/index.do">
</c:if>

<input type="hidden" id="qna_id" value="${qna_id}">
<input type="hidden" id="cloth_category" value="${cloth_category}">
<input type="hidden" id="cloth_id" value="${qna.getCloth_id}">

<div id="editForm" class="box">
	<table>
	<tr>
	<td><label for="content">내용</label> </td>
	<td><textarea id="updateCont" rows="13" cols="75">${qna.getQna_content()}</textarea></td>
	</tr>
	</table>
	<div id="editBDiv">
	<button id="cancle">취소</button>
	<button id="update">수정</button>
	</div>
</div>