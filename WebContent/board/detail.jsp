<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<c:choose>
		<c:when test="${board!=null}">
			<div class="card">
				<div class="card-body">
					<h2 class="d-flex justify-content-center">${board.title}</h2>
					<hr />
					<div class="d-flex justify-content-around">
						<div>조회수 : ${board.readCount}</div>
						<div>작성일 : ${board.createDate}</div>
					</div>
					<div>
						<p>${board.content}</p>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise></c:otherwise>
	</c:choose>

</div>

</body>
</html>