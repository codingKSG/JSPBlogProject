package com.cos.blog.domain.board;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int id;
	private int userId;
	private String title;
	private String content;
	private int readCount; // 조회수 디폴트 값 0
	private Timestamp createDate;
	
	// 루시필터 적용해보기!!
	public String getTitle() {
		return title.replaceAll("<", "&lt").replaceAll("<", "&gt");
	}
}
