package com.cos.blog.domain.reply.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindAllReqDto {
	private int id;
	private int boardId;
	private int userId;
	private String content;
	private String username;
	private Timestamp createDate;
}
