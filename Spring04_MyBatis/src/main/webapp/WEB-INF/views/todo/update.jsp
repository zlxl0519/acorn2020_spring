<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/todo/update.jsp</title>
</head>
<body>
<script>
	alert("할일이 수정되었습니다");
	location.href="${pageContext.request.contextPath }/todo/list.do";
</script>
</body>
</html>