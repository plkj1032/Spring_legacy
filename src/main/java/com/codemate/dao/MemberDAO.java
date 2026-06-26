package com.codemate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.codemate.dto.MemberDTO;
import com.codemate.util.DBConnection;
import com.codemate.util.PasswordUtil;

@Repository
public class MemberDAO {
	public boolean insertMember(MemberDTO dto)
	{
		String sql = "INSERT INTO members(name,age,email,password) VALUES(?,?,?,?)";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setString(1, dto.getName());
			ps.setInt(2, dto.getAge());
			ps.setString(3, dto.getEmail());
			String hashPw = PasswordUtil.encrypt(dto.getPassword());
			ps.setString(4, hashPw);
			
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
	
	public MemberDTO selsectLogin(String email, String password)
	{
		String sql = "SELECT * FROM members WHERE email = ? AND password = ?";
		
		MemberDTO loginUser = null;
		
		try (
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setString(1, email);
			String hashPw = PasswordUtil.encrypt(password);
			ps.setString(2, hashPw);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				loginUser = new MemberDTO();
				
				loginUser.setId(rs.getInt("id"));
				loginUser.setName(rs.getString("name"));
				loginUser.setAge(rs.getInt("age"));
				loginUser.setEmail(rs.getString("email"));
				
				return loginUser;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return loginUser;
	}
}
