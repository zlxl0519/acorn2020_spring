<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/todo/list.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<style>
	table> thead{
		color: white;
	}
</style>
</head>
<body>
<div class="container">
	<h1>할일 목록 페이지 입니다.</h1>
	<table class="table table-hover">
		<thead class="bg-primary">
			<tr>
				<th>번호</th>
				<th>할 일</th>
				<th>날짜</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tmp" items="${todo_list }">
				<tr>
					<td>${tmp.num }</td>
					<td>${tmp.content }</td>
					<td>${tmp.regdate }</td>
					<td><a href="updateform.do?num=${tmp.num }">수정</a></td>
					<td><a href="delete.do?num=${tmp.num }" style="color:red">삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="insertform.do">할일 추가하기</a>
</div>
</body>
</html>