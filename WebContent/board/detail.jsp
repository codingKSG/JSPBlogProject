<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<c:if test="${detailRespDto.userId == sessionScope.principal.id}">
		<button type="button" class="btn btn-danger"
			onclick="deleteById(${detailRespDto.id})">삭제</button>
	</c:if>

	<br /> <br />
	<div class="card">
		<div class="card-body">
			<h6 class="m-2">
				작성자 : <i>${detailRespDto.username}</i>
			</h6>
			<hr />
			<h2 class="d-flex justify-content-center">
				<b>${detailRespDto.title}</b>
			</h2>
			<hr />
			<div class="d-flex justify-content-end">
				<div>조회수 : ${detailRespDto.readCount}</div>
			</div>
			<div class="card">
				<p>${detailRespDto.content}</p>
			</div>
			<div class="d-flex justify-content-end">
				<div>작성일 : ${detailRespDto.createDate}</div>
			</div>
		</div>
	</div>
	<!-- 댓글 박스 -->
	<div class="row bootstrap snippets">
		<div class="col-md-12">
			<div class="comment-wrapper">
				<div class="panel panel-info">
					<div class="panel-heading m-2">
						<b>Comment</b>
					</div>
					<div class="panel-body">
						<textarea id="reply__write__form" class="form-control"
							placeholder="write a comment..." rows="2"></textarea>
						<br>
						<button onclick="#" class="btn btn-primary pull-right">댓글쓰기</button>
						<div class="clearfix"></div>
						<hr />

						<!-- 댓글 리스트 시작-->
						<ul id="reply__list" class="media-list">

							<!-- 댓글 아이템 -->
							<li id="reply-1" class="media">
								<div class="media-body">
									<strong class="text-primary">홍길동</strong>
									<p>댓글입니다.</p>
								</div>
								<div class="m-2">

									<i onclick="#" class="material-icons">delete</i>

								</div>
							</li>

						</ul>
						<!-- 댓글 리스트 끝-->
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- 댓글 박스 끝 -->
</div>

<script>
	function deleteById(boardId){
	// 요청과 응답	을 json
		var data = {
			boardId: boardId
		}
		
		$.ajax({
			type: "post",
			url: "/blog/board?cmd=delete",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(result){
			console.log(result);
			if(result.status == "ok"){
				location.href="index.jsp";
			}else{
				alert("삭제에 실패하였습니다.");
			}
		});
	}
</script>

</body>
</html>