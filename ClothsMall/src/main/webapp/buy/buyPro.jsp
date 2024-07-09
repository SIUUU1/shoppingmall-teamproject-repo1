<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=${pageContext.request.contextPath}/index.do">
</c:if>
<meta http-equiv="Refresh" content="0;url=${pageContext.request.contextPath}/buyList.do?member_id=${sessionScope.id}" />