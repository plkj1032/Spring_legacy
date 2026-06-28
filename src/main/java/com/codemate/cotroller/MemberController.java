package com.codemate.cotroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemate.dto.MemberDTO;
import com.codemate.service.MemberService;


@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/signup")
	public String GetSignup()
	{
		return "member/signup";
	}
	
	@PostMapping("/signup")
	public String PostSignup(MemberDTO dto,
			RedirectAttributes rttr)
	{
		boolean check = service.insertMember(dto);
		
		if(check)
		{
			rttr.addFlashAttribute("msg","회원가입 성공!");
			return "redirect:/";
		}
		else
		{
			rttr.addFlashAttribute("msg","회원가입 실패!");
			return "redirect:/signup";
		}
	}
	
	@GetMapping("/login")
	public String Getlogin()
	{
		return "member/login";
	}
	
	@PostMapping("/login")
	public String Postlogin(MemberDTO dto,
			HttpSession session,
			RedirectAttributes rttr)
	{
		MemberDTO loginUser = service.selectLoginUser(dto.getEmail(), dto.getPassword());
		
		if(loginUser != null)
		{
			session.setAttribute("loginUser", loginUser);
			
			return "redirect:/";
		}
		else
		{
			rttr.addFlashAttribute("msg","이메일 또는 비밀번호가 틀렸습니다!");
			return "redirect:/login";
		}
	}
	
	@GetMapping("/logout")
	public String GetLogout(HttpSession session)
	{
		if(session != null)
		{
			session.invalidate();
		}
		return "redirect:/";
	}
	
}
