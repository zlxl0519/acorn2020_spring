<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<c:choose>
		<c:when test="${empty id }">
			<a href="${pageContext.request.contextPath }/users/loginform.do">로그인</a>
			<a href="${pageContext.request.contextPath }/users/signup_form.do">회원가입</a>
		</c:when>
		<c:otherwise>
			<a href="#"></a>님 환영합니다.
			<a href="${pageContext.request.contextPath }/users/logout.do">로그아웃</a>
		</c:otherwise>
	</c:choose>
	<h1>인덱스 페이지 입니다.</h1>
	<p>Spring Framework 동작중...</p>
	<ul>
		<li><a href="#">카페글 목록 보기</a></li>
	</ul>
</div>
</body>
</html>
