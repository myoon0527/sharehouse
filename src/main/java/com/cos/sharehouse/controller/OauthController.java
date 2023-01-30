package com.cos.sharehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/oauth")
public class OauthController {
//	 @ResponseBody
//	    @GetMapping("/kakao")
//	    public void kakaoCallback(@RequestParam String code) {
//	        System.out.println(code);
//	    }
}
