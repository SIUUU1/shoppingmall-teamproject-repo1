<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/ClothsMall/mngr/css/style.css?v=2"/>
<script src="/ClothsMall/mngr/qnaProcess/qnalist.js"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/ClothsMall/mg/managerMain.do" >
</c:if>

<div id="qnaHeader">
  <button id="clothsMain">관리자 메인으로</button>
</div>

<c:if test="${count==0}">
  <p>등록된 QnA가 없습니다.</p>
</c:if>

<c:if test="${count>0}">
<div id="qnaList">
  <c:forEach var="qna" items="${qnaLists}">
      <c:if test="${qna.getQora()==1}">
       <p><b>[${qna.getCloth_name()}] 상품에 대한 QnA</b></p>
            <p>${qna.getQna_writer()}<small class="date">(${qna.getReg_date()})</small></p>
            <p>${qna.getQna_content()}</p>
      </c:if>
      <c:if test="${qna.getReply()==0}">
        <p><button id="reply" name="${qna.getQna_id()}" 
	       onclick="reply(this)">답변하기</button></p>
      </c:if>
      <c:if test="${qna.getQora()==2}">
            <p><b>${qna.getQna_content()}</b></p>
            <p><c:if test="${qna.getQna_writer()=='manager'}">관리자</c:if>
             <small class="date">(${qna.getReg_date()})</small>
            <button id="editReply" name="${qna.getQna_id()}" onclick="edit(this)">수정</button>
            </p>
      </c:if>
    <hr>
  </c:forEach>
</div>
</c:if>