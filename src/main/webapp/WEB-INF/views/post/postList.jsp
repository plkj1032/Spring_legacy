<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.codemate.dto.PostDTO"%>
<%@	page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
	Integer totalPageObj = (Integer)request.getAttribute("totalPage");
	Integer currentPageObj = (Integer)request.getAttribute("currentPage");
	Integer sizeParamObj = (Integer)request.getAttribute("sizeParam");
	
	int totalPage = totalPageObj != null ? totalPageObj : 1;
	int currentPage = currentPageObj != null ? currentPageObj : 1;
	int sizeParam = sizeParamObj != null ? sizeParamObj : 1;
%>
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
	
		<%
			for(int i = 1; i <= totalPage; i++)
			{
				if( i == currentPage )
				{
					%>
						<b><%= i %></b>
					<%
				} else{
					%> 
						<a href="${pageContext.request.contextPath}/post/list?pageParam=<%=i %>&sizeParam=<%=sizeParam %>">
						<%=i %>
						</a>				
					<%
				}
			}
		
		%>
	
		
		<form action="${pageContext.request.contextPath}/post/list" method="get">
			<select name="sizeParam">
				<option value="5" <%= sizeParam == 5 ? "selected" : "" %>>5개씩 보기</option>
		        <option value="10" <%= sizeParam == 10 ? "selected" : "" %>>10개씩 보기</option>
		        <option value="20" <%= sizeParam == 20 ? "selected" : "" %>>20개씩 보기</option>
		        <option value="50" <%= sizeParam == 50 ? "selected" : "" %>>50개씩 보기</option>
			</select>
			
			<input type="hidden" name="pageParam" value="1">
			<button type="submit">적용</button>
		
		</form>
	
</body>
</html>