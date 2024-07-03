<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<script src="${pageContext.request.contextPath}/mngr/memberProcess/memberList.js"></script>
<c:if test="${empty sessionScope.managerId}">
	<meta http-equiv="Refresh"
		content="0;url=${pageContext.request.contextPath}/mg/managerMain.do">
</c:if>

<div id="memberList">
	<form method="post"
		action="${pageContext.request.contextPath}/mg/memberDeletePro.do">
		<c:choose>
			<c:when test="${count == 0}">
				<span>등록된 회원 수가 없습니다</span>
			</c:when>
			<c:otherwise>
				<div id="memlistHeader">
					<button type="button" id="clothMain">관리자메인으로</button>
				</div>
				<table>
					<tr>
						<td>아이디</td>
						<td>이름</td>
						<td>성별</td>
						<td>가입날짜</td>
						<td>우편번호</td>
						<td>주소</td>
						<td>전화번호</td>
						<td>등급</td>
						<td>마일리지/포인트</td>
						<td>체크</td>
					</tr>
					<c:forEach var="member" items="${memberList}">
						<tr>
							<td>${member.getMember_id()}</td>
							<td>${member.getMember_name()}</td>
							<td>${member.getMember_gender()}</td>
							<td>${member.getReg_date()}</td>
							<td>${member.getMember_postal_code()}</td>
							<td>${member.getMember_address()}<br>${member.getMember_detailed_address()}</td>
							<td>${member.getMember_tel()}</td>
							<td>${member.getMember_grade()}</td>
							<td>${member.getPoint()}/${member.getPoint()}</td>
							<td><input type="checkbox" name="delMemberIdList" value="${member.getMember_id()}"></td>
						</tr>
					</c:forEach>
				</table>
				<div id="memlistFooter">
					<button type="submit">회원삭제</button>
				</div>
			</c:otherwise>
		</c:choose>
	</form>
</div>