<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/mngr/css/style.css?v=1" />
<script src="<%=request.getContextPath()%>/mngr/productProcess/clothList.js?ver=<%=new Date().getTime()%>"></script>

<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=<%=request.getContextPath()%>/mg/managerMain.do">
</c:if>

<div id="listHeader">
	<p>
		등록된 상품 목록(전체 상품:${count})
		<button id="regist">상품 등록</button>
		<button id="clothMain">관리자 메인으로</button>
	</p>
</div>
<div id="cloths">
	<c:if test="${count == 0}">
		<ul>
			<li>등록된 상품이 없습니다.
		</ul>
	</c:if>
	<c:if test="${count > 0}">
		<table>
			<tr class="title">
				<td align="center" width="30">번호</td>
				<td align="center" width="80">카테고리</td>
				<td align="center" width="40">성별</td>
				<td align="center" width="50">사이즈</td>
				<td align="center" width="200">옷이름</td>
				<td align="center" width="80">가격</td>
				<td align="center" width="80">수량</td>
				<td align="center" width="150">브랜드</td>
				<td align="center" width="150">등록일</td>
				<td align="center" width="100">옷이미지</td>
				<td align="center" width="50">할인율</td>
				<td align="center" width="50">수정</td>
				<td align="center" width="50">삭제</td>
			</tr>
			<c:set var="number" value="${0}" />
			<c:forEach var="cloth" items="${clothList}">
				<tr>
					<td align="center" width="50">
					<c:set var="number" value="${number+1}" /> <c:out value="${number}" /></td>
					<td width="30">${cloth.getCloth_category()}</td>
					<td width="30">${cloth.getCloth_gender()}</td>
					<td width="30">${cloth.getCloth_size()}</td>
					<td width="100" align="left">${cloth.getCloth_name()}</td>
					<td width="50" align="right">${cloth.getCloth_price()}</td>
					<td width="50" align="right">
					<c:if test="${cloth.getCloth_count() == 0}">
							<font color="red">일시품절</font>
						</c:if> 
					<c:if test="${cloth.getCloth_count() > 0}">${cloth.getCloth_count()}</c:if>
					</td>
					<td width="70">${cloth.getCloth_brand()}</td>
					<td width="70"><fmt:formatDate pattern="yyyy-MM-dd" value="${cloth.getReg_date()}" /></td>
					<td width="50">${cloth.getCloth_image()}</td>
					<td width="30">${cloth.getDiscount_rate()}</td>
					<td width="50">
						<button id="edit" name="${cloth.getCloth_id()},${cloth.getCloth_category()},${cloth.getCloth_gender()},${cloth.getCloth_size()}" onclick="edit(this)">수정</button>
					</td>
					<td width="50">
						<button id="delete" name="${cloth.getCloth_id()},${cloth.getCloth_category()}" onclick="del(this)">삭제</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>