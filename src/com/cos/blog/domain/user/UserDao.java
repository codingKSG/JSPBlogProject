package com.cos.blog.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.blog.config.DB;
import com.cos.blog.domain.user.dto.JoinReqDto;
import com.cos.blog.domain.user.dto.LoginReqDto;

public class UserDao {

	public User findByUsernameAndPassword(LoginReqDto dto) { // 회원가입		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT id, username, email, address FROM user ");
		sb.append("WHERE username = ? And password = ?");
		String sql = sb.toString();

		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				User user = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.address(rs.getString("address"))
						.build();
				return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return null; // DB에 username 존재x
	}
	
	public int findByUsername(String username) {		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM user ");
		sb.append("WHERE username = ?");
		String sql = sb.toString();

		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return 1; // DB에 username 존재o
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return -1; // DB에 username 존재x
	}
	
	public int save(JoinReqDto dto) { // 회원가입		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO user(username, password, email, address, userRole, createDate) ");
		sb.append("VALUES(?, ?, ?, ?, 'USER', now())");
		String sql = sb.toString();

		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getAddress());
			
			int result = pstmt.executeUpdate();
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}

	public void update() { // 정보수정

	}

	public void usernameCheck() { // 아이디 중복 체크

	}

	public void findById() { // 회원정보보기

	}
}
