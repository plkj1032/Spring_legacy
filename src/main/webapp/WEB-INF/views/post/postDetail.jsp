<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.codemate.dto.PostDTO" %>
<%@ page import="com.codemate.dto.MemberDTO" %>
<%@ page import="com.codemate.dto.CommentDTO" %>
<%@ page import="java.util.List" %>
<%
	PostDTO post = (PostDTO) request.getAttribute("post"); 
	MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser"); 
	List<CommentDTO> comments = (List<CommentDTO>) request.getAttribute("comments");
%>
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

		<table border="1">
			<tr>
				<th>댓글번호</th>
				<th>내용</th>
				<th>작성자</th>
				<th>작성시간</th>
			</tr>
	<%if(comments != null && !comments.isEmpty()) {%>
		<%for(CommentDTO cto : comments) {%>
			<tr>
				<td><%=cto.getId() %></td>
				<td>
					<%=cto.getContent() %>
					
					<%if(loginUser != null && loginUser.getId() == cto.getMember_id()){ %>
						<form action="${pageContext.request.contextPath}/comment/update" method="post">
							<input type="hidden" name="id" value="<%=cto.getId() %>">
							<input type="hidden" name="post_id" value="<%=post.getId() %>">
							
							<textarea name="content" rows="2" cols="40"><%= cto.getContent() %></textarea>
							
							<button type="submit">수정하기</button>
						</form>
						
						<form action="${pageContext.request.contextPath}/comment/delete" method="post">
							<input type="hidden" name="id" value="<%=cto.getId() %>">
							<input type="hidden" name="post_id" value="<%=post.getId() %>">
							
							<button type="submit">삭제하기</button>
						
						</form>
					<%} %>
					
				</td>
				<td><%=cto.getComment_writer() %></td>
				<td><%=cto.getCreated_at() %></td>
			</tr>
		<%} 
		} else { %>
		<tr>
			<td colspan="5">댓글이 없습니다.</td>
		</tr>
	<% } %>
	</table>
	
	<form action="${pageContext.request.contextPath}/comment/write" method="post">
		<input type="hidden" name="post_id" value="<%=post.getId() %>">
		
		<textarea name="content" rows="4" cols="50"></textarea>
		<br>
		
		<button type="submit">댓글 등록</button>
	
	</form>
	
</body>
</html>