<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/todo/updateform.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h1>할 일 수정 폼입니다.</h1>
	<form action="update.do" method="post">
		<div class="form-group">
			<input type="hidden" name="num" value="${todo_dto.num }" />
			<label for="num">번호</label>
			<input class="form-control" type="text" id="num" name="num" value="${todo_dto.num }" disabled /><br />
		</div>
		<div class="form-group">
			<label for="content">할 일</label>
			<input class="form-control" type="text" id="content" name="content" value=${todo_dto.content } /><br />
		</div>
		<button class="btn btn-primary" type="submit">수정 확인</button>
		<button class="btn btn-warning" type="reset">취소</button>
	</form>
</div>
</body>
</html>