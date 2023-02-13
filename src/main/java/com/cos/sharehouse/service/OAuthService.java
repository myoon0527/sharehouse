package com.cos.sharehouse.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.sharehouse.model.Users;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minidev.json.JSONObject;

@Service
public class OAuthService {
	@Autowired
	private userService userService;
	

	
	 public void kakaologin(String id,String pwd)throws Exception {
		 String reqURL = "http://localhost:8006/auth/loginProc";
	
	        
	            URL url = new URL(reqURL);
	        
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("POST");
//	            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=utf-8");
	            conn.setRequestProperty("Content-Type ", "application/json; charset=utf-8");
	            conn.setRequestProperty("Accept", "application/json");
	            conn.setDoOutput(true);
	            
	            String jsonInputString = "{username:"+id+", password:"+pwd+"}";
	           
//			    String testname =id;
	            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            System.out.println(jsonInputString);
	           try(OutputStream os = conn.getOutputStream()) {
	            byte[] input = jsonInputString.getBytes("utf-8");
	            os.write(input, 0, input.length);
	 			}
//	           StringBuffer buffer = new StringBuffer();
//
//	            //HashMap으로 전달받은 파라미터가 null이 아닌경우 버퍼에 넣어준다
//	            if (map != null) {
//
//	                Set key = map.keySet();
//
//	                for (Iterator iterator = key.iterator(); iterator.hasNext();) {
//	                    String keyName = (String) iterator.next();
//	                    String valueName = map.get(keyName);
//	                    buffer.append(keyName).append("=").append(valueName);
//	                }
//	            }
//
//	            OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
//	            PrintWriter writer = new PrintWriter(outStream);
//	            writer.write(testname);
//	            writer.flush();
	        
	        System.out.println("로그인 시도");
	        
		 
		
	 }
	 public static String getJsonStringFromMap(Map<String, String> map) {
		    
			JSONObject json = new JSONObject();
			
			for(Entry<String, String> entry : map.entrySet()) {
				
				String key = entry.getKey();
	            Object value = entry.getValue();
	            
	            json.put(key, value);
	        }
	        
	        return json.toJSONString();
		}
	 public String getKakaoAccessToken (String code) {
	        String access_Token = "";
	        String refresh_Token = "";
	        String reqURL = "https://kauth.kakao.com/oauth/token";

	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);

	            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            sb.append("grant_type=authorization_code");
	            sb.append("&client_id=bc3d1da7dfe8afb92b61f8f94ad85623"); // TODO REST_API_KEY 입력
	            sb.append("&redirect_uri=http://localhost:8006/oauth/kakao"); // TODO 인가코드 받은 redirect_uri 입력
	            sb.append("&code=" + code);
	            bw.write(sb.toString());
	            bw.flush();

	            //결과 코드가 200이라면 성공
	            int responseCode = conn.getResponseCode();
	            System.out.println("responseCode : " + responseCode);

	            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";

	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            System.out.println("response body : " + result);

	            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
	            JsonParser parser = new JsonParser();
	            JsonElement element = parser.parse(result);

	            access_Token = element.getAsJsonObject().get("access_token").getAsString();
	            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

	            System.out.println("access_token : " + access_Token);
	            System.out.println("refresh_token : " + refresh_Token);

	            br.close();
	            bw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return access_Token;
	    }
	 public HashMap<String,Object> getUserInfo(String access_Token){
		 HashMap<String, Object> userInfo = new HashMap<>();
		 String reqUrl= "https://kapi.kakao.com/v2/user/me";
		 try {
			 URL url = new URL(reqUrl);
			 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			 conn.setRequestMethod("POST");
			 
			 conn.setRequestProperty("Authorization", "Bearer "+access_Token);
			 int responseCode = conn.getResponseCode();
			  BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        
		        String line = "";
		        String result = "";
			  while ((line = br.readLine()) != null) {
		            result += line;
		        }
		        System.out.println("response body : " + result);
		        
		        JsonParser parser = new JsonParser();
		        JsonElement element = parser.parse(result);
		    
		        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
		       
		        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
		        
		        String kakao_pkid =result.substring(6, 16); //카카오 고유번호 
		        
		        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
		        String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
		        
		        String email = kakao_account.getAsJsonObject().get("email").getAsString();
		        userInfo.put("kakaoid", kakao_pkid);
		        userInfo.put("nickname", nickname);
		        userInfo.put("email", email);
		        userInfo.put("profile_image", profile_image);
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 
		 return userInfo;
	 }
	 public void naver가입(HashMap naverInfo) {
		 Users naver_user =new Users();
		 
			 naver_user = Users.builder()
					 .username(naverInfo.get("email").toString())
					 .password("123")
					 .nickname(naverInfo.get("username").toString())
					 .email(naverInfo.get("email").toString())
					 .social("naver")
					 .phonenumber(naverInfo.get("phonenumber").toString())
					 .build();
		 
			  
		 Users refer = userService.회원찾기(naverInfo.get("email").toString());
		 if(refer.getUsername()== null) {
			 userService.회원가입(naver_user);
		 }
	 }
	 public void kakao가입(HashMap userInfo) {
		 Users kakao_user =new Users();
		 if(userInfo.get("email")==null) {
			 kakao_user = Users.builder()
					 .username(userInfo.get("kakaoid").toString())
					 .password("123")
					 .nickname(userInfo.get("nickname").toString())
					 .email("none")
					 .social("kakao")
					 .profileimage(userInfo.get("profile_image").toString())
					 .build();
		 }else {
			  kakao_user = Users.builder()
					 .username(userInfo.get("kakaoid").toString())
					 .password("123")
					 .nickname(userInfo.get("nickname").toString())
					 .email(userInfo.get("email").toString())
					 .social("kakao")
					 .profileimage(userInfo.get("profile_image").toString())
					 .build();
		 }
		 Users refer = userService.회원찾기(userInfo.get("kakaoid").toString());
		 if(refer.getUsername()== null) {
			 userService.회원가입(kakao_user);
			
		 }
//		 MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		    
//		    params.add("username", userInfo.get("nickname").toString());
//		    params.add("password", "123");
//		    
//		    HttpHeaders headers = new HttpHeaders();
//		    
//		    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
//		    System.out.println(entity.toString());
//		    RestTemplate rt = new RestTemplate();
//
//		    ResponseEntity<String> response1 = rt.exchange(
//	             "http://localhost:8006/auth/loginProc", //{요청할 서버 주소}
//	             HttpMethod.POST, //{요청할 방식}
//	             entity, // {요청할 때 보낼 데이터}
//	              //{요청시 반환되는 데이터 타입}
//	             String.class);
//		    System.out.println(response1.toString());
	 }
	 
	 public void kakaoLogout(String access_Token) {
		    String reqURL = "https://kapi.kakao.com/v1/user/logout";
		    try {
		        URL url = new URL(reqURL);	
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("POST");
		        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
		        
		        int responseCode = conn.getResponseCode();
		        System.out.println("responseCode : " + responseCode);
		        
		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        
		        String result = "";
		        String line = "";
		        
		        while ((line = br.readLine()) != null) {
		            result += line;
		        }
		        System.out.println(result);
		       
		        
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		}
	 
	 public Users save(Map<String, String> map ) {
		 Users user = new Users();
		 	user.setUsername(map.get("username"));
		 	user.setPassword(map.get("password"));
		 return user;
		 
	 }
	 public HashMap<String,Object> naverInfo(String code) {
		 HashMap<String,Object> naverInfo = new HashMap<>();
		 String token = code; // 네이버 로그인 접근 토큰;
	        String header = "Bearer " + token; // Bearer 다음에 공백 추가
	        String apiURL = "https://openapi.naver.com/v1/nid/me";
	        Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("Authorization", header);
	        String responseBody = get(apiURL,requestHeaders);
	        System.out.println(responseBody);
	        JsonParser parser = new JsonParser();
	        JsonElement element = parser.parse(responseBody);
	    
	        JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();
	       
	        String username = response.getAsJsonObject().get("name").getAsString();
	        
	        String email = response.getAsJsonObject().get("email").getAsString();
	        String phonenumber =response.getAsJsonObject().get("mobile").getAsString().replaceAll("-", "");
	     
	        naverInfo.put("username", convertString(username));
	        naverInfo.put("email",email);
	        naverInfo.put("phonenumber", phonenumber);
	        return naverInfo;
	 }
	 private static String get(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }
	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                return readBody(con.getInputStream());
	            } else { // 에러 발생
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }


	    private static HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }


	    private static String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);


	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();


	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	                
	            }


	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	    }
	    public static String convertString(String val) {
			  // 변환할 문자를 저장할 버퍼 선언
			  StringBuffer sb = new StringBuffer();
			  // 글자를 하나하나 탐색한다.
			  for (int i = 0; i < val.length(); i++) {
			    // 조합이  시작하면 6글자를 변환한다. 
			    if ('\\' == val.charAt(i) && 'u' == val.charAt(i + 1)) {
			      // 그 뒤 네글자는 유니코드의 16진수 코드이다. int형으로 바꾸어서 다시 char 타입으로 강제 변환한다.
			      Character r = (char) Integer.parseInt(val.substring(i + 2, i + 6), 16);
			      // 변환된 글자를 버퍼에 넣는다.
			      sb.append(r);
			      // for의 증가 값 1과 5를 합해 6글자를 점프
			      i += 5;
			    } else {
			      // ascii코드면 그대로 버퍼에 넣는다.
			      sb.append(val.charAt(i));
			    }
			  }
			  // 결과 리턴
			  return sb.toString();
			}
}
