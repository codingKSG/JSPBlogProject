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
						<textarea id="content" id="reply__write__form"
							class="form-control" placeholder="write a comment..." rows="2"></textarea>
						<br>
						<button
							onclick="replySave(${sessionScope.principal.id}, ${detailRespDto.id})"
							class="btn btn-primary pull-right">댓글쓰기</button>

						<div class="clearfix"></div>
						<hr />

						<!-- 댓글 리스트 시작-->
						<ul id="reply__list" class="media-list">
							<!-- 댓글 아이템 -->
							<c:forEach var="reply" items="${replies}">
								<li id="reply-${reply.id}" class="media">
									<div class="media-body">
										<strong class="text-primary">${reply.username}</strong>
										<p>${reply.content}</p>
									</div>
									<div class="m-2">
										<c:if test="${sessionScope.principal.id == reply.userId}">
											<i onclick="deleteReply(${reply.id})" class="material-icons">delete</i>
										</c:if>
									</div>
								</li>
							</c:forEach>

						</ul>
						<!-- 댓글 리스트 끝-->
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- 댓글 박스 끝 -->
</div>

<script src="/blog/js/boardDetail.js"></script>

</body>
</html>