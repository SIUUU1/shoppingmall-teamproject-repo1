<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=<%=request.getContextPath()%>/index.do">
</c:if>
<div id="cartUpdate">
	<form id="cartUpdateForm" method="post" action="<%=request.getContextPath()%>/cartUpdatePro.do">
		<input type="text" name="quantity" size="5" value="${quantity}">
		<input type="hidden" name="cart_id" value="${cart_id}">
		<input type="submit" value="변경">
	</form>
</div>
