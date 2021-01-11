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

	public List<Board> �۸�Ϻ���(int page) {

		return boardDao.findAll(page);
	}

	public DetailRespDto �󼼺���(int id) {

		int result = boardDao.updateReadCount(id);

		if (result == 1) {
			return boardDao.findById(id);
		} else {
			return null;
		}
	}

	public int �۰���() {

		return boardDao.count();
	}

	public int �۰���(String keyword) {

		return boardDao.count(keyword);
	}

	public int �Խñۻ���(int id) {

		return boardDao.deleteById(id);
	}

	public List<Board> �۰˻�(String keyword, int page) {
		return boardDao.findByKeyword(keyword, page);
	}
}
