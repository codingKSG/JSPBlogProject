package com.cos.blog.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.reply.dto.SaveReqDto;
import com.cos.blog.service.ReplyService;
import com.cos.blog.util.Script;

//http://localhost:8080/blog/reply
@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReplyController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		
		ReplyService replyService = new ReplyService();
		// http://localhost:8080/blog/reply?cmd=loginForm
		
		if(cmd.equals("save")) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			int boardId = Integer.parseInt(request.getParameter("boardId"));
			String content = request.getParameter("content");
			
			SaveReqDto saveReqDto = SaveReqDto.builder()
					.userId(userId)
					.boardId(boardId)
					.content(content)
					.build();
					
			
			int result = replyService.´ñ±Û¾²±â(saveReqDto);
			
			if(result == 1) {
				response.sendRedirect("board?cmd=detail&id="+boardId);
			} else {
				Script.back(response, "´ñ±Û¾²±â ½ÇÆÐ");
			}
		}
	}
}
