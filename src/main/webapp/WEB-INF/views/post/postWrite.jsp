<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<c:if test="${not empty msg}">
	<script>
		alert("${msg}");
	</script>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성하기</title>
</head>
<body>
	<h1>게시글 작성</h1>
	<form action="${pageContext.request.contextPath}/post/write" method="post">
	
		제목 : 
		<input type="text" name="title">
		<br><br>
		
		내용 :
		<textarea name="content" rows="10" cols="50"></textarea>
		<br><br>
		
		<button type="submit">작성하기</button>
	</form>
	<br>
	<a href="${pageContext.request.contextPath}/post/list">목록으로</a>
</body>
</html>