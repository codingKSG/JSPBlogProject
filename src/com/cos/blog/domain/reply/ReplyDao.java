package com.cos.blog.domain.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.config.DB;
import com.cos.blog.domain.board.dto.DetailRespDto;
import com.cos.blog.domain.reply.dto.FindAllReqDto;
import com.cos.blog.domain.reply.dto.FindByIdRespDto;
import com.cos.blog.domain.reply.dto.SaveReqDto;

public class ReplyDao {
	
	public int save(SaveReqDto dto) { // 댓글쓰기
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO reply(userId, boardId, content, createDate) ");
		sb.append("VALUES(?, ?, ?, now())");
		String sql = sb.toString();

		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int generateId;
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getBoardId());
			pstmt.setString(3, dto.getContent());

			int result = pstmt.executeUpdate();
			
			rs= pstmt.getGeneratedKeys();
			if(rs.next()) {
				generateId = rs.getInt(1);
				System.out.println("생성된 키(ID) : "+generateId);
				
				if(result == 1) {
					return generateId;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return -1;
	}
	
	public List<FindAllReqDto> findAllByBoardId(int boardId) {
		// SELECT해서 Board 객체를 컬렉션에 담아서 리턴
		List<FindAllReqDto> replies = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT f.id, f.userId, f.boardId, f.content, u.username, f.createDate FROM reply f ");
		sb.append("INNER JOIN user u ON f.userId = u.id WHERE f.boardId = ? ORDER BY id DESC");
		String sql = sb.toString();
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				FindAllReqDto findAllReqDto = FindAllReqDto.builder()
						.id(rs.getInt("id"))
						.userId(rs.getInt("f.id"))
						.userId(rs.getInt("f.userId"))
						.boardId(rs.getInt("f.boardId"))
						.content(rs.getString("f.content"))
						.username(rs.getString("u.username"))
						.createDate(rs.getTimestamp("f.createDate"))
						.build();

				replies.add(findAllReqDto);
			}
			return replies;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public FindByIdRespDto findById(int id) {
		// SELECT해서 Board 객체를 컬렉션에 담아서 리턴
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT r.id, r.userId, r.boardId, r.content, u.username, r.createDate");
		sb.append(" FROM reply r INNER JOIN user u ON r.userId = u.id WHERE r.id = ?");
		String sql = sb.toString();
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				FindByIdRespDto findByIdRespDto = FindByIdRespDto.builder()
						.id(rs.getInt("r.id"))
						.userId(rs.getInt("r.userId"))
						.boardId(rs.getInt("r.boardId"))
						.content(rs.getString("r.content"))
						.username(rs.getString("u.username"))
						.createDate(rs.getTimestamp("r.createDate"))
						.build();

				return findByIdRespDto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public int deleteById(int id) {
		String sql = "DELETE FROM reply where id = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			int result = pstmt.executeUpdate();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
}
