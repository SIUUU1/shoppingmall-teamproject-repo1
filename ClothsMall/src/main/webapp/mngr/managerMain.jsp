<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/mngr/css/style.css?v=1" />
<script src="<%=request.getContextPath()%>/mngr/managerMain.js"></script>

<c:if test="${empty sessionScope.id}">
	<div id="mList">
		<p><span>CRUELLA</span> 관리자 페이지입니다.</P>
	</div>
</c:if>

<c:if test="${!empty sessionScope.id}">
	<div id="mList">
		<button id="registProduct">상품등록</button>
		<button id="updateProduct">상품수정/삭제</button>
		<button id="updateMember">회원수정/삭제</button>
		<button id="orderedProduct">전체구매목록 확인</button>
		<button id="qna">상품 QnA답변</button>
	</div>
</c:if>