<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/angularjs/test11.jsp</title>
<script src="${pageContext.request.contextPath }/resources/js/angular.min.js"></script>
<script>
	var myApp=angular.module("myApp", []);
	myApp.controller("msgCtrl", function($scope){
		//페이지가 로딩되는 시점에 msgs 라는 모델을 정의하고 배열 저장하기
		$scope.msgs=[{msg:"하나"},{msg:"두울"}];
		//페이지가 로딩되는 시점에 send 라는 함수 정의하기  
	    $scope.send=function(){
			//msgs 배열에 새로운 object 만들어서 저장하기(view 에 자동 반영된다.)
	       $scope.msgs.push({msg:$scope.inputMsg});
			$scope.inputMsg="";
	    };
	    
		//ajax 통신으로 받아온 데이터라고 가정하자
		var data=[];
		data.push({name:"아이폰 6",snippet:"가볍고 이쁜 폰입니다.",price:80});
		data.push({name:"겔럭시 S5",snippet:"삼성에서 만든 방수폰 입니다.",price:110});
		data.push({name:"Nexus 6",snippet:"Google 테블릿 입니다.",price:65});
		data.push({name:"옵티머스 G",snippet:"LG 핸드폰 입니다.", price:80});
		
		//전화기 목록을 담을 배열
		$scope.phones=[];
		//전화기 목록을 얻어오느 함수
		$scope.getPhones=function(){
			$scope.phones.push({name:"아이폰 6",snippet:"가볍고 이쁜 폰입니다.",price:80});
			$scope.phones.push({name:"겔럭시 S5",snippet:"삼성에서 만든 방수폰 입니다.",price:110});
			$scope.phones.push({name:"Nexus 6",snippet:"Google 테블릿 입니다.",price:65});
			$scope.phones.push({name:"옵티머스 G",snippet:"LG 핸드폰 입니다.", price:80});
		};
	    
	    $scope.member=[{num:$scope.num,name:$scope.name,addr:$scope.addr}];
	    $scope.send2=function(){
	    	$scope.member.push({num:$scope.num,name:$scope.name,addr:$scope.addr});
	    	
	    }
	});
</script>
</head>
<body ng-app="myApp">
	<div class="container"  ng-controller="msgCtrl">
		<input type="text" placeholder="메세지 입력..." ng-model="inputMsg"/>
		<button ng-click="send()">전송</button>
		<ul>
        	<li ng-repeat="tmp in msgs">{{tmp.msg}}</li>
      	</ul><br />
      	<h1>전화기 목록입니다.</h1>
      	<button ng-click="getPhones()">목록 받아오기</button>
      	<table>
      		<thead>
      			<tr>
      				<th>이름</th>
      				<th>설명</th>
      				<th>가격</th>
      			</tr>
      		</thead>
      		<tbody>
      			<tr ng-repeat="tmp in phones | filter:keyword | orderBy:orderValue :isReverse"><!-- orderValue:true 를 넣으면 역순이 된다. -->
      				<td ng-bind="tmp.name"></td>
      				<td ng-bind="tmp.snippet"></td>
      				<td>{{tmp.price}}</td>
      			</tr>
      		</tbody>
      	</table>
      	<input ng-model="keyword" type="text" placeholder="검색어 입력..."/>
      	<select ng-model="orderValue">
      		<option value="">정렬</option>
      		<option value="name">이름</option>
      		<option value="price">가격</option>
      	</select>
      	<label>
      		<input type="checkbox" ng-model="isReverse"/> 역순
      	</label>
      	<p>입력한 검색어: <strong>{{keyword}}</strong></p>
      	<p>정렬 방식: <strong>{{orderValue}}</strong></p>
      	
      	<h2>테이블 테스트</h1>
      	<input type="text" placeholder="번호입력..." ng-model="num"/>
      	<input type="text" placeholder="이름입력..." ng-model="name"/>
      	<input type="text" placeholder="주소입력..." ng-model="addr"/>
      	<button ng-click="send2()">전송2</button>
      	<table>
      		<thead>
      			<tr>
      				<th>번호</th>
      				<th>이름</th>
      				<th>주소</th>
      			</tr>
      		</thead>
      		<tbody>
      			<tr ng-repeat="tmp in member">
      				<td >{{tmp.num}}</td>
      				<td >{{tmp.name}}</td>
      				<td >{{tmp.addr}}</td>
      			</tr>
      		</tbody>
      	</table>
	</div>
</body>
</html>