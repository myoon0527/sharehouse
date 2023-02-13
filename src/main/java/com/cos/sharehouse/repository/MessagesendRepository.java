package com.cos.sharehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.sharehouse.model.Message;
import com.cos.sharehouse.model.Messagesend;

public interface MessagesendRepository extends JpaRepository<Messagesend,Integer>{

	Page<Messagesend> findBySenduserid(int id, Pageable pageable);

	Page<Message> findByReceiveuseridAndReceiveusernickContaining(int userid, String searchtext, Pageable pageable);

	Page<Messagesend> findByReceiveuseridAndContentsContaining(int userid, String searchtext, Pageable pageable);

	Page<Messagesend> findBySenduseridAndReceiveusernickContaining(int userid, String searchtext, Pageable pageable);

}
