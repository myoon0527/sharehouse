package com.cos.sharehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.sharehouse.service.houseService;

@Controller
public class houseController {
	
	@Autowired
	private houseService houseService;
	
	//숙소 등록 페이지
	@GetMapping("/auth/bcah")
	public String bcah() {
		return "/house/bcah";
	}
	
	//숙소 자세히 보기
	@GetMapping("/auth/detail/{id}")
	public String detail(@PathVariable int id, Model model) {
		model.addAttribute("house", houseService.detail(id));
		return "/house/detail";
	}
	
	//숙소 자세히 보기
	@GetMapping("/detail/{id}")
	public String review(@PathVariable int id, Model model) {
		model.addAttribute("house", houseService.detail(id));
		return "/house/detail";
	}
	
}
