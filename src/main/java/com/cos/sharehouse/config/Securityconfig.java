package com.cos.sharehouse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.sharehouse.config.auth.PrincipalDetailService;


@Configuration //빈등록(Ioc관리)
@EnableWebSecurity //시큐리티 필터가 등록된다. (설정을 여기서)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 의미
public class Securityconfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private PrincipalDetailService principalDetailService;
	@Bean //IoC가 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("로그인 패스워드 인코더 호출");
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
		//패스워드 인코더가 encodePWD라는 것을 null자리의 오브젝트에게 알려줘야한다.
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf토큰 비활성화(테스트시 걸어두는게 좋음)
			.authorizeRequests()
			.antMatchers("/main","/","/auth/**","/js/**","/css/**","/api/**","/img/**","/oauth/**","/profile/**","/product/**","/image/**","/search/**")//권한 설정
			.permitAll()
			.anyRequest()//이게 아닌 다른 모든 요청은 
			.authenticated()//인증이 필요
		.and()
			.formLogin()
			.loginPage("/auth/loginForm")
			.loginProcessingUrl("/auth/loginProc")
			.defaultSuccessUrl("/")
//			.failureUrl("/auth/fail")
		.and()
        	.logout()
        	.logoutSuccessUrl("/auth/loginForm") // 로그아웃 성공시 리다이렉트 주소
        	.invalidateHttpSession(true); // 로그아웃 이후 세션 전체 삭제 여부
	}
}
