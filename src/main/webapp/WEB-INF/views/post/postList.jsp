<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.codemate.dto.PostDTO"%>
<%@	page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty msg }">
	<script>
		alert("${msg}");
	</script>
</c:if>

<% List<PostDTO> list = (List<PostDTO>) request.getAttribute("lists"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}">홈으로</a>
	<a href="${pageContext.request.contextPath}/post/write">글쓰기</a>
	
	<table border="1">
		<tr>
			<th>게시글 번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성시간</th>
		</tr>
		<% if(list != null && !list.isEmpty()){ %>
			<% for(PostDTO post : list) {%>
				<tr>
					<td><%= post.getId() %></td>
					<td>
						<a href="${pageContext.request.contextPath}/post/detail?id=<%= post.getId() %>">
							<%= post.getTitle() %>
						</a>
					</td>
					<td><%= post.getPost_writer() %></td>
					<td><%= post.getView_count() %></td>
					<td><%= post.getCreated_at() %></td>
				</tr>
			<%} %>
		<%} else {%>
			<tr>
				<td colspan="5">게시글이 없습니다.</td>
			</tr>	
		<%} %>
	
	</table>
	
	
</body>
</html>