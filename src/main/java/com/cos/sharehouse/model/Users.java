package com.cos.sharehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name = "USER_SEQ_GENERATOR"
		, sequenceName = "USER_SEQ"
		, initialValue = 1
		, allocationSize = 1
		)
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR")
	private int id;
	@Column(nullable=false, length=100,unique = true)
	private String username; //아이디
	@Column(length=100)
	private String nickname; //유저이름 ,별명
	@Column(nullable=false, length=100)
	private String password; //비밀번호
	@Column(length=100) 
	private String email; // 이메일 인증번호
	@Column( length=100)
	private String phonenumber; // 전화번호 실명인증 
	@Lob
	private String profileimage; //프로필 이미지 
	@ColumnDefault("0")
	private int grade; // 등급
	
	@Enumerated(EnumType.STRING)
	private RoleType roles;// 권한 (게스트, 호스트(인증필요))
	
	@Enumerated(EnumType.STRING)
	private CurrentType type;//상태 (기본, 임시 정지, 영구정지) 
	
	private String social;
	
	@Enumerated(EnumType.STRING)
	private authType userauth;
	
}
