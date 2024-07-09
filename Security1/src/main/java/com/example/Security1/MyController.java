package com.example.Security1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminEndpoint() {
        myService.adminMethod();
        return "Access granted to admin endpoint";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userEndpoint() {
        myService.userMethod();
        return "Access granted to user endpoint";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Access granted to public endpoint";
    }
}
