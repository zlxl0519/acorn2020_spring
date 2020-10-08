<%@ page language="java" contentType="text/html; charset=UTF-8"
<<<<<<< HEAD
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/private/update.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<script>
	alert("수정되었습니다.");
	location.href="${pageContext.request.contextPath }/cafe/detail.do?num=${param.num}";
</script>
</body>
</html>



=======
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/cafe/update.jsp</title>
</head>
<body>
<script>
	alert("글을 수정했습니다.");
	location.href="${pageContext.request.contextPath }/cafe/detail.do?num=${param.num}";
</script>
</body>
</html>
>>>>>>> refs/remotes/upstream/master
