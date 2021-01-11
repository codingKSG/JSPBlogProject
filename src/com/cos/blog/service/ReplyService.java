package com.cos.blog.service;

import java.util.List;

import com.cos.blog.domain.reply.ReplyDao;
import com.cos.blog.domain.reply.dto.FindAllReqDto;
import com.cos.blog.domain.reply.dto.SaveReqDto;

public class ReplyService {
	private ReplyDao replyDao;
	
	public ReplyService() {
		replyDao = new ReplyDao();
	}
	
	public int ´ñ±Û¾²±â(SaveReqDto saveReqDto) {		
		int result = replyDao.save(saveReqDto);
		
		return result;
	}
	
	public List<FindAllReqDto> ´ñ±Û(int boardId){
		
		return replyDao.findAllByBoardId(boardId);
	}
}
