<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/todo/inserform.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h1>할일 추가 폼 입니다.</h1>
	<form action="insert.do" method="post">
		<div class="form-group">
			<input class="form-control" type="text" name="content" placeholder="할일 입력..." />
		</div>
		<button class="btn btn-outline-info" type="submit">추가</button>
	</form>
</div>
</body>
</html>