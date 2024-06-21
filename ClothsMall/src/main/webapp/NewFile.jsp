<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="shoppingmall.StudentDAO" />
<%
boolean check = dao.idCheck("abc");
if (check == true) {
	out.print("데이타베이스성공 1");
} else {
	out.print("데이타베이스실패 1");
}
%>