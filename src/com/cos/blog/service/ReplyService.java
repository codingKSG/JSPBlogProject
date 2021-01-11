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
	
	public int ¥Ò±€æ≤±‚(SaveReqDto saveReqDto) {		
		int result = replyDao.save(saveReqDto);
		
		return result;
	}
	
	public List<FindAllReqDto> ¥Ò±€(int boardId){
		
		return replyDao.findAllByBoardId(boardId);
	}
	
	public FindByIdRespDto ¥Ò±€√£±‚(int id) {
		
		return replyDao.findById(id);
	}
}
