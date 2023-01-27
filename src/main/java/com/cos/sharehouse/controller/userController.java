package com.cos.sharehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class userController {

	@GetMapping("/auth/signup")
	public String signup() {
		return "user/signup";
	}
	@GetMapping("/")
	public String home() {
		return "index";
	}
	@GetMapping("/auth/signin")
	public String signin() {
		return "user/signin";
	}
}
