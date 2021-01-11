package com.cos.blog.domain.board.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateReqDto {
	private int id;
	private String title;
	private String content;
}
