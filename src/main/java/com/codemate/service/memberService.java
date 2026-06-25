package com.codemate.service;

import com.codemate.dto.memberDTO;
import com.codemate.dao.memberDAO;

public class memberService {
	
	private memberDAO dao = new memberDAO();
	
	public boolean insertMember(memberDTO dto)
	{
		return dao.insertMember(dto);
	}
}
