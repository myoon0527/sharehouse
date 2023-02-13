package com.cos.sharehouse.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cos.sharehouse.config.auth.PrincipalDetail;
import com.cos.sharehouse.controller.api.userApiController;
import com.cos.sharehouse.service.MailSendService;
import com.cos.sharehouse.service.MessageService;
import com.cos.sharehouse.service.OAuthService;
import com.cos.sharehouse.service.userService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
@Controller
public class userController {
	@Autowired
	private OAuthService OAuthService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private userApiController userApiController;
	@Autowired
	private userService userService;
	@Autowired
	private MessageService MessageService;
	@Autowired
	private MailSendService MailSendService;
	@GetMapping("/auth/signup")
	public String signup() {
		return "user/signup";
	}
	@GetMapping({"/","index"})
	public String home() {
		return "index";
	}
	@GetMapping("/auth/loginForm")
	public String signin() {
		return "user/loginForm";
	}
	@GetMapping("/user/mypage")
	public String mypage() {
	
		return "user/mypage";
	}
	@GetMapping("/user/updateprofileimg")
	public String updateprofileimg() {
		return "user/profileimg";
	}
	@GetMapping("/user/msntest")
	public String msntest() {
		return "user/msn";
	}
	@GetMapping("/user/message")//수신메시지로 가는 주소
	public String message(@AuthenticationPrincipal PrincipalDetail principal,Pageable pageable,Model model) {
		model.addAttribute("messages",MessageService.수신메시지보기(principal.getUser().getId(), pageable));
		return "user/message";
	}
	@GetMapping("/user/sendmessage")//발신메시지로 가는 주소
	public String sendmessage(@AuthenticationPrincipal PrincipalDetail principal,Pageable pageable,Model model) {
		model.addAttribute("messages",MessageService.발신메시지보기(principal.getUser().getId(), pageable));
		return "user/sendmessage";
	}
	@GetMapping("/user/messagedetail/{id}")
	public String messagedetail(@PathVariable int id,Model model) {
		model.addAttribute("message",MessageService.수신메시지상세보기(id));
		return "user/messagedetail";
	}
	@GetMapping("/user/sendmessagedetail/{id}")
	public String sendmessagedetail(@PathVariable int id,Model model) {
		model.addAttribute("message",MessageService.발신메시지상세보기(id));
		return "user/sendmessagedetail";
	}
	@GetMapping("/user/update")
	public String userupdate() {
		return "user/userupdate";
	}
	@GetMapping("/user/out")
	public String userout() {
		return "user/userout";
	}
	
	
	@RequestMapping(value="/oauth/kakao")
	public String kakao(@RequestParam("code") String code,HttpSession session,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirect) throws Exception,ServletException, IOException {
		String access_Token = OAuthService.getKakaoAccessToken(code);
		  HashMap<String, Object> userInfo = OAuthService.getUserInfo(access_Token);
		  if(userInfo.get("email")!=null) {
//			  session.setAttribute("userId", userInfo.get("email"));
//		      session.setAttribute("access_Token", access_Token);	
		      OAuthService.kakao가입(userInfo);
		  }else {
			  OAuthService.kakao가입(userInfo);
		  }
		  String username = userInfo.get("kakaoid").toString();
		  String password ="123" ;
		  Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,"123"));
		  SecurityContextHolder.getContext().setAuthentication(authentication);
		  
//		  OAuthService.kakaologin(userInfo.get("nickname").toString(),"123",request,response);
//		  Map<String, String> map = new HashMap<String, String>();
//		    map.put("username", username);
//		    map.put("password", password);
//		    OAuthService.kakaologin(username, password);
//		    JSONObject jsonObject = new JSONObject();
//	        for( Map.Entry<String, String> entry : map.entrySet() ) {
//	            String key = entry.getKey();
//	            Object value = entry.getValue();
//	            jsonObject.put(key, value);
//	        }
//	        FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
//	        fm.put("username", username);
//	        fm.put("password", password);
//	        
	        //마무리  https://zzznara2.tistory.com/687

//		    redirect.addFlashAttribute(OAuthService.save(map));
//		    ModelAndView mv = new ModelAndView();
//		    mv.setViewName("redirect:/auth/loginProc");
//		    mv.addObject("username", username);
//		    mv.addObject("password", password);
//		    
//		    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		    
//		    params.add("username", username.toString());
//		    params.add("password", password.toString());
//		    
//		    HttpHeaders headers = new HttpHeaders();
//		    
//		    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
//		    System.out.println(entity.toString());
//		    RestTemplate rt = new RestTemplate();
//
//		    ResponseEntity<String> response1 = rt.exchange(
//	                "http://localhost:8006/auth/loginProc", //{요청할 서버 주소}
//	                HttpMethod.POST, //{요청할 방식}
//	                entity, // {요청할 때 보낼 데이터}
//	                String.class //{요청시 반환되는 데이터 타입}
//		    		);
//		    System.out.println(response1.toString());
		   
//		    OAuthService.kakaologin(username, password);
		   
		    
//		    Cookie myCookie = new Cookie("user", userApiController.kakaologinproc(username, password));
//		    myCookie.setHttpOnly(true);
//		    myCookie.setPath("/");
//		    response.addCookie(myCookie);
//		    response.sendRedirect("/");

		    
		return "redirect:/"; 
	}
//	@GetMapping("/auth/naver")
//	public String naverload(HttpSession session) throws UnsupportedEncodingException {
//		
//		return "user/naverlogin";
//	}
	@RequestMapping(value="/oauth/naver")
	public String naver(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		String accessToken = "";
	    String refresh_token = "";
		 String clientId = "hDfVLGjHY3Pm8HsywMlG";//애플리케이션 클라이언트 아이디값";
		    String clientSecret = "GY2QiNgEza";//애플리케이션 클라이언트 시크릿값";
		    String code = request.getParameter("code");
		    String state = request.getParameter("state");
		    String redirectURI = URLEncoder.encode("http://localhost:8006/oauth/naver", "UTF-8");
		    String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
		        + "&client_id=" + clientId
		        + "&client_secret=" + clientSecret
		        + "&redirect_uri=" + redirectURI
		        + "&code=" + code
		        + "&state=" + state;
		   
		    try {
		      URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      if (responseCode == 200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      String res = new String();
		      while ((inputLine = br.readLine()) != null) {
		        res+=inputLine;
		      }
		      br.close();
		      if (responseCode == 200) {
		        System.err.println(res.toString());
		        JsonParser parser = new JsonParser();
	            JsonElement element = parser.parse(res);

	            accessToken = element.getAsJsonObject().get("access_token").getAsString();
	            refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

	            System.out.println("accessToken : " + accessToken);
	            System.out.println("refresh_token : " + refresh_token);
		      }
		    } catch (Exception e) {
		      // Exception 로깅
		    }
		    HashMap<String, Object> naverInfo = OAuthService.naverInfo(accessToken);
		   
		    OAuthService.naver가입(naverInfo);
			
		    
		    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(naverInfo.get("email"),"123"));
		    SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}
	
	@GetMapping("/auth/fail")
	public String faillogin() {
		
		return "user/loginfail";
	}
	
	@RequestMapping(value="/oauth/logout" )
	public String logout(HttpSession session) {
		
		OAuthService.kakaoLogout((String)session.getAttribute("access_Token"));
	    session.removeAttribute("access_Token");
	    session.removeAttribute("userId");
	    session.invalidate();
	    
	    return "redirect:/";
	}
	@PostMapping("/user/messagesearch")
	public String searchmessage(
								
								@RequestParam(value="searchuser")int searchuser,
								@RequestParam(value="messagesearchsel")String messagesearchsel,
								@RequestParam(value="searchtext")String searchtext,
								Pageable pageable,
								Model model) {
		
		model.addAttribute("messages",userService.수신메시지검색(searchuser,messagesearchsel,searchtext,pageable));
		
		return "user/message";

	}
	@PostMapping("/user/messagesendsearch")
	public String searchsendmessage(
								
								@RequestParam(value="searchuser")int searchuser,
								@RequestParam(value="messagesearchsel")String messagesearchsel,
								@RequestParam(value="searchtext")String searchtext,
								Pageable pageable,
								Model model) {
		
		model.addAttribute("messages",userService.발신메시지검색(searchuser,messagesearchsel,searchtext,pageable));
		
		return "user/sendmessage";

	}
	@GetMapping("/auth/finduserwindow")
	public String finduserwindow() {
		return "user/finduserid";
	}
	
	
}
