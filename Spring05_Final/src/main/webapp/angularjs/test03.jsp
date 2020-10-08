<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/angularjs/test03.jsp</title>
<!-- bootstrap css 로딩 시키기 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<!-- angularjs 로딩 시키기 -->
<script src="${pageContext.request.contextPath }/resources/js/angular.min.js"></script>
</head>
<!-- body 에서 일어나는 일은 angular 로 관리를 하겠다라는 의미 -->
<body ng-app>
<%
	request.setAttribute("myName", "김구라");
	String[] friends={"cat", "dog", "elephant"};
	request.setAttribute("friends", friends);
%>
<div class="container">
	<h1 ng-init="a='btn-primary'">클래스 속성 조작하기</h1>
	<input type="text" ng-model="b" /><br />
	<button class="btn btn-primary">버튼1</button>
	<button class="btn {{a}}">버튼2</button>
	<button class="btn {{b}}">버튼3</button>
	<button ng-class="['btn', 'btn-warning']">버튼4</button>
	<!-- 속성을 추가할지말지를 boolean type 으로 관리하는것 -->
	<button ng-class="{'btn' :true, 'btn-primary':true}">버튼5</button>
	<br />
	<input type="checkbox" ng-model="isLarge"/>
	<button class="btn btn-success" ng-class="{'btn-lg':isLarge}">버튼6</button>
</div>
</body>
</html>
=======
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/angularjs/test03.jsp</title>
<!-- bootstrap css 로딩 시키기 -->
<link href="../resources/css/bootstrap.css" rel="stylesheet"/>
<!-- angularjs 로딩 시키기 -->
<script src="../resources/js/angular.min.js"></script>
</head>
<!-- body 에서 일어나는 일은 angular 로 관리를 하겠다라는 의미 -->
<body ng-app>
<div class="container">
	<h1 ng-init="a='btn-primary'">클래스 속성 조작하기</h1>
	<input type="text" ng-model="b"/><br/>
	<button class="btn btn-primary">버튼1</button>
	<button class="btn {{a}}">버튼2</button>
	<button	class="btn {{b}}">버튼3</button>
	<button ng-class="['btn', 'btn-primary']">버튼4</button>
	<button ng-class="{'btn':true, 'btn-primary':true}">버튼5</button>
	<br/>
	<input type="checkbox" ng-model="isLarge"/>
	<button class="btn btn-success" ng-class="{'btn-lg':isLarge}">버튼6</button>
</div>
</body>
</html>
















>>>>>>> refs/remotes/upstream/master
