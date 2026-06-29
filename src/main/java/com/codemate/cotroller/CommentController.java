package com.codemate.cotroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemate.dto.CommentDTO;
import com.codemate.dto.MemberDTO;
import com.codemate.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService service;
	
	@PostMapping("/write")
	public String WriteComment(HttpSession session,
			RedirectAttributes rttr, CommentDTO cto)
	{
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
		
		if(loginUser == null)
		{
			rttr.addFlashAttribute("msg","로그인이 필요합니다!");
			return "redirect:/member/login";
		}
		
		cto.setMember_id(loginUser.getId());
		
		boolean check = service.insertComment(cto);
		
		if(check)
		{
			rttr.addFlashAttribute("msg","댓글 등록 완료!");
		}
		else
		{
			rttr.addFlashAttribute("msg","댓글 등록 실패!");
		}
		
		return "redirect:/post/detail?id=" + cto.getPost_id();
	}
}
