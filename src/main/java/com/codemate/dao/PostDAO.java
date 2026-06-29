package com.codemate.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.codemate.dto.PostDTO;
import com.codemate.util.DBConnection;

@Repository
public class PostDAO {
	
	public boolean insertPost(PostDTO pto)
	{
		String sql = "INSERT INTO posts(member_id,title,content,created_at) VALUES(?,?,?,NOW())";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			
			ps.setInt(1, pto.getMember_id());
			ps.setString(2, pto.getTitle());
			ps.setString(3, pto.getContent());
			
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
	
	
	public List<PostDTO> selectPosts(int size,int offset)
	{		
		String sql = "SELECT p.id, p.title, p.view_count, "
				+ "m.name AS post_writer, p.created_at "
				+ "FROM posts p "
				+ "JOIN members m ON p.member_id = m.id "
				+ "ORDER BY p.id DESC "
				+ "LIMIT ?,?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				
				){
			
			ps.setInt(1, offset);
			ps.setInt(2, size);
			
			ResultSet rs = ps.executeQuery();
			
			List<PostDTO> list = new ArrayList<>();
			
			while(rs.next())
			{
				PostDTO pto = new PostDTO();
				
				pto.setId(rs.getInt("id"));
				pto.setTitle(rs.getString("title"));
				pto.setPost_writer(rs.getString("post_writer"));
				pto.setView_count(rs.getInt("view_count"));
				pto.setCreated_at(rs.getString("created_at"));
				
				list.add(pto);
			}
			return list;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public PostDTO selectDetailPost(int post_id)
	{

		String sql = "SELECT p.id,p.member_id,p.title,p.content,p.view_count,m.name AS post_writer,p.created_at "
				+ "FROM posts p "
				+ "JOIN members m ON p.member_id = m.id "
				+ "WHERE p.id = ? ";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setInt(1, post_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				PostDTO post = new PostDTO();
				
				post.setId(rs.getInt("id"));
				post.setMember_id(rs.getInt("member_id"));
				post.setTitle(rs.getString("title"));
				post.setView_count(rs.getInt("view_count"));
				post.setContent(rs.getString("content"));
				post.setPost_writer(rs.getString("post_writer"));
				post.setCreated_at(rs.getString("created_at"));
				
				return post;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean updatePost(PostDTO pto)
	{
		String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setString(1, pto.getTitle());
			ps.setString(2, pto.getContent());
			ps.setInt(3, pto.getId());
			
			int result = ps.executeUpdate();
			
			if( result > 0 )
			{
				return true;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deletePost(int post_id)
	{
		String sql = "DELETE FROM posts WHERE id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setInt(1, post_id);
			
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
	
	public int selectAllCount()
	{
		String sql = "SELECT COUNT(*) AS posts_all FROM posts";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				
			ResultSet rs = ps.executeQuery();
				){
			
			if(rs.next())
			{
				return rs.getInt("posts_all");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
}
