<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="mngr.enc.PassCrypt"%>
<%@ page import = "java.util.List" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<%
  PassCrypt dbPro = PassCrypt.getInstance();
  dbPro.cryptProcess();
  out.println("암호화 성공! 꼭 한번만 수행");
%>