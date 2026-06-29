package com.codemate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.codemate.dto.CommentDTO;
import com.codemate.util.DBConnection;

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
}
