package com.codemate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.codemate.dto.MemberDTO;
import com.codemate.util.DBConnection;
import com.codemate.util.PasswordUtil;

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
}
