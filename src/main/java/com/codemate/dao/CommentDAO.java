package com.codemate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.codemate.dto.CommentDTO;
import com.codemate.util.DBConnection;

@Repository
public class CommentDAO {
	public boolean insertComment(CommentDTO cto)
	{
		String sql = "INSERT INTO "
				+ "comments(post_id,member_id,content,created_at) VALUES(?,?,?,NOW())";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			
			ps.setInt(1, cto.getPost_id());
			ps.setInt(2, cto.getMember_id());
			ps.setString(3, cto.getContent());
			
			int result = ps.executeUpdate();
			
			if(result > 0)
			{
				return true;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public List<CommentDTO> selectComments(int post_id)
	{
		String sql = "SELECT c.id, m.name AS comment_write, c.content, c.created_at "
				+ "FROM comments c "
				+ "JOIN members m ON c.member_id = m.id "
				+ "WHERE post_id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setInt(1, post_id);
			
			ResultSet rs = ps.executeQuery();
			
			List<CommentDTO> lists = new ArrayList<>();
			
			while(rs.next())
			{
				CommentDTO cto = new CommentDTO();
				
				cto.setId(rs.getInt("id"));
				cto.setComment_writer(rs.getString("comment_write"));
				cto.setContent(rs.getString("content"));
				cto.setCreated_at(rs.getString("created_at"));
				
				lists.add(cto);
			}
			return lists;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateComment(CommentDTO cto)
	{
		String sql = "UPDATE comments SET content = ? WHERE id = ? AND member_id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setString(1, cto.getContent());
			ps.setInt(2, cto.getId());
			ps.setInt(3, cto.getMember_id());
			
			int result = ps.executeUpdate();
			
			if(result > 0)
			{
				return true;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteComment(CommentDTO cto)
	{
		String sql = "DELETE FROM comments WHERE id = ? AND member_id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setInt(1, cto.getId());
			ps.setInt(2, cto.getMember_id());
			
			int result = ps.executeUpdate();
			
			if(result > 0)
			{
				return true;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
