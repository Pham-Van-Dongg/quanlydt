package com.example.demo.controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.CustomUserDetails;
import com.example.demo.entity.User.User;
import com.example.demo.repository.User.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class UserController {

	@Autowired
	private UserRepository repo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/index")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user, HttpSession session, Model model) {		
		if (repo.findByEmail(user.getEmail()) != null) {
			model.addAttribute("errorMessage", "Email đã được sử dụng. Vui lòng sử dụng email khác.");
			return "signup_form";
		}
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
	    return "login";
	}
	
	@GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Trả về file login.html hoặc login.jsp
    }
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "Logged out successfully!";
	}
	
	@GetMapping("/")
	public String index(@AuthenticationPrincipal CustomUserDetails userDetails, Model model, HttpSession session) {
	    if (userDetails != null) {
	        session.setAttribute("fullName", userDetails.getFullName());
	    }
	    return "index";
	}
}
