package com.codemate.service;

import com.codemate.dto.MemberDTO;

import org.springframework.stereotype.Service;

import com.codemate.dao.MemberDAO;

@Service
public class MemberService {
	
	private MemberDAO dao = new MemberDAO();
	
	public boolean insertMember(MemberDTO dto)
	{
		return dao.insertMember(dto);
	}
}
