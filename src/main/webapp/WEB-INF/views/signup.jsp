<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<c:if test="${not empty msg}">
<script>
    alert("${msg}");
</script>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
	<form action="signup" method="post">
		이름 : 
		<input type="text" name="name">
		<br><br>
		
		나이 :
		<input type="number" name="age">
		<br><br>
		
		아이디 :
		<input type="text" name="email">
		<br><br>
		
		비밀번호 :
		<input type="password" name="password">
		<br><br>
		
		<button type="submit">회원가입</button>
	</form>
</body>
</html>