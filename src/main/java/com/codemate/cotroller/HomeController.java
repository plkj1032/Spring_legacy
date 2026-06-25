package com.codemate.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemate.dto.memberDTO;
import com.codemate.service.memberService;

@Controller
public class HomeController {
	
	private memberService m_service;
	
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
    public String singupPost(memberDTO dto,
    		RedirectAttributes rttr)
    {
    	if(m_service.insertMember(dto)) {
    	       rttr.addFlashAttribute("msg","회원가입 성공!");
    	       return "redirect:/";
    	}

    	rttr.addFlashAttribute("msg","회원가입 실패!");
    	return "redirect:/signup";
    }
}
