<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/angularjs/test01.jsp</title>
<!-- angularjs 로딩 시키기 -->
<script src="${pageContext.request.contextPath }/resources/js/angular.min.js"></script>
</head>
<body ng-app>
<h1>hello angular js!</h1>
<!-- model 은 데이터 입력값을 msg 라는 이름으로 담는다 . {{msg}} 이 데이터를 출력한다는 의미 
	자바스크립트와 다르게
	데이터는 화면에 화면은 데이터에 양방향으로 연결 되있다. (model 은 view 에 view는 model에 연결되있다.)
	angularjs 를 mvvm frame work 라고 도 한다. 
-->
<input ng-model="msg" type="text" placeholder="메세지 입력..."/>
<!-- document.querySelector().addEventListener("input",
		function(){
			var msg=xxx;
			document.querySelector().innerText=msg;
		}
	) -->
<p>{{msg}}</p>
<h1 ng-init="friends=['김구라', '해골', '원숭이']">친구 목록 입니다.</h1>
<ul>
	<!-- anglarjs 가 클라이언트 화면에서 인식되는 것이다. 
		ng-repeat 반복문
	-->
	<li ng-repeat="tmp in friends">{{tmp}}</li>
</ul>
<h1>체크박스를 체크 해 보세요.</h1>
<!-- 조건부로 이미지를 보이게 할것인지 말것인지 결정할수 있다.
	isShow라는 이름으로 boolean type 데이터를 관리하겠다.
	ng-show="isShow" isShow 데이터를 반영하겠다는 뜻
	validation 회원가입할때 쓰기 편하다.
 -->
<input type="checkbox" ng-model="isShow" /><strong>{{isShow}}</strong><br />
<img src="../resources/images/kim1.png" ng-show="isShow" />
</body>
</html>