package com.codemate.service;

import com.codemate.dto.MemberDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codemate.dao.MemberDAO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao = new MemberDAO();
	
	public boolean insertMember(MemberDTO dto)
	{
		return dao.insertMember(dto);
	}
	
	public MemberDTO selectLoginUser(String email, String password)
	{
		return dao.selsectLogin(email,password);
	}
}
