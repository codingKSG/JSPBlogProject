package com.cos.blog.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.domain.dto.CommonRespDto;
import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.reply.dto.FindAllReqDto;
import com.cos.blog.domain.reply.dto.FindByIdRespDto;
import com.cos.blog.domain.reply.dto.SaveReqDto;
import com.cos.blog.service.ReplyService;
import com.cos.blog.util.Script;
import com.google.gson.Gson;

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
						
			BufferedReader br = request.getReader();
			String reqData = br.readLine();
			Gson gson = new Gson();
			SaveReqDto saveReqDto = gson.fromJson(reqData, SaveReqDto.class);
			System.out.println("saveReqDto : " + saveReqDto);
			
			int result = replyService.´ñ±Û¾²±â(saveReqDto);
			
			CommonRespDto<FindByIdRespDto> commonRespDto = new CommonRespDto<>();
			FindByIdRespDto findByIdRespDto = null;
			findByIdRespDto = replyService.´ñ±ÛÃ£±â(result);
			System.out.println(findByIdRespDto);
			
			if(result != -1) {
				findByIdRespDto = replyService.´ñ±ÛÃ£±â(result);
				commonRespDto.setStatusCode(1);
				commonRespDto.setData(findByIdRespDto);
			} else {
				commonRespDto.setStatusCode(-1);
			}
			
			String respData = gson.toJson(commonRespDto);
			System.out.println("respData : " + respData);
			Script.respData(response, respData);
		} else if(cmd.equals("delete")){
			int id = Integer.parseInt(request.getParameter("id"));
			int result = replyService.´ñ±Û»èÁ¦(id);
			
			CommonRespDto commonRespDto = new CommonRespDto<>();
			commonRespDto.setStatusCode(result); // 1, -1
			
			Gson gson = new Gson();
			String jsonData = gson.toJson(commonRespDto);
			// {"statusCode":1}
			Script.respData(response, jsonData);
			
		}
	}
}
