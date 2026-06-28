package com.codemate.cotroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemate.dto.MemberDTO;
import com.codemate.dto.PostDTO;
import com.codemate.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService service;
	
	@GetMapping("/list")
	public String GetList(Model model)
	{
		List<PostDTO> lists = service.selectList();
		
		model.addAttribute("lists",lists);
		
		return "post/postList";
	}
	
	@GetMapping("/detail")
	public String GetPostDetail(@RequestParam("id") int id, Model model)
	{
		PostDTO post = service.selectPostDetail(id);
		
		model.addAttribute("post",post);
		
		return "post/postDetail";
	}

	@GetMapping("/write")
	public String GetWrite(
			HttpSession session,
			RedirectAttributes rttr)
	{
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
		
		if(loginUser == null)
		{
			rttr.addFlashAttribute("msg","로그인이 필요합니다!");
			return "redirect:/member/login";
		}
		return "post/postWrite";
	}
	
	@PostMapping("/write")
	public String PostWrite(PostDTO pto,
			HttpSession session,
			RedirectAttributes rttr) 
	{
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
		
		if(loginUser == null)
		{
			rttr.addFlashAttribute("msg", "로그인이 필요합니다.");
			return "redirect:/member/login";
		}
		
		pto.setMember_id(loginUser.getId());
		
		boolean check = service.insertPost(pto);
		
		if(check)
		{
			rttr.addFlashAttribute("msg","게시글 작성 성공!");
			return "redirect:/post/list";
		}
		else
		{
			rttr.addFlashAttribute("msg","게시글 작성 실패!");
			return "redirect:/post/write";
		}
	}
	
	@GetMapping("/update")
	public String GetPostUpdate(@RequestParam("id") int post_id,Model model)
	{
		PostDTO post = service.selectPostDetail(post_id);
		
		model.addAttribute("post", post);
		
		return "post/postUpdate";
	}
	
	@PostMapping("/update")
	public String PostPostUpdate(PostDTO pto,
			RedirectAttributes rttr)
	{
		boolean check = service.updatePost(pto);
		
		if(check)
		{
			rttr.addFlashAttribute("msg","게시글 수정 완료!");
			return "redirect:/post/detail?id=" + pto.getId();
		}
		else
		{
			rttr.addFlashAttribute("msg","게시글 수정 실패!");
			return "redirect:/post/update?id=" + pto.getId();
		}
	}
	
	@GetMapping("/delete")
	public String GetPostDelete(@RequestParam("id") int post_id,
			RedirectAttributes rttr)
	{
		boolean check = service.deletePost(post_id);
		
		if(check)
		{
			rttr.addFlashAttribute("msg","게시글 삭제 완료!");
		}
		else
		{
			rttr.addFlashAttribute("msg","게시글 삭제 실패!");
		}
		
		return "redirect:/post/list";
	}
}
