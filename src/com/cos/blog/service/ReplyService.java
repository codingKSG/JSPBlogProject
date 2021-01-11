package com.cos.blog.service;

import java.util.List;

import com.cos.blog.domain.reply.ReplyDao;
import com.cos.blog.domain.reply.dto.FindAllReqDto;
import com.cos.blog.domain.reply.dto.FindByIdRespDto;
import com.cos.blog.domain.reply.dto.SaveReqDto;

public class ReplyService {
	private ReplyDao replyDao;
	
	public ReplyService() {
		replyDao = new ReplyDao();
	}
	
	public int ��۾���(SaveReqDto saveReqDto) {		
		int result = replyDao.save(saveReqDto);
		
		return result;
	}
	
	public List<FindAllReqDto> ���(int boardId){
		
		return replyDao.findAllByBoardId(boardId);
	}
	
	public FindByIdRespDto ���ã��(int id) {
		
		return replyDao.findById(id);
	}
	
	public int ��ۻ���(int id) {
		return replyDao.deleteById(id);
	}
}
