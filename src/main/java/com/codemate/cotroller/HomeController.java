package com.codemate.cotroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemate.dto.MemberDTO;
import com.codemate.service.MemberService;

@Controller
public class HomeController {
	
	@Autowired
	private MemberService memberService;
	
    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/signup")
    public String signup()
    {
    	return "signup";
    }
    
    @PostMapping("/signup")
    public String singupPost(MemberDTO dto,
    		RedirectAttributes rttr)
    {
    	boolean check = memberService.insertMember(dto);
    	
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
    public String loginGet()
    {
    	return "login";
    }
    
    @PostMapping("/login")
    public String loginPost(MemberDTO dto,
    		HttpSession session,
    		RedirectAttributes rttr)
    {    	
    	MemberDTO loginUser = memberService.selectLoginUser(dto.getEmail(),dto.getPassword());
    	
    	if(loginUser != null)
    	{
    		session.setAttribute("loginUser",loginUser);
    		
    		return "redirect:/";
    	}
    	
    	rttr.addFlashAttribute("msg","이메일 또는 비밀번호가 틀렸습니다.");
    	
    	return "redirect:/index";
    }
    
    @GetMapping("/logout")
    public String logoutPost(HttpSession session)
    {    	
    	if(session != null)
    	{
    		session.invalidate();
    	}
    	
    	return "redirect:/";
    }
}
