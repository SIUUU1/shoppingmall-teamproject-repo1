<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
    <div id="shop" class="shop-display">
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
            <c:set var="display" value="전체" />
        </c:if>
        <c:if test="${cloth_category!='all'}">
            <c:set var="display" value="${cloth_categoryName}" />
        </c:if>
        
        <h3 class="b">${display}</h3>
        <div class="vhcenter">
            <c:forEach var="cloth" items="${clothList}">
                <div class="vhcenter-content">
                <a href="${pageContext.request.contextPath}/clothContent.do?cloth_id=${cloth.getCloth_id()}&cloth_category=${cloth.getCloth_category()}">
                <img src="${pageContext.request.contextPath}/clothImage/${cloth.getCloth_image()}" class="listimage"></a>
                <div class="vhcenter-info">
                <span class="brand">${cloth.getCloth_brand()}</span><br>
                <a href="${pageContext.request.contextPath}/clothContent.do?cloth_id=${cloth.getCloth_id()}&cloth_category=${cloth.getCloth_category()}">
                ${cloth.getCloth_name()}</a><br>
            <c:set var="price" value="${cloth.getCloth_price()}"/>
            <c:set var="rate" value="${cloth.getDiscount_rate()}"/>
            <span class="ratio">${rate}% </span>
            <strong class="bred">
            <fmt:formatNumber value="${price*((100.0-rate)/100)}" type="number" pattern="#,##0"/>원
            </strong>
            <span class="origin"><fmt:formatNumber value="${price}" type="number" pattern="#,##0"/>원</span>
            </div>
            </div>
    </c:forEach>
    </div>
    </div>