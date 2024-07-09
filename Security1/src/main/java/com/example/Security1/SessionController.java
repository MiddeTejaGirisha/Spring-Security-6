package com.example.Security1;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {
	
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@GetMapping("/user")
	public String getUser() {
		return "user";
	}
	
	@GetMapping("/manager")
	public String getManager() {
		return "manager";
	}
	
	 @GetMapping("/authenticated")
	    public String secured(Model model,HttpServletRequest request) {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        HttpSession  session=request.getSession();
	        String message = "Hello, " + authentication.getName() + "by"+session;
	        model.addAttribute("message", message);
	        return "authenticated";
	    }
	 
	
	 
	 

}

