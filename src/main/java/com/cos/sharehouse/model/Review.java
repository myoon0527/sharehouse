package com.cos.sharehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name = "REVIEW_SEQ_GENERATOR"
		, sequenceName = "REVIEW_SEQ"
		, initialValue = 1
		, allocationSize = 1
		)
	
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REVIEW_SEQ_GENERATOR")
	private int id;
	
	@Column(nullable=false, length=200)
	private String content;
	
	@ManyToOne
	@JoinColumn(name="House")
	private House house;
	
	@ManyToOne
	@JoinColumn(name="Users")
	private Users users;
}
