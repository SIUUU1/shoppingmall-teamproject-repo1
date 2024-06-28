<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<script src="<%=request.getContextPath()%>/backmain/memberList.js"></script>

<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=<%=request.getContextPath()%>/index.do">
</c:if>

<div id="memberList">
	<form method="post" action="<%=request.getContextPath()%>/mg/memberDeletePro.do">
		<c:choose>
			<c:when test="${count == 0} }">
				<span>등록된 회원 수가 없습니다</span>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>체크</td>
						<td>아이디</td>
						<td>비밀번호</td>
						<td>이름</td>
						<td>성별</td>
						<td>가입 날짜</td>
						<td>우편번호</td>
						<td>주소</td>
						<td>전화번호</td>
						<td>등급</td>
						<td>마일리지/포인트</td>
					</tr>
					<c:forEach var="member" items="${memberList}">
						<tr>
							<td>
								<input type="checkbox" name="delMemberIdList" value="${member.getMember_id()}">
							</td>
							<td>${member.getMember_id()}</td>
							<td>${member.getMember_passwd()}</td>
							<td>${member.getMember_name()}</td>
							<td>${member.getMember_gender()}</td>
							<td>${member.getReg_date()}</td>
							<td>${member.getMember_postal_code()}</td>
							<td>${member.getMember_address()}
								<br>
								${member.getMember_detailed_address()}
							</td>
							<td>${member.getMember_tel()}</td>
							<td>${member.getMember_grade}</td>
							<td>마일리지&nbsp;${member.getPoint}
							<br>포인트&nbsp;${member.getPoint}</td>
						</tr>
					</c:forEach>
				</table>
				<input type="submit" value="선택항목 삭제">
			</c:otherwise>
		</c:choose>
	</form>

</div>