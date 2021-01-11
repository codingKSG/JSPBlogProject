function deleteById(boardId) {

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
	}).done(function(result) {
		console.log(result);
		if (result.status == "ok") {
			location.href = "index.jsp";
		} else {
			alert("삭제에 실패하였습니다.");
		}
	});
}

function replySave(userId, boardId) {

	var data = {
		userId: userId,
		boardId: boardId,
		content: $("#content").val()
	}

	$.ajax({
		type: "post",
		url: "/blog/reply?cmd=save",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(result) {
		if (result.statusCode == 1) {
			console.log(result);
			addReply(result.data);
			$("#content").val("");
			/*location.reload();*/
		} else {
			alert("댓글쓰기 실패")
		}
	});
}

function addReply(data){
	
	console.log(data);
	
	var replyItem = `<li id="reply-${data.id}" class="media">`;
	replyItem += `<div class="media-body">`;
	replyItem += `<strong class="text-primary">${data.username}</strong>`;
	replyItem += `<p>${data.content}</p></div><div class="m-2">`;
	replyItem += `<i onclick="#" class="material-icons">delete</i></div></li>`;
	
	$("#reply__list").prepend(replyItem);
}

function deleteReply(id){
	// 세션의 id와 reply의 userId를 비교해서 같을때 만
	$.ajax({
		type: "post",
		url: "/blog/reply?cmd=delete&id="+id,
		dataType: "json"
	}).done(function(result) {
		if (result.statusCode == 1) {
			console.log(result);
			$("#reply-"+id).remove();
		} else {
			alert("댓글쓰기 실패")
		}
	});
}