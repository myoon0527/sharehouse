package com.cos.sharehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.sharehouse.service.productService;

@Controller
public class productController {
	@Autowired
	private productService productService;
	@GetMapping("/product/main")
	public String productmain() {
		return "product/searchProduct";
	}
	@GetMapping("/search")
	public String searchproduct(
					@RequestParam(value="type")String[] type,
					@RequestParam(value="category")String[] category,
					@RequestParam(value="bedroom")int[] bedroom,
					@RequestParam(value="amenities")String amenities,
					Pageable page,
					Model model) {
		//대충 모델에 제품검색에서 나온 결과들을house 에 넣어서 뿌려주기
		
		productService.제품검색(type, category, bedroom, amenities, page);
//		model.addAttribute("houses",productService.제품검색(type, category, bedroom, amenities, page));
		
		
		
		return "product/searchProduct";
	}
	
}
