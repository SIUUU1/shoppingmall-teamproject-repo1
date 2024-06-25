<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=<%=request.getContextPath()%>/index.do">
</c:if>
<div id="updateResult">
	<p>${msg}
</div>
<div id="cartDeletePro">
	<form id="cartDeletePro" method="post" action="<%=request.getContextPath()%>/cartList.do">
		<input type="hidden" name="member_id" value="${sessionScope.id}">
		<input type="submit" value="장바구니로 되돌아가기">
	</form>
</div>
