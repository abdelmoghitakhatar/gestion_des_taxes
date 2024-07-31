package com.app.taxes.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/entreprises";
    }

    @GetMapping("/403")
    public String Unauthorized(){
        return "403";
    }
}
