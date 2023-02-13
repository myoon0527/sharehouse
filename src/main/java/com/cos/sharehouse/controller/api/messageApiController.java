package com.cos.sharehouse.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.sharehouse.dto.ResponseDto;
import com.cos.sharehouse.model.Message;
import com.cos.sharehouse.model.Messagesend;
import com.cos.sharehouse.service.MessageService;

@RestController
public class messageApiController {
	@Autowired
	private MessageService MessageService;
	//메시지 등록 (발신 버튼 클릭시 받아올 주소)
	@PostMapping("/api/sendMessage")
	public ResponseDto<Integer> sendMessage(@RequestBody Message message){
		MessageService.메시지발신(message);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@DeleteMapping("/api/deleteMessage/{id}")
	public ResponseDto<Integer> deleteMessage(@PathVariable int id){
		//수신메시지 삭제시 여기로 
		MessageService.수신메시지삭제(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@DeleteMapping("/api/deleteMessagesend/{id}")
	public ResponseDto<Integer> deleteMessagesend(@PathVariable int id){
		//발신메시지 삭제시 여기로 		
		MessageService.발신메시지삭제(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
