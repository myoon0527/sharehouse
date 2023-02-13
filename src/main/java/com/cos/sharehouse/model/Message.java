package com.cos.sharehouse.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="Message")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name = "Message_SEQ_GENERATOR"
		, sequenceName = "Message_SEQ"
		, initialValue = 1
		, allocationSize = 1
		)
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Message_SEQ_GENERATOR")
	private int id;
	
	private int senduserid; //발신인 
	private String sendusernick;
	private int receiveuserid; //수신인
	private String receiveusernick;
	
	@Lob
	private String contents;
	@ColumnDefault("0")
	private int checkcount;
	
	@CreationTimestamp
	private Timestamp sendDate;//보낸 시간
	
	//수신칸 전용
}
