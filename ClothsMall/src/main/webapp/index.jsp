<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/slideshow.js"></script>
<script src="https://kit.fontawesome.com/3842b33e0f.js" crossorigin="anonymous"></script>
<title>Cruella</title>

<div id="auth" class="box1">
	<c:if test="${type == 0}">
		<jsp:include page="mngr/logon/mLoginForm.jsp" />
	</c:if>
	<c:if test="${type == 1}">
		<jsp:include page="/header.jsp" />
	</c:if>
</div>

<div id="content" class="box2">
	<jsp:include page="${cont}" />
</div>

<c:if test="${footer==1}">
	<div class="box3">
		<jsp:include page="/footer.jsp" />
	</div>
</c:if>