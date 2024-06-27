<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh"
		content="0;url=<%=request.getContextPath()%>/index.do">
</c:if>
<!-- 삭제 성공여부 -->
<div>
	<c:choose>
		<c:when test="${flag==1}}">
			<span>삭제 성공</span>
		</c:when>
		<c:otherwise>
			<span>삭제 실패</span>
		</c:otherwise>
	</c:choose>
</div>
<div id="cartDeletePro">
	<form id="memberDeletePro" method="post"
		action="<%=request.getContextPath()%>/mg/memberList.do">
		<input type="hidden" name="id" value="${sessionScope.id}">
		<input type="submit" value="회원 리스트로 되돌아가기">
	</form>
</div>
