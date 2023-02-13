package com.cos.sharehouse.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cos.sharehouse.model.CurrentType;
import com.cos.sharehouse.model.Message;
import com.cos.sharehouse.model.Messagesend;
import com.cos.sharehouse.model.RoleType;
import com.cos.sharehouse.model.Users;
import com.cos.sharehouse.model.authType;
import com.cos.sharehouse.repository.MessageRepository;
import com.cos.sharehouse.repository.MessagesendRepository;
import com.cos.sharehouse.repository.userRepository;

@Service
public class userService {
	static String imgurl;
	@Autowired
	private MessageRepository messageRepository; //수신전용
	@Autowired
	private MessagesendRepository messagesendRepository; //발신전용
	@Autowired
	private userRepository userrepository;
	@Autowired
	private BCryptPasswordEncoder encodeer;
	
	
	@Transactional
	public void 회원가입(Users user) {
		user.setRoles(RoleType.guest);
		user.setType(CurrentType.defaultType);
		user.setUserauth(authType.notauth);
		String rawPassword = user.getPassword();
		String encPassword = encodeer.encode(rawPassword);
		user.setPassword(encPassword);
		userrepository.save(user);
	}
	@Transactional(readOnly = true)
	public Users 회원찾기(String username) {
		Users refer =userrepository.findByUsername(username).orElseGet(()-> {
			return new Users();
	});
	return refer;
	}
	@Transactional(readOnly= true)
	public Users 회원정보(int id) {
		Users refer = userrepository.findById(id).orElseGet(()->{
			return new Users();
		});
		return refer;
	}
	@Transactional
	public Users test(String username) {
		Users refer =userrepository.findByUsername(username).orElseGet(()-> {
			return new Users();
		});
		return refer;
	}
	@Transactional
	public void 회원정보변경(int id,Users user) {
		Users refer = 회원정보(id);
		refer.setEmail(user.getEmail());
		refer.setPhonenumber(user.getPhonenumber());
		String rawPassword = user.getPassword();
		String encPassword = encodeer.encode(rawPassword);
		refer.setPassword(encPassword);
		
	}
	@Transactional
	public void 회원탈퇴(int id) {
		userrepository.deleteById(id);
		
	}
	@Transactional
	public void 이미지변경(int id,MultipartFile profileimage) throws IOException {
		Users refer= 회원정보(id);
		UUID uuid =UUID.randomUUID();
		profileimage.transferTo(new File("C:\\Users\\GREEN\\Documents\\workspace-spring-tool-suite-4-4.11.0.RELEASE\\sharehouse\\src\\main\\resources\\static\\profile\\"+uuid.toString()+profileimage.getOriginalFilename()));
		imgurl=uuid.toString()+profileimage.getOriginalFilename();
		refer.setProfileimage("/profile/"+imgurl);
	}
	@Transactional
	public Page<Message>  수신메시지검색(int userid,String searchmod, String searchtext,Pageable pageable){
		
		Page<Message> messagelist = null;
		
		switch (searchmod){
			case "finduserid":{
				messagelist=messageRepository.findByReceiveuseridAndSendusernickContaining(userid,searchtext,pageable);
				
				break;
			}
			case "findcontent":{
				messagelist=messageRepository.findByReceiveuseridAndContentsContaining(userid,searchtext,pageable);
				
				break;
			}
		}
		System.out.println(messagelist);
			
			
		return messagelist;
	}
	@Transactional
	public Page<Messagesend>  발신메시지검색(int userid,String searchmod, String searchtext,Pageable pageable){
		
		Page<Messagesend> messagelist = null;
		
		switch (searchmod){
			case "finduserid":{
				messagelist=messagesendRepository.findBySenduseridAndReceiveusernickContaining(userid,searchtext,pageable);
				
				break;
			}
			case "findcontent":{
				messagelist=messagesendRepository.findByReceiveuseridAndContentsContaining(userid,searchtext,pageable);
				
				break;
			}
		}
		System.out.println(messagelist);
			
			
		return messagelist;
	}
	@Transactional
	public Users finduseremail(String usernick,String email) {
		Users refer = userrepository.findByNicknameAndEmail(usernick,email).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		return refer;
	}
	@Transactional
	public Users finduseridmail(String userid, String email2) {
		Users refer = userrepository.findByUsernameAndEmail(userid,email2).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		return refer;	
	}
	@Transactional
	public void 비밀번호변경(Users user) {
		Users refer = 회원정보(user.getId());
		String rawPassword = user.getPassword();
		String encPassword = encodeer.encode(rawPassword);
		refer.setPassword(encPassword);
	}
	@Transactional
	public void 권한설정(int id) {
		Users refer = 회원정보(id);
		refer.setRoles(RoleType.host);
		refer.setUserauth(authType.auth);
		
	}
}