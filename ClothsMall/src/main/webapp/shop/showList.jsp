<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/shop/css/style.css" />

<div id="cata" class="box2">
	<ul>
		<li><a href="<%=request.getContextPath()%>/list.do?cloth_category=1000">상의</a>
		<li><a href="<%=request.getContextPath()%>/list.do?cloth_category=2000">하의</a>
		<li><a href="<%=request.getContextPath()%>/list.do?cloth_category=3000">아우터</a>
		<li><a href="<%=request.getContextPath()%>/list.do?cloth_category=4000">신발</a>
		<li><a href="<%=request.getContextPath()%>/list.do?cloth_category=5000">패션소품</a>
		<li><a href="<%=request.getContextPath()%>/list.do?cloth_category=all">전체</a>
	</ul>
</div>

<div id="shop" class="box2">
		<c:if test="${cloth_category=='1000'}">
			<c:set var="cloth_categoryName" value="상의" />
		</c:if>
		<c:if test="${cloth_category=='2000'}">
			<c:set var="cloth_categoryName" value="하의" />
		</c:if>
		<c:if test="${cloth_category=='3000'}">
			<c:set var="cloth_categoryName" value="아우터" />
		</c:if>
		<c:if test="${cloth_category=='4000'}">
			<c:set var="cloth_categoryName" value="신발" />
		</c:if>
		<c:if test="${cloth_category=='5000'}">
			<c:set var="cloth_categoryName" value="패션소품" />
		</c:if>
		
		<c:if test="${cloth_category=='all'}">
			<c:set var="cloth_categoryName" value="전체" />
			<c:set var="display" value="전체 목록" />
		</c:if>
		<c:if test="${cloth_category!='all'}">
			<c:set var="display" value="[${cloth_categoryName}] 카테고리" />
		</c:if>
		<p class="b">${display} : (${count}개)</p>
		<c:forEach var="cloth" items="${clothList}">
		<table class="vhcenter">
		<tr height="30">
			<td rowspan="4" width="100">
			<a href="<%=request.getContextPath()%>/clothContent.do?cloth_id=${cloth.getCloth_id()}&cloth_category=${cloth.getCloth_category()}">
			<img src="<%=request.getContextPath()%>/clothImage/${cloth.getCloth_image()}" class="listimage"></a></td>
			<td width="350" class="vhcenter">
			<a href="<%=request.getContextPath()%>/clothContent.do?cloth_id=${cloth.getCloth_id()}&cloth_category=${cloth.getCloth_category()}" class="b">
			${cloth.getCloth_name()}</a></td>
			<td rowspan="4" width="100">
			<c:if test="${cloth.getCloth_count()==0}">일시품절</c:if>
			<c:if test="${cloth.getCloth_count()!=0}">구매가능</c:if>
			</td>
		</tr>
		<tr height="30">
			<td width="350">브랜드 :${cloth.getCloth_brand()}</td></tr>
		<tr height="30">
			<td width="350">
		<c:set var="price" value="${cloth.getCloth_price()}"/>
		<c:set var="rate" value="${cloth.getDiscount_rate()}"/>정가 : 
		<fmt:formatNumber value="${price}" type="number" pattern="#,##0"/>원<br>
		<strong class="bred">판매가:
		<fmt:formatNumber value="${price*((100.0-rate)/100)}" type="number" pattern="#,##0"/>원
		</strong></td>
		</tr>
</table>
</c:forEach>
</div>
		