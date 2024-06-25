<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/mngr/css/style.css" />
<script src="<%=request.getContextPath()%>/mngr/managerMain.js"></script>
<c:if test="${empty sessionScope.id}">
	<div id="mList">
		<p>로그인 하세요.<P>	
	</div>
</c:if>
<c:if test="${!empty sessionScope.id}">
	<div id="mList">
		<ul>
			<li>상품관련 작업
			<li><button id="registProduct">상품등록</button>
			<li><button id="updateProduct">상품수정/삭제</button>
		</ul>
		<ul>
			<li>구매된 상품관련 작업
			<li><button id="orderedProduct">전체구매목록 확인</button>
		</ul>
		<ul>
			<li>상품 QnA 작업
			<li><button id="qna">상품 QnA답변</button>
		</ul>
	</div>
</c:if>