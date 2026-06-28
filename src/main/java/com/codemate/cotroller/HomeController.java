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

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
