package com.cos.sharehouse.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.sharehouse.model.Users;
import com.cos.sharehouse.repository.userRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{
	@Autowired
	private userRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당사용자를 찾을 수 없습니다."+username);
				});
		System.out.println("로그인 유저 : "+principal.getUsername());
		System.out.println("로그인 유저 principal : "+new PrincipalDetail(principal).getUsername());
		return new PrincipalDetail(principal);
	}

}
