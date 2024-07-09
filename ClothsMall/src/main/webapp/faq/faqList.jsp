<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<title>FAQ</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script src="<%=request.getContextPath()%>/faq/faqList.js"></script>
</head>
<body>
	<h2 id="faqTitle">FAQ</h2>
	<div id="menu">
		<select onchange="sortFaq(this)">
			<option value="all">전체</option>
			<option value="100">계정</option>
			<option value="200">상품</option>
			<option value="300">배송</option>
		</select>
		<div id="select">
			<input type="text" id="searchInput" placeholder="검색어를 입력하세요..."
				onkeyup="enterFunc();"> <i id="erase"
				class="fa-solid fa-xmark"></i>
		</div>
		<button id="selectBtn" onclick="searchFunc()">검색</button>
	</div>
	<section>
		<table>
			<tr id="col">
				<th width="100px">번호</th>
				<th width="100px">카테고리</th>
				<th width="600px">제목</th>
			</tr>
			<c:forEach var="faq" items="${faqList}">
				<tr class="cat${faq.getFaq_category()}" id="${faq.getFaq_title()}">
					<td width="100px">${faq.getFaq_no()}</td>
					<td width="100px"><c:choose>
							<c:when test="${faq.getFaq_category()==100}">
								<span>계정</span>
							</c:when>
							<c:when test="${faq.getFaq_category()==200}">
								<span>상품</span>
							</c:when>
							<c:when test="${faq.getFaq_category()==300}">
								<span>배송</span>
							</c:when>
						</c:choose></td>
					<td width="600px"><button name="${faq.getFaq_no()}"
							onclick="showContent(this)">${faq.getFaq_title()}</button>
						<div class="faq_content" id="${faq.getFaq_no()}">
							${faq.getFaq_content()}
						</div></td>
				</tr>
			</c:forEach>
		</table>
	</section>
</body>
</html>