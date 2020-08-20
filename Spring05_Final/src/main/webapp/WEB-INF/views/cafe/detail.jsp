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
	.loader{
		position: fixed; /* 좌하단 고정된 위치에 배치 하기 위해 */
		width: 100%;
		left: 0;
		bottom: 0;
		text-align: center; /* 이미지를 좌우로 가운데  정렬 */
		z-index: 1000;
		display: none; /* 일단 숨겨 놓기 */
	}
	/* 댓글 프로필 이미지를 작은 원형으로 만든다. */
	.profile-image{
		width: 50px;
		height: 50px;
		border: 1px solid #cecece;
		border-radius: 50%;
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
	.comment-form textarea, .comment-form button{
		float: left;
	}
	.comments li{
		clear: left;
	}
	.comments ul li{
		border-top: 1px solid #888;
	}
	.comment-form textarea{
		width: 85%;
		height: 100px;
	}
	.comment-form button{
		width: 15%;
		height: 100px;
	}
	/* 댓글에 댓글을 다는 폼은 일단 숨긴다. */
	.comments .comment-form{
		display: none;
	}
	/* .reply_icon 을 li 요소를 기준으로 배치 하기 */
	.comments li{
		position: relative;
	}
	.comments .reply-icon{
		position: absolute;
		top: 1em;
		left: 1em;
		color: red;
	}
	pre {
	  display: block;
	  padding: 9.5px;
	  margin: 0 0 10px;
	  font-size: 13px;
	  line-height: 1.42857143;
	  color: #333333;
	  word-break: break-all;
	  word-wrap: break-word;
	  background-color: #f5f5f5;
	  border: 1px solid #ccc;
	  border-radius: 4px;
	}
	/* 글 내용중에 이미지가 있으면 최대 폭을 100%로 제한하기 */
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
	
	<hr />
	
	<!-- 댓글 목록 -->
	<div class="comments">
		<ul>
			<c:forEach var="tmp" items="${commentList }">
				<c:choose>
					<c:when test="${tmp.deleted eq 'yes' }">
						<li>삭제된 댓글 입니다.</li>
					</c:when>
					<c:otherwise>
						<li id="comment${tmp.num }" <c:if test="${tmp.num ne tmp.comment_group }">style="padding-left:50px;"</c:if> ><%--대댓글인 경우 --%>
							<c:if test="${tmp.num ne tmp.comment_group }"><%--대댓글인 경우 --%>
								<svg class="reply-icon" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-arrow-return-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
		  							<path fill-rule="evenodd" d="M10.146 5.646a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L12.793 9l-2.647-2.646a.5.5 0 0 1 0-.708z"/>
		  							<path fill-rule="evenodd" d="M3 2.5a.5.5 0 0 0-.5.5v4A2.5 2.5 0 0 0 5 9.5h8.5a.5.5 0 0 0 0-1H5A1.5 1.5 0 0 1 3.5 7V3a.5.5 0 0 0-.5-.5z"/>
								</svg>
							</c:if>
							<dl>
								<dt><%--프로필 사진, 작성자, 날짜 --%>
									<c:choose><%--프로필 사진이 없을때와 있을때 --%>
										<c:when test="${empty tmp.profile }"><%--empty는 빈문자도 true null도 true 로 인식해 준다. --%>
											<svg class="profile-image" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
		  										<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
											</svg>
										</c:when>
										<c:otherwise>
											<img class="profile-image" src="${pageContext.request.contextPath }${tmp.profile}" />
										</c:otherwise>
									</c:choose>
									<span>${tmp.writer }</span>
									<c:if test="${tmp.num ne tmp.comment_group }"><%--대댓글인 경우 --%>
										@<i>${tmp.target_id }</i>
									</c:if>
									<span>${tmp.regdate }</span>
									<a data-num="${tmp.num }" href="javascript:" class="reply-link">답글</a>
									<c:if test="${tmp.writer eq id }"><!-- 자바스크립트에서 요소에 어떤 정보를 저장하기 위해 속성을 만들수 있다. (data-xxx="x" 형태) -->
										| <a data-num="${tmp.num }" href="javascrip:" class="comment-update-link">수정</a>
										| <a data-num="${tmp.num }" href="javascript:" class="comment-delete-link">삭제</a>
									</c:if>
								</dt>
								<dd>
									<pre>${tmp.content }</pre><%--pre 요소는 개행기호를 인식해준다. --%>
								</dd>
							</dl>
							<%--대댓글을 다는 폼, 답글을 누르기 전까지 숨겨둔다. --%>
							<form class="comment-form re-insert-form" action="private/comment_insert.do" method="post">
								<input type="hidden" name="ref_group" value="${dto.num }"/>
								<input type="hidden" name="target_id" value="${tmp.writer }" />
								<input type="hidden" name="comment_group" value="${tmp.comment_group }" />
								<textarea name="content"></textarea>
								<button type="submit">등록</button>
							</form>
							<!-- 로그인된 아이디와 댓글의 작성자가 같으면 수정 폼 출력 -->
							<c:if test="${tmp.writer eq id }">
								<form class="comment-form update-form" action="private/comment_update.do" method="post">
									<input type="hidden" name="num" value="${tmp.num }"/>
									<textarea name="content">${tmp.content }</textarea>
									<button type="submit">수정</button>
								</form>
							</c:if>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
	</div><!-- comments  -->
	<!-- 
		위에 float:left 에 영향을 받지 않게 하기 위해 
		위에 form 에 textarea, button 에 css가 적용되서 아래에까지 영향을 미치는데 그걸 막기 위해 class="clearfix"를 씀
	 -->
	<div class="clearfix"></div>
	
	<div class="page-display">
		<ul class="pagination pagination-sm">
			<c:if test="${startPageNum ne 1 }">
				<li class="page-item"><a class="page-link" href="detail.do?num=${dto.num }&pageNum=${startPageNum-1 }">Prev</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
				<c:choose>
					<c:when test="${i eq pageNum }">
						<li class="page-item active"><a class="page-link" href="detail.do?num=${dto.num }&pageNum=${i }">${i }</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="detail.do?num=${dto.num }&pageNum=${i }">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${endPageNum lt totalPageCount }">
				<li class="page-item"><a class="page-link" href="detail.do?num=${dto.num }&pageNum=${endPageNum+1 }">Next</a></li>
			</c:if>
		</ul>	
	</div>
	<!-- 원글에 댓글을 작성하는 form -->
	<form class="comment-form insert-form" action="private/comment_insert.do" method="post">
		<!-- 원글의 글번호가 ref_group 번호가 된다. -->
		<input type="hidden" name="ref_group" value="${dto.num }"/>
		<!-- 원글의 작성자가 댓글의 수신자가 된다. -->
		<input type="hidden" name="target_id" value="${dto.writer }" />
		<textarea name="content"><c:if test="${empty id }">로그인이 필요합니다</c:if></textarea>
		<button type="submit">등록</button>
	</form>
</div><!-- .container -->
<div class="loader">
	<img src="${pageContext.request.contextPath }/resources/images/ajax-loader.gif"/>
</div>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.5.1.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>
<script>
	//댓글 수정 링크를 눌렀을때 호출되는 함수 등록 
	/*
		이벤트명, 선택자, 함수 문서에서 이벤트가 이 선택자에게 걸리면 함수를 실행 이라는 의미
		ajax 로 동적으로 추가가 되면 원래 대로 썼을때는 동작을 안하기 때문에
		$(".comment-delete-link").on("click", function(){})
	*/
	
	$(document).on("click", ".comment-update-link", function(){
		/*
			click 이벤트가 일어난 댓글 수정 링크에 저장된 data-num 속성의 값을
			읽어와서 id 선택자를 구성한다.
		*/
		//구성된 id 선택자를 이용해서 원하는 li 요소에서 .update-form 을 찾아서 동작하기
		var selector="#comment"+$(this).attr("data-num");
		$(selector).find(".update-form").slideToggle();
	});
	
	//로딩한 jquery.form.min.js jquery 플러그인의 기능을 이용해서 댓글 수정폼을
	//ajax 요청을 통해 전송하고 응답받기
	$(document).on("submit", ".update-form", function(){
		//그 폼을 ajaxSubmit 하겠다는 의미
		//이벤트가 일어난 폼을 ajax로 전송되도록 하고
		$(this).ajaxSubmit(function(data){
			//console.log(data);
			//수정이 일어난 댓글의 li 요소를 선택해서 원하는 작업을 한다.
			var selector="#comment"+data.num; //"#comment6" 형식의 선택자 구성
			
			//댓글 수정 폼을 안보이게 한다.
			$(selector).find(".update-form").slideUp();
			//pre 요소에 출력된 내용 수정하기
			$(selector).find("pre").text(data.content);
		});
		//폼 전송을 막아준다.
		return false;
	});
	
	$(document).on("click",".comment-delete-link", function(){
		//삭제할 글번호
		var num=$(this).attr("data-num");
		var isDelete=confirm("댓글을 삭제 하시겠습니까?");
		if(isDelete){
			location.href="${pageContext.request.contextPath }/cafe/private/comment_delete.do?num="+num+"&ref_group=${dto.num}";
		}
	});
		
	//답글 달기 링크를 클릭했을때 실행할 함수 등록
	$(document).on("click",".reply-link", function(){
		
		//로그인 여부
		var isLogin=${not empty id};
		if(isLogin == false){
			alert("로그인 페이지로 이동합니다.")
			location.href="${pageContext.request.contextPath }/users/loginform.do?"+
					"url=${pageContext.request.contextPath }/cafe/detail.do?num=${dto.num}";
		}
		
		var selector="#comment"+$(this).attr("data-num");
		$(selector).find(".re-insert-form").slideToggle();
		
		if($(this).text()=="답글"){//링크 text를 답글일때 클릭하면
			$(this).text("취소");//취소로 바꾸고
		}else{//취소일때 클릭하면
			$(this).text("답글");//답글로 바꾼다.
		}
	});


	$(document).on("submit",".insert-form", function(){
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
	
	
	////페이지가 처음 로딩될때 1page 를 보여준다고 가정
	//var currentPage=1;
	////전체 페이지의 수를 javascript 변수에 담아준다.
	//var totalPageCount=${totalPageCount};
	//
	///*
	//	페이지 로딩 시점에 document 의 높이가 window 의 실제 높이 보다 작고
	//	전체 페이지의 갯수가(totalPageCount) 현재페이지(currentPage)
	//	보다 크면 추가로 댓글을 받아오는 ajax 요청을 해야한다.
	//*/
	//var dH=$(document).height();//문서의 높이
	//var wH=window.screen.height;//window 의 높이
	//if(dH < wH && totalPageCount > currentPage){
	//	//로딩 이미지 띄우기
	//	$(".loader").show();
	//	
	//	currentPage++; //페이지를 1 증가 시키고 
	//	//해당 페이지의 내용을 ajax  요청을 해서 받아온다. 
	//	$.ajax({
	//		url:"ajax_comment_list.do",
	//		method:"get",
	//		data:{pageNum:currentPage, ref_group:${dto.num}},
	//		success:function(data){
	//			console.log(data);
	//			//data 가 html 마크업 형태의 문자열 
	//			$(".comments ul").append(data);
	//			//로딩 이미지를 숨긴다. 
	//			$(".loader").hide();
	//		}
	//	});		
	//}
 	//
	////웹브라우저에 scoll 이벤트가 일어 났을때 실행할 함수 등록 
	//$(window).on("scroll", function(){
	//	if(currentPage == totalPageCount){//만일 마지막 페이지 이면 
	//		return; //함수를 여기서 종료한다. 
	//	}
	//	//위쪽으로 스크롤된 길이 구하기
	//	var scrollTop=$(window).scrollTop();
	//	//window 의 높이
	//	var windowHeight=$(window).height();
	//	//document(문서)의 높이
	//	var documentHeight=$(document).height();
	//	//바닥까지 스크롤 되었는지 여부
	//	var isBottom = scrollTop+windowHeight + 10 >= documentHeight;
	//	if(isBottom){//만일 바닥까지 스크롤 했다면...
	//		//로딩 이미지 띄우기
	//		$(".loader").show();
	//		
	//		currentPage++; //페이지를 1 증가 시키고 
	//		//해당 페이지의 내용을 ajax  요청을 해서 받아온다. 
	//		$.ajax({
	//			url:"ajax_comment_list.do",
	//			method:"get",
	//			data:{pageNum:currentPage, ref_group:${dto.num}},
	//			success:function(data){
	//				console.log(data);
	//				//data 가 html 마크업 형태의 문자열 
	//				$(".comments ul").append(data);
	//				//로딩 이미지를 숨긴다. 
	//				$(".loader").hide();
	//			}
	//		});
	//	}
	//});
	
</script>
</body>
</html>





