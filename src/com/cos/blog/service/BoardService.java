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

	public int 글쓰기(SaveReqDto dto) {
		return boardDao.save(dto);
	}

	public List<Board> 글목록보기(int page) {

		return boardDao.findAll(page);
	}

	public DetailRespDto 상세보기(int id) {

		int result = boardDao.updateReadCount(id);

		if (result == 1) {
			return boardDao.findById(id);
		} else {
			return null;
		}
	}

	public int 글개수() {

		return boardDao.count();
	}

	public int 글개수(String keyword) {

		return boardDao.count(keyword);
	}

	public int 게시글삭제(int id) {

		return boardDao.deleteById(id);
	}

	public List<Board> 글검색(String keyword, int page) {
		return boardDao.findByKeyword(keyword, page);
	}
}
