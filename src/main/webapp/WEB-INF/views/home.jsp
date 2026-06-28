<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.codemate.dto.MemberDTO" %>
    
<% MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CodeMate</title>
<link rel="stylesheet"
href="${pageContext.request.contextPath}/resources/css/home.css">
</head>
<body>
<div class="container">
	<h1>CodeMate</h1>
	
	<p class="subTitle">
		개발자를 위한 커뮤니티
	</p>
	<%if(loginUser == null) {%>
	<div class="btnBox">
		<a href="${pageContext.request.contextPath}/member/signup" class="btn">
			회원가입
		</a>
		
		<a href="${pageContext.request.contextPath}/member/login" class="btn">
			로그인
		</a>
		
		<a href="${pageContext.request.contextPath}/post/list" class="btn">
			게시글 가기
		</a>
	</div>
	<%} else { %>
	<div class="loginBox">
		<p class="welcome">
			<strong><%=loginUser.getName() %></strong>님 환영합니다!
		</p>
		
		<div class="btnBox">
			<a href="${pageContext.request.contextPath}/post/list" class="btn">
				게시판 가기
			</a>
			
			<a href="${pageContext.request.contextPath}/member/logout" class="btn logoutBtn">
				로그아웃
			</a>
		</div>
	
	</div>
	<%} %>

</div>
</body>
</html>