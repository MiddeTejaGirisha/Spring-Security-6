package com.example.Security1;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//public class MyController {
//
//    @Autowired
//    private MyService myService;
//
//    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String adminEndpoint() {
//        myService.adminMethod();
//        return "Access granted to admin endpoint";
//    }
//
//    @GetMapping("/user")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public String userEndpoint() {
//        myService.userMethod();
//        return "Access granted to user endpoint";
//    }
//
//    @GetMapping("/public")
//    public String publicEndpoint() {
//        return "Access granted to public endpoint";
//    }
//}
@Controller
public class MyController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @Secured("ROLE_USER")
    @GetMapping("/user")
    public String userEndpoint(Model model) {
        model.addAttribute("message", "User method accessed");
        return "user";
    }

    @GetMapping("/default")
    public String defaultAfterLogin(Authentication authentication) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin";
        }
        return "redirect:/user";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminEndpoint(Model model) {
        model.addAttribute("message", "Admin method accessed");
        return "admin";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/user-or-admin")
    public String userOrAdminEndpoint(Model model) {
        model.addAttribute("message", "User or Admin method accessed");
        return "user-or-admin";
    }
}