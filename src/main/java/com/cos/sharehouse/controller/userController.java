package com.cos.sharehouse.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.sharehouse.service.OAuthService;

@Controller
public class userController {
	@Autowired
	private OAuthService OAuthService;
	@GetMapping("/auth/signup")
	public String signup() {
		return "user/signup";
	}
	@GetMapping("/")
	public String home() {
		return "index";
	}
	@GetMapping("/auth/loginForm")
	public String signin() {
		return "user/loginForm";
	}
	@RequestMapping(value="/oauth/kakao")
	public String kakao(@RequestParam("code") String code,HttpSession session) {
		String access_Token = OAuthService.getKakaoAccessToken(code);
		  HashMap<String, Object> userInfo = OAuthService.getUserInfo(access_Token);
		  if(userInfo.get("email")!=null) {
			  session.setAttribute("userId", userInfo.get("email"));
		      session.setAttribute("access_Token", access_Token);
		  }
		  session.setAttribute("social", "kakao");
		return "/user/socialsignup";
	}
	
	@RequestMapping(value="/oauth/logout")
	public String logout(HttpSession session) {
		OAuthService.kakaoLogout((String)session.getAttribute("access_Token"));
	    session.removeAttribute("access_Token");
	    session.removeAttribute("userId");
	    return "index";
	}

}
