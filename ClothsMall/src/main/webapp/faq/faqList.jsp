<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<title>자주 묻는 질문</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/faq/css/style.css" />
<script src="<%=request.getContextPath()%>/faq/faqList.js"></script>
</head>
<body>
	<header>
		<h2>FAQ</h2>
		<div id="search">
			<input type="text" placeholder="검색어를 입력하세요...">
			<button id="searchButn">검색</button>
		</div>
	</header>
	<button id="mainButn">메인으로</button>
	<section>
		<table>
			<tr>
				<td width="100px">번호</td>
				<td width="100px">
					<select>
						<option>전체</option>
						<option value="100">백</option>
						<option value="200">이백</option>
						<option value="300">삼백</option>
					</select>
				</td>
				<td width="600px">제목</td>
			</tr>
			<c:forEach var="faq" items="${faqList}">
				<tr>
					<td width="100px">${faq.getFaq_no()}</td>
					<td width="100px">
						<c:choose>
							<c:when test="${faq.getFaq_category()==100}">백</c:when>
							<c:when test="${faq.getFaq_category()==200}">이백</c:when>
							<c:when test="${faq.getFaq_category()==300}">삼백</c:when>
						</c:choose>
					</td>
					<td width="600px">${faq.getFaq_title()}</td>
				</tr>
			</c:forEach>
		</table>
	</section>
</body>
</html>