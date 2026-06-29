<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.codemate.dto.PostDTO" %>
<%@ page import="com.codemate.dto.MemberDTO" %>
<%PostDTO post = (PostDTO) request.getAttribute("post"); %>
<%MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<body>
	<% if(post != null){ %>
	<h1><%= post.getTitle() %></h1>
	<p>작성자 : <%= post.getPost_writer() %></p>
	<p>조회수 : <%= post.getView_count() %></p>
	<p>작성 시간 : <%=post.getCreated_at() %></p>
	<hr>
	
	<div>
		<%= post.getContent() %>
	</div>
	<br>
	<a href="${pageContext.request.contextPath}/post/list">목록으로</a>
	
		<%if(loginUser != null && loginUser.getId() == post.getMember_id()){ %>
		<a href="${pageContext.request.contextPath}/post/update?id=<%=post.getId()%>">수정하기</a>
		<a href="${pageContext.request.contextPath}/post/delete?id=<%=post.getId()%>">삭제하기</a>
		<%} %>
	<%}else { %>
		<p>게시글을 찾을 수 없습니다.</p>
		<a href="${pageContext.request.contextPath}/post/list">목록으로</a>
	<%} %>
	
	<form action="${pageContext.request.contextPath}/comment/write" method="post">
		<input type="hidden" name="post_id" value="<%=post.getId() %>">
		
		<textarea name="content" rows="4" cols="50"></textarea>
		<br>
		
		<button type="submit">댓글 등록</button>
	
	</form>
	
</body>
</html>