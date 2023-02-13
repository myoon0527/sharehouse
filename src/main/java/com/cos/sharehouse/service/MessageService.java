package com.cos.sharehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.sharehouse.model.Message;
import com.cos.sharehouse.model.Messagesend;
import com.cos.sharehouse.model.Users;
import com.cos.sharehouse.repository.MessageRepository;
import com.cos.sharehouse.repository.MessagesendRepository;
@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository; //수신전용
	@Autowired
	private MessagesendRepository messagesendRepository; //발신전용
	@Autowired
	private userService userService;
	//메시지를 등록 해주기 
	//메시지 출력 (목록 )
	//삭제 .. 업데이트는 없음
	@Transactional
	public void 메시지발신(Message message) {
		Users senduser =userService.회원정보(message.getSenduserid());
		message.setSendusernick(senduser.getUsername());
		Users receiveuser = userService.회원정보(message.getReceiveuserid());
		message.setReceiveusernick(receiveuser.getUsername());
		messageRepository.save(message);
		Messagesend refer = new Messagesend();
		refer.setContents(message.getContents());
		refer.setReceiveuserid(message.getReceiveuserid());
		refer.setReceiveusernick(message.getReceiveusernick());
		refer.setSenduserid(message.getSenduserid());
		refer.setSendusernick(message.getSendusernick());
		messagesendRepository.save(refer);
		
		
	}
	//수신메시지보기
	@Transactional(readOnly=true)
	public Page<Message> 수신메시지보기(int id,Pageable pageable) {
		Page<Message> messagelist = (Page<Message>)messageRepository.findByReceiveuserid(id,pageable);
		//유저 아이디로 수신된 아이디만 찾기
		return messagelist;
	}
	@Transactional(readOnly=true)
	public Page<Messagesend> 발신메시지보기(int id,Pageable pageable) {
		
		Page<Messagesend> messagelist = (Page<Messagesend>)messagesendRepository.findBySenduserid(id,pageable);	
		//유저 아이디로 수신된 아이디만 찾기
		return messagelist;
	}
	@Transactional(readOnly=true)
	public Message 수신메시지상세보기(int id) {
		Message refer = messageRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("상세보기 실패");
		});
		return refer;
	}
	@Transactional(readOnly=true)
	public Messagesend 발신메시지상세보기(int id) {
		Messagesend refer = messagesendRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("상세보기 실패");
		});
		return refer;
	}
	@Transactional
	public void 수신메시지삭제(int id) {
		messageRepository.deleteById(id);
	}
	@Transactional
	public void 발신메시지삭제(int id) {
		messagesendRepository.deleteById(id);
	}
}
