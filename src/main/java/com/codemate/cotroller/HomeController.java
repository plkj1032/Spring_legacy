package com.codemate.cotroller;

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
}
