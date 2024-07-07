<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mngr/css/style.css" />
<script src="<%=request.getContextPath()%>/mngr/logon/mlogin.js?ver=1"></script>
<script src="<%=request.getContextPath()%>/mngr/managerMain.js"></script>
<header>
	<c:if test="${empty sessionScope.managerId}">
		<h1 style="color: white">CRUELLA MANAGER</h1>
	</c:if>
	<c:if test="${!empty sessionScope.managerId}">
		<h1 style="color: white">CRUELLA MANAGER</h1>
	</c:if>
</header>
<section>
	<c:if test="${empty sessionScope.managerId}">
		<div id="status">
			<div id="loginForm">
				<input id="id" name="id" type="text" maxlength="50"
					placeholder="아이디"> <input id="passwd" name="passwd"
					type="password" placeholder="비밀번호" maxlength="16">
				<button id="login" onclick="login()">로그인</button>
			</div>
		</div>
		<div id="mList">CRUELLA 관리자 페이지입니다.</div>
	</c:if>

	<c:if test="${!empty sessionScope.managerId}">
		<div id="status">
			<div id="loginForm">
				<div>
					<span style="color:#595959; font-weight: 900; font-size:1.1em;">관리자님</span> 환영합니다
				</div>
				<button id="logout" onclick="logout()">로그아웃</button>
			</div>
		</div>
		<div id="mList">
			<button id="registProduct" class="menuList">상품등록</button>
			<button id="updateProduct" class="menuList">상품수정/삭제</button>
			<button id="updateMember" class="menuList">회원관리</button>
			<button id="orderedProduct" class="menuList">전체구매목록 확인</button>
			<button id="qna" class="menuList">상품 QnA답변</button>
			<hr>
		</div>
	</c:if>
</section>