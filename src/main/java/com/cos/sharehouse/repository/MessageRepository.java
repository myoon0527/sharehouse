package com.cos.sharehouse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.sharehouse.model.Message;

public interface MessageRepository extends JpaRepository<Message,Integer>{

	Page<Message> findByReceiveuserid(int id,Pageable pageable);

	Page<Message> findByReceiveuseridContaining(String searchtext, Pageable pageable);

	Page<Message> findByContentsContaining(String searchtext, Pageable pageable);

	Page<Message> findByReceiveusernickContaining(String searchtext, Pageable pageable);

	Page<Message> findByReceiveuseridAndReceiveusernickContaining(int receiveuserid,String searchtext, Pageable pageable);

	Page<Message> findByReceiveuseridAndContentsContaining(int userid, String searchtext, Pageable pageable);

	Page<Message> findByReceiveuseridAndSenduseridContaining(int userid, String searchtext, Pageable pageable);

	Page<Message> findByReceiveuseridAndSendusernickContaining(int userid, String searchtext, Pageable pageable);
	
}
