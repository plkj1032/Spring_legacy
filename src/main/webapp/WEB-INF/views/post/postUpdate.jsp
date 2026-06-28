<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.codemate.dto.PostDTO" %>
<%PostDTO post = (PostDTO) request.getAttribute("post"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<%if(post != null){ %>
	<h1>게시글 수정</h1>
	<form action="${pageContext.request.contextPath}/post/update" method="post">
	
		<input type="hidden" name="id" value="<%=post.getId() %>">
		
		제목 :
		<input type="text" name="title" value="<%=post.getTitle()%>">
		<br><br>
		
		내용 :
		<textarea name="content" id="content" cols="50"><%=post.getContent() %></textarea>
		<br><br>
		
		<button type="submit">수정하기</button>
	</form>
	<%} else { %>
		<p>게시글 정보를 찾을 수 없습니다.</p>
		<a href="${pageContext.request.contextPath}/post/list">목록으로</a>
	<% } %>
</body>
</html>