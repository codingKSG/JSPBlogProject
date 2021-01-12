package com.cos.blog.domain.board.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailRespDto {
	private int id;
	private int userId;
	private String title;
	private String content;
	private int readCount; // 조회수 디폴트 값 0
	private Timestamp createDate;
	private String username;
	
	public String getTitle() {
		return title.replaceAll("<", "&lt").replaceAll("<", "&gt");
	}
}
