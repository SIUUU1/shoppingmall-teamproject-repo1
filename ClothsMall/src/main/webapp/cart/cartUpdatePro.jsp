<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=<%=request.getContextPath()%>/index.do">
</c:if>
<script type="text/javascript">
	alert(`상품의 수량이 ${msg}개로 수정되었습니다.`);
</script>
<meta http-equiv="Refresh" content="0;url=<%=request.getContextPath()%>/cartList.do?member_id=${sessionScope.id}" />
