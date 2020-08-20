<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 폼</title>
</head>
<body>
<div class="container">
	<h1>회원 가입 폼 입니다.</h1>
	<form action="signup.do" method="post">
		<div class="form-group">
			<label for="id">아이디</label>
			<input type="text" name="id" id="id" />
		</div>
		<div class="form-group">
			<label for="pwd">비밀번호</label>
			<input type="password" name="pwd" id="pwd" />
		</div>
		
	</form>
</div>
</body>
</html>