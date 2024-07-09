<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<script src="${pageContext.request.contextPath}/shop/clothContent.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>

<div id="showCloth">
        <img src="${pageContext.request.contextPath}/clothImage/${cloth.getCloth_image()}" class="contentimage">
     	<div class="cloth-content">
 		<span class="brand"><i class="fa-solid fa-house"></i> ${cloth.getCloth_brand()} ></span><br><br>
     	<span class="cloth-name">${cloth.getCloth_name()}</span><br><br>
	
    <c:set var="price" value="${cloth.getCloth_price()}"/>
    <c:set var="rate" value="${cloth.getDiscount_rate()}"/>
    <span class="ratio">${rate}% </span>
    <strong class="bred"><c:set var="rPrice" value="${price*((100.0-rate)/100)}"/>
    <fmt:formatNumber value="${rPrice}" type="number" pattern="#,##0"/>원</strong>
    <span class="origin"><fmt:formatNumber value="${price}" type="number" pattern="#,##0"/>원</span>
    <br><br><br>
   
    <select id="cloth_size">
   					<option disabled hidden selected>[사이즈]를 선택하세요.</option>
                    <option value="S">S</option>
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>
    </select><br><br><br>
        <c:if test="${!empty sessionScope.id}">
        <!-- count -->
        <c:if test="${cloth.getCloth_count()==0}">
        <p>일시품절</p>
        </c:if>
        <c:if test="${cloth.getCloth_count()>=1}">
        <table>
        <tr><td onclick="minus()"> - </td>
        <td><input type="text" size="5" id="quantity" value="1"></td>
        <td onclick="plus()"> + </td></tr>
        </table><br><br>
        </c:if>
        
        <input type="hidden" id="cloth_id" value="${cloth_id}">
        <input type="hidden" id="cloth_image" value="${cloth.getCloth_image()}">
        <input type="hidden" id="cloth_name" value="${cloth.getCloth_name()}">
        <input type="hidden" id="cloth_price" value="${rPrice}">
        <input type="hidden" id="cloth_category" value="${cloth_category}">
        <input type="hidden" id="cloth_gender" value="${cloth.getCloth_gender()}">
        <input type="hidden" id="discount_rate" value="${cloth.getDiscount_rate()}">
        <input type="hidden" id="cloth_brand" value="${cloth.getCloth_brand()}">
        <input type="hidden" id="member_id" value="${sessionScope.id}">
        <button id="insertCart">장바구니 담기</button>
        </c:if>
        <c:if test="${empty sessionScope.id}">
        <c:if test="${cloth.getCloth_count()==0}"><p class="nStock">일시품절</p></c:if>
   		<br><br>
        <p>제품을 구매하시려면 로그인 하세요.</p>
        </c:if>
        <!--cloth content  -->
		<%-- ${cloth.getCloth_content()} --%>
</div>
</div>
<!-- qna -->
<div id="showQna">
<p class="b">상품 QnA
<c:if test="${!empty sessionScope.id}">
 <div id="rightButton">
 <button id="writeQna">상품 QnA 쓰기</button>
 </div>
</c:if>
<c:if test="${empty sessionScope.id}">
 <p>상품 QnA 를 쓰실려면 로그인 하세요.</p>
</c:if>
</p>
<c:if test="${count == 0}">
<div class="qna-content">
 등록된 상품 QnA 가 없습니다.
</div>
</c:if>
<c:if test="${count > 0}">

 <c:forEach var="qna" items="${qnaLists}">
<div class="qna-contentList">
 <c:set var="writer" value="${qna.getQna_writer()}"/>
 ${fn:substring(writer, 0, 4)}****
 <small class="date">(${qna.getReg_date()})</small><br>
 ${qna.getQna_content()}
 <c:if test="${sessionScope.id==writer}">
 <button id="edit" name="${qna.getQna_id()},${cloth_category}" onclick="edit(this)">수정</button>
 <button id="delete" name="${qna.getQna_id()},${cloth_id},${cloth_category},${qna.getGroup_id()},${qna.getQora()}" onclick="del(this)">삭제</button>
 </c:if>
 </div>
 </c:forEach>
 
</c:if>
</div>