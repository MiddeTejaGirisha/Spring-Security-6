package com.example.Security1;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/")
    public String home() {
        return "redirect:/default";
    }

    @GetMapping("/default")
    public String defaultAfterLogin(Authentication authentication) {
        System.out.println("entered");
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin";
        }
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String userEndpoint(Model model) {
        model.addAttribute("message", "User method accessed");
        return "user";
    }

    @GetMapping("/admin")
    public String adminEndpoint(Model model) {
        model.addAttribute("message", "Admin method accessed");
        return "admin";
    }
}