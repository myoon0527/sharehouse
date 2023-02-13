package com.cos.sharehouse.controller.api;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.cos.sharehouse.config.auth.PrincipalDetail;
import com.cos.sharehouse.dto.ResponseDto;
import com.cos.sharehouse.model.Users;
import com.cos.sharehouse.service.MailSendService;
import com.cos.sharehouse.service.OAuthService;
import com.cos.sharehouse.service.userService;



@RestController
public class userApiController {
	@Autowired
	private OAuthService OAuthService;
	@Autowired
	private userService userservice;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MailSendService MailSendService;
	@PostMapping("/api/signup")
	public ResponseDto<Integer> signup(@RequestBody Users user){
		userservice.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	public String kakaologinproc(String username,String password){
		
	
	 MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    
	    params.add("username", username.toString());
	    params.add("password", password.toString());
	    
	    HttpHeaders headers = new HttpHeaders();
	    
	    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
	    System.out.println(entity.toString());
	    RestTemplate rt = new RestTemplate();

	    ResponseEntity<String> response1 = rt.exchange(
             "http://localhost:8006/auth/loginProc", //{요청할 서버 주소}
             HttpMethod.POST, //{요청할 방식}
             entity, // {요청할 때 보낼 데이터}
             String.class //{요청시 반환되는 데이터 타입}
	    		);
	    System.out.println(response1);
	    System.out.println(response1.getHeaders());
	    System.out.println(response1.getHeaders().get("Set-Cookie"));
//	    System.out.println(response1.getHeaders().get("Set-Cookie").toString().indexOf("=")); //쿠키값 시작
//	    System.out.println(response1.getHeaders().get("Set-Cookie").toString().indexOf(";")); //쿠키값 마지막
	    //response1.getHeaders().get("Set-Cookie").toString().substring(12, 44);
	    return  response1.getHeaders().get("Set-Cookie").toString().substring(12, 44);
	}
	@PutMapping("/api/update/{id}")
	public ResponseDto<Integer> userupdate(@PathVariable int id,@RequestBody Users user){
		userservice.회원정보변경(id, user);
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword() ));
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
//		Authentication authentication = new UsernamePasswordAuthenticationToken(
//				principal, null, principal.getAuthorities());
//		 	SecurityContext securityContext = SecurityContextHolder.getContext();
//	 		securityContext.setAuthentication(authentication);
// 			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@DeleteMapping("/api/out/{id}")
	public ResponseDto<Integer> userupdate(@PathVariable int id){
		userservice.회원탈퇴(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@PostMapping("/api/check/{id}")
	public ResponseDto<Integer> useridcheck(@PathVariable String id){
		int check =userservice.test(id).getId();
		return new ResponseDto<Integer>(HttpStatus.OK.value(),check);
	}
	@PostMapping("/api/imgchange/{id}")
	public ResponseDto<Integer> imgchange(@PathVariable int id,@RequestPart("profileimage") MultipartFile profileimage) throws IOException{
		userservice.이미지변경(id,profileimage);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@PostMapping("/api/finduseremail")
	public ResponseDto<Users> finduseremail(@RequestParam(value="usernick")String usernick,@RequestParam(value="email")String email){
		Users user =userservice.finduseremail(usernick,email);
		
		return new ResponseDto<Users>(HttpStatus.OK.value(),user);
	}
	@GetMapping("/auth/mailCheck")
	public String usermailcheck(@RequestParam(value="email")String email) {
		
		System.out.println("eamil : " + email);
		return MailSendService.joinEmail(email);
	}
	
}
