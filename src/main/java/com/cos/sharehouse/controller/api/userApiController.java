package com.cos.sharehouse.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.sharehouse.dto.ResponseDto;
import com.cos.sharehouse.model.Users;
import com.cos.sharehouse.service.userService;



@RestController
public class userApiController {
	@Autowired
	private userService userservice;
	@PostMapping("/api/signup")
	public ResponseDto<Integer> signup(@RequestBody Users user){
		userservice.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
