package com.codemate.service;

import java.util.List;

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
	
	public List<CommentDTO> selectComments(int post_id)
	{
		return cao.selectComments(post_id);
	}
	
	public boolean updateComment(CommentDTO cto)
	{
		return cao.updateComment(cto);
	}
	
	public boolean deleteComment(CommentDTO cto)
	{
		return cao.deleteComment(cto);
	}
}
