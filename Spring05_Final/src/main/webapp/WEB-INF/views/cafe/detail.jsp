<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/detail.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<style>
	/* 글 내용을 출력할 div 에 적용할 css */
	.contents{
		width: 100%;
		border: 1px dotted #cecece;
	}
	/* 댓글 프로필 이미지를 작은 원형으로 만든다. */
	#profileImage{
		width : 50px;
		height : 50px;
		border : 1px solid #cecece;
		border-radius : 50%;
	}
	/* ul 요소의 기본 스타일 제거 */
	.comments ul{
		padding: 0;
		margin: 0;
		list-style-type: none;
	}
	.comments dt{
		margin-top: 5px;
	}
	.comments dd{
		margin-left: 50px;
	}
	.comment_form textarea, .comment_form button, 
		.comment-insert-form textarea, .comment-insert-form button{
		float: left;
	}
	.comments li{
		clear: left;
	}
	.comments ul li{
		border-top: 1px solid #888;
	}
	.comment_form textarea, .comment-insert-form textarea{
		width: 85%;
		height: 100px;
	}
	.comment_form button, .comment-insert-form button{
		width: 14%;
		height: 100px;
	}
	/* 댓글에 댓글을 다는 폼은 일단 숨긴다. */
	.comments form{
		display: none;
	}
	/* .reply_icon 을 li 요소를 기준으로 배치 하기 */
	.comments li{
		position: relative; /* 이게 기준이 된다.*/
	}
	.comments .reply_icon{
		position: absolute; /* relative 라고 지정된곳에서 움직으고 싶은 것에 지정한다. */
		top: 1em; /* 움직임을 나타냄 */
		left: 1em;
		color:red;
	}
	
	pre {
	  display: block;
	  padding: 9.5px;
	  margin: 0 0 10px;
	  font-size: 13px;
	  line-height: 1.42857143;
	  color: #333333;
	  word-break: break-all;
	  white-space: pre-wrap;
	  background-color: #f5f5f5;
	  border: 1px solid #ccc;
	  border-radius: 4px;
	}
	/* 글 내용중에 이미지가 있으면 최대 폭을 100%로 제한하기*/
	.contents img{
		max-width: 100%;
	}
</style>
</head>
<body>
<div class="container">
	<c:if test="${not empty keyword }"><%--만일 검색키워드가 있다면 --%>
		<p class="alert alert-info">
			<strong>${keyword }</strong> 라는 키워드로 검색한 결과에 대한 자세히 보기 입니다.
		</p>
	</c:if>

	<c:if test="${dto.prevNum ne 0 }">
		<a class="btn btn-outline-info"  href="detail.do?num=${dto.prevNum }&condition=${condition}&keyword=${encodedK}">
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  				<path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/>
			</svg>
			이전글
		</a>
	</c:if>
	<c:if test="${dto.nextNum ne 0 }">
		<a class="btn btn-outline-info" href="detail.do?num=${dto.nextNum }&condition=${condition}&keyword=${encodedK}">
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-down" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  				<path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
			</svg>
			다음글
		</a>
	</c:if>
	<h1>글 상세 페이지</h1>
	<table>
		<tr>
			<th>글번호</th>
			<td>${dto.num }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.writer }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.title }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${dto.regdate }</td>
		</tr>
	</table>
	<div class="contents">${dto.content }</div>
	<a href="list.do">목록 보기</a>
	
	<%-- 
		글 작성자와 로그인 된 아이디가 같을때만 기능을 제공해 준다. 
		즉, 본인이 작성한 글만 수정할수 있도록 하기 위해
	--%>
	<c:if test="${dto.writer eq id }">
		<a class="btn btn-outline-warning btn-sm" href="private/updateform.do?num=${dto.num }">
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  				<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
  				<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
			</svg>
			수정
		</a>
		<a class="btn btn-outline-danger btn-sm" href="javascript:deleteConfirm()">
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  				<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
  				<path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
			</svg>
			삭제
		</a>
	</c:if>
	
	<!-- 댓글 목록 -->
	<div class="comments">
		<ul>
			<c:forEach var="tmp" items="${commentList }">
				<c:choose>
					<c:when test="${tmp.deleted eq 'yes' }">
						<li>삭제된 댓글 입니다.</li>
					</c:when>
					<c:otherwise>
						<li <c:if test="${tmp.num ne tmp.comment_group }">style="padding-left:50px;"</c:if> >
						<c:if test="${tmp.num ne tmp.comment_group }">
							<svg class="reply_icon" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-arrow-return-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
	  							<path fill-rule="evenodd" d="M10.146 5.646a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L12.793 9l-2.647-2.646a.5.5 0 0 1 0-.708z"/>
	  							<path fill-rule="evenodd" d="M3 2.5a.5.5 0 0 0-.5.5v4A2.5 2.5 0 0 0 5 9.5h8.5a.5.5 0 0 0 0-1H5A1.5 1.5 0 0 1 3.5 7V3a.5.5 0 0 0-.5-.5z"/>
							</svg>
						</c:if>
							<dl>
								<dt>
									<c:choose>
										<c:when test="${empty tmp.profile }"><%--empty는 빈문자도 true null도 true 로 인식해 준다. --%>
											<svg id="profileImage" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
		  										<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
											</svg>
										</c:when>
										<c:otherwise>
											<img id="profileImage" src="${pageContext.request.contextPath }${tmp.profile}" />
										</c:otherwise>
									</c:choose>
									<span>${tmp.writer }</span>
									<c:if test="${tmp.num ne tmp.comment_group }">
										@<strong>${tmp.target_id }</strong>
									</c:if>
									<span>${tmp.regdate }</span>
									<a href="javascript:" class="reply_link">답글</a>
									<c:if test="${tmp.writer eq id }">
										| <a href="javascript:deleteComment(${tmp.num })">삭제</a>
									</c:if>
								</dt>
								<dd>
									<pre>${tmp.content }</pre><%--pre 요소는 개행기호를 인식해준다. --%>
								</dd>
							</dl>
							<form class="comment-insert-form" action="private/comment_insert.do" method="post">
								<input type="hidden" name="ref_group" value="${dto.num }"/>
								<input type="hidden" name="target_id" value="${tmp.writer }" />
								<input type="hidden" name="comment_group" value=${tmp.comment_group } />
								<textarea name="content"></textarea>
								<button type="submit">등록</button>
							</form>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
	</div><!-- comments div -->
	
	<div class="comment_form">
		<!-- 원글에 댓글을 작성하는 form -->
		<form action="private/comment_insert.do" method="post">
			<!-- 원글의 글번호가 ref_group 번호가 된다. -->
			<input type="hidden" name="ref_group" value="${dto.num }"/>
			<!-- 원글의 작성자가 댓글의 수신자가 된다. -->
			<input type="hidden" name="target_id" value="${dto.writer }" />
			<textarea name="content"><c:if test="${empty id }">로그인이 필요합니다</c:if></textarea>
			<button type="submit">등록</button>
		</form>
	</div>
	
</div>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.5.1.js"></script>
<script>

	function deleteComment(num){
		var isDelete=confirm("댓글을 삭제 하시겠습니까?");
		if(isDelete){
			location.href="${pageContext.request.contextPath }/cafe/private/comment_delete.do?num="+num+"&ref_group=${dto.num}";
		}
		
	}
	
	//답글 달기 링크를 클릭했을때 실행할 함수 등록
	$(".reply_link").on("click", function(){
		
		//로그인 여부
		var isLogin=${not empty id};
		if(isLogin == false){
			alert("로그인 페이지로 이동합니다.")
			location.href="${pageContext.request.contextPath }/users/loginform.do?"+
					"url=${pageContext.request.contextPath }/cafe/detail.do?num=${dto.num}";
		}
		
		$(this).parent().parent().parent().find(".comment-insert-form")
		.slideToggle();
		if($(this).text()=="답글"){//링크 text를 답글일때 클릭하면
			$(this).text("취소");//취소로 바꾸고
		}else{//취소일때 클릭하면
			$(this).text("답글");//답글로 바꾼다.
		}
	});


	$(".comment_form").on("submit", function(){
		//로그인 여부
		var isLogin=${not empty id};
		if(isLogin == false){
			alert("로그인 페이지로 이동합니다.")
			location.href="${pageContext.request.contextPath }/users/loginform.do?"+
					"url=${pageContext.request.contextPath }/cafe/detail.do?num=${dto.num}";
			return false; //폼 전송 막기
		}
	});

	function deleteConfirm(){
		var isDelete=confirm("삭제하시겠습니까?");
		if(isDelete){
			location.href="private/delete.do?num=${dto.num}";
		}
	};
</script>
</body>
</html>





