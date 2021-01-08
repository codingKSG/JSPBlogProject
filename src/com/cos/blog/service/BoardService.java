package com.cos.blog.service;

import java.util.List;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.BoardDao;
import com.cos.blog.domain.board.dto.DetailRespDto;
import com.cos.blog.domain.board.dto.SaveReqDto;

public class BoardService {

	private BoardDao boardDao;

	public BoardService() {
		boardDao = new BoardDao();
	}

	public int �۾���(SaveReqDto dto) {
		return boardDao.save(dto);
	}
	
	public List<Board> �۸�Ϻ���(int page){
		
		return boardDao.findAll(page);
	}
	
	public DetailRespDto �󼼺���(int id){
		
		return boardDao.findById(id);
	}
	
	public void ��ȸ��(int id) {
		
		boardDao.updateReadCount(id);
	}
	
	public int �۰���() {
		
		return boardDao.count();
	}
}
