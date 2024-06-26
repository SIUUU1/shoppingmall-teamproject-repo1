<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/ClothsMall/qna/css/style.css"/>
<script src="/ClothsMall/qna/update.js"></script>
<html>
<head>
    <title>Reply Update Form</title>
    <script src="qnaUpdate.js"></script>
</head>
<body>
    <h1>Reply Update Form</h1>
    <form id="updateForm">
        <input type="hidden" id="cloth_id" value="${cloth_id}">
        <input type="hidden" id="cloth_category" value="${cloth_category}">
        <input type="hidden" id="qna_id" value="${qna_id}">
        <label for="updateCont">Reply:</label>
        <textarea id="updateCont" name="reply" rows="4" cols="50">${reply}</textarea><br>
        <button type="button" id="update">Update</button>
        <button type="button" id="cancle">Cancel</button>
    </form>
</body>
</html>
