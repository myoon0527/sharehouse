package com.cos.sharehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class houseController {
	
	@GetMapping("/auth/bcah")
	public String bcah() {
		return "/house/bcah";
	}
}
