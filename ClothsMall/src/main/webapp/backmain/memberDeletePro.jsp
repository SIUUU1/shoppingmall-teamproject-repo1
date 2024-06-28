<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=<%=request.getContextPath()%>/index.do">
</c:if>

<div>
	<span>${delCount}명 삭제</span>
</div>
<div id="cartDeletePro">
	<form id="memberDeletePro" method="post" action="<%=request.getContextPath()%>/mg/memberList.do">
		<input type="hidden" name="id" value="${sessionScope.id}">
		<input type="submit" value="회원 리스트로 되돌아가기">
	</form>
</div>
