<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/ClothsMall/mngr/css/style.css" />
<script src="/ClothsMall/mngr/qnaProcess/qnaupdate.js?ver=2"></script>

<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh"
		content="0;url=/ClothsMall/mg/managerMain.do">
</c:if>

<input type="hidden" id="qna_id" value="${qna_id}">

<div id="editForm" class="box">
	<table>
		<tr>
			<td><label for="content">내용</label></td>
			<td><textarea id="uRContent" rows="13" cols="75">${qna.getQna_content()}</textarea></td>
		</tr>
	</table>
	<div id="editBDiv">
	<button id="cancle">취소</button>
	<button id="update">수정</button>
	</div>
</div>