package com.codemate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codemate.dao.PostDAO;
import com.codemate.dto.PostDTO;

@Service
public class PostService {
	
	@Autowired
	private PostDAO pao;
	
	public boolean insertPost(PostDTO pto)
	{
		return pao.insertPost(pto);
	}
	
	public List<PostDTO> selectList()
	{
		return pao.selectPosts();
	}
	
	public PostDTO selectPostDetail(int post_id)
	{
		return pao.selectDetailPost(post_id);
	}
	
	public boolean updatePost(PostDTO pto)
	{
		return pao.updatePost(pto);
	}
	
	public boolean deletePost(int post_id)
	{
		return pao.deletePost(post_id);
	}
}
