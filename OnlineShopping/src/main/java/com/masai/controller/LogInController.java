package com.masai.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.masai.models.Cart;
import com.masai.models.User;
import com.masai.repository.CartRepo;
import com.masai.repository.UserRepo;
import com.masai.service.UserService;

@Controller
public class LogInController {
	@Autowired UserService userService;
	@Autowired BCryptPasswordEncoder passwordEncoder;
	@Autowired UserRepo userRepo;
	@Autowired CartRepo cartRepo;
	
	@GetMapping("/")
	public String commonHome(Model model, Principal principal) {
		if(principal != null) {
			String email = principal.getName();
			User user = userRepo.findByEmail(email);
			System.out.println(user.getRole());
			if(user.getRole().equals("ROLE_ADMIN")) {
				return "redirect:/admin/home";
			}
			else return "redirect:/user/shop";
		}
		else return "home";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("user") User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Cart cart = new Cart();
		if(user.getRole().equals("ROLE_USER")) {
			cart.setUser(user);
			cartRepo.save(cart);
		}
		userService.saveUser(user);
		return "redirect:/login?success";
	}
	
	@GetMapping("/login")
	public String register() {
		return "login";
	}
	
	
}
