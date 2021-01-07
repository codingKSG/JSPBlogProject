package com.cos.blog.web;

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
import com.cos.blog.domain.board.dto.SaveReqDto;
import com.cos.blog.domain.user.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.util.Script;

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
		HttpSession session = request.getSession();
		// http://localhost:8000/blog/board?cmd=saveForm
		
		if(cmd.equals("saveForm")) {
			User principal = (User)session.getAttribute("principal");
			if(principal != null) {
				RequestDispatcher dis = request.getRequestDispatcher("board/saveForm.jsp");
				dis.forward(request, response);
			} else {
				RequestDispatcher dis = request.getRequestDispatcher("user/loginForm.jsp");
				dis.forward(request, response);
			}
		} else if(cmd.equals("save")) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			SaveReqDto dto = SaveReqDto.builder()
					.userId(userId)
					.title(title)
					.content(content)
					.build();
			int result = boardService.�۾���(dto);
			if(result == 1) { // ����
				response.sendRedirect("index.jsp");
			} else {
				Script.back(response, "�۾��� ����");
			}
		} else if(cmd.equals("list")) {
			// request�� ��� RequestDispatcher���� �̵�
			int page = Integer.parseInt(request.getParameter("page"));
			List<Board> boards = boardService.�۸�Ϻ���(page);
			int endPage = boards.size() / 4;
			boolean first = false;
			boolean end = false;
			if(page == 0) {
				first = true;
			} else if(page == endPage) {
				end = true;
			}
			request.setAttribute("boards", boards);
			request.setAttribute("page", page);
			request.setAttribute("first", first);
			request.setAttribute("end", end);
			
			RequestDispatcher dis = request.getRequestDispatcher("board/list.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("detail")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			System.out.println(id);
			Board board = boardService.�󼼺���(id);
			
			request.setAttribute("board", board);
			
			RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
			dis.forward(request, response);
		}
	}
}
