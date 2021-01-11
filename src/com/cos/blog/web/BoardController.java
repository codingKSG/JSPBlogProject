package com.cos.blog.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.dto.DetailRespDto;
import com.cos.blog.domain.board.dto.SaveReqDto;
import com.cos.blog.domain.board.dto.UpdateReqDto;
import com.cos.blog.domain.reply.dto.FindAllReqDto;
import com.cos.blog.domain.board.dto.DeleteReqDto;
import com.cos.blog.domain.board.dto.DeleteRespDto;
import com.cos.blog.domain.user.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.ReplyService;
import com.cos.blog.util.Script;
import com.google.gson.Gson;

//http://localhost:8000/blog/board
@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
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
		BoardService boardService = new BoardService();
		ReplyService replyService = new ReplyService();
		HttpSession session = request.getSession();
		// http://localhost:8000/blog/board?cmd=saveForm

		if (cmd.equals("saveForm")) {
			User principal = (User) session.getAttribute("principal");
			if (principal != null) {
				RequestDispatcher dis = request.getRequestDispatcher("board/saveForm.jsp");
				dis.forward(request, response);
			} else {
				RequestDispatcher dis = request.getRequestDispatcher("user/loginForm.jsp");
				dis.forward(request, response);
			}
		} else if (cmd.equals("save")) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			SaveReqDto dto = SaveReqDto.builder().userId(userId).title(title).content(content).build();
			int result = boardService.�۾���(dto);
			if (result == 1) { // ����
				response.sendRedirect("index.jsp");
			} else {
				Script.back(response, "�۾��� ����");
			}
		} else if (cmd.equals("list")) {
			// request�� ��� RequestDispatcher���� �̵�
			String keyword = request.getParameter("keyword");
			int page = Integer.parseInt(request.getParameter("page"));
			List<Board> boards;
			int boardCount;
			if(keyword == null) {
				boards = boardService.�۸�Ϻ���(page);
				boardCount = boardService.�۰���();
			} else {
				boards = boardService.�۰˻�(keyword, page);
				boardCount = boardService.�۰���(keyword);
			}			
			
			
			int lastPage = (boardCount - 1) / 4;
			double currentPosition = (double) page / (lastPage) * 100;

			request.setAttribute("boards", boards);
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("currentPosition", currentPosition);

			RequestDispatcher dis = request.getRequestDispatcher("board/list.jsp");
			dis.forward(request, response);
		} else if (cmd.equals("detail")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			DetailRespDto detailRespDto = boardService.�󼼺���(id);
			List<FindAllReqDto> replies = replyService.���(id);

			request.setAttribute("detailRespDto", detailRespDto);
			request.setAttribute("replies", replies);

			RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("delete")) {

			// 1. ��û ���� json �����͸� �ڹ� ������Ʈ�� �Ľ�
			BufferedReader br = request.getReader();
			String data = br.readLine();
			
//			System.out.println("delete : "+data);

			Gson gson = new Gson();
			DeleteReqDto dto = gson.fromJson(data, DeleteReqDto.class);

			// 2. DB���� id������ �� ����
			int result = boardService.�Խñۻ���(dto.getBoardId());

			// 3. ������ json �����͸� ����
			DeleteRespDto respDto = new DeleteRespDto();
			if(result == 1) {
				respDto.setStatus("ok");
			}else {
				respDto.setStatus("fail");
			}
			String respData = gson.toJson(respDto);
			System.out.println("respData : "+respData);
			PrintWriter out = response.getWriter();
			out.print(respData);
			out.flush();
		} else if(cmd.equals("search")) {
			String keyword = request.getParameter("keyword");
			int page = Integer.parseInt(request.getParameter("page"));
			
			List<Board> boards = boardService.�۰˻�(keyword, page);
			request.setAttribute("boards", boards);
			
			int boardCount = boardService.�۰���(keyword);
			int lastPage = (boardCount - 1) / 4;
			double currentPosition = (double) page / (lastPage) * 100;

			request.setAttribute("lastPage", lastPage);
			request.setAttribute("currentPosition", currentPosition);

			RequestDispatcher dis = request.getRequestDispatcher("board/list.jsp");
			dis.forward(request, response);
			
		} else if(cmd.equals("updateForm")) {
			int id = Integer.parseInt(request.getParameter("id"));
			DetailRespDto detailRespDto = boardService.�󼼺���(id);
			request.setAttribute("detailRespDto", detailRespDto);
			RequestDispatcher dis = request.getRequestDispatcher("board/updateForm.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			System.out.println(id + ", "+ title+ ", "+content);
			UpdateReqDto updateReqDto = UpdateReqDto.builder()
					.id(id)
					.title(title)
					.content(content)
					.build();
			
			int result = boardService.�ۼ���(updateReqDto);
			
			if (result == 1) { // ����
				
				response.sendRedirect("/blog/board?cmd=detail&id="+id);
			} else {
				Script.back(response, "�� ������ �����Ͽ����ϴ�.");
			}
		}
	}
}
