package com.cos.sharehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.sharehouse.model.CurrentType;
import com.cos.sharehouse.model.RoleType;
import com.cos.sharehouse.model.Users;
import com.cos.sharehouse.repository.userRepository;

@Service
public class userService {
	@Autowired
	private userRepository userrepository;
	@Autowired
	private BCryptPasswordEncoder encodeer;
	
	public void 회원가입(Users user) {
		user.setRoles(RoleType.guest);
		user.setType(CurrentType.defaultType);
		String rawPassword = user.getPassword();
		String encPassword = encodeer.encode(rawPassword);
		user.setPassword(encPassword);
		userrepository.save(user);
	}
}
