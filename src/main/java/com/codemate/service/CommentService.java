package com.codemate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codemate.dao.CommentDAO;
import com.codemate.dto.CommentDTO;

@Service
public class CommentService {
	
	@Autowired
	private CommentDAO cao;
	
	public boolean insertComment(CommentDTO cto)
	{
		return cao.insertComment(cto);
	}
}
