package com.cos.sharehouse.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="house")
@SequenceGenerator(
		name = "HOUSE_SEQ_GENERATOR"
		, sequenceName = "HOUSE_SEQ"
		, initialValue = 1
		, allocationSize = 1
		)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class House {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HOUSE_SEQ_GENERATOR")
	private int id;
	
	@Column
	private String category;
	
	@Column
	private String type;
	
	@Column(length=30)
	private String addr;
	
	@Column(length=30)
	private String addr2;
	
	@Column
	private int guestMax;
	
	@Column
	private int bed;
	
	@Column
	private int bedroom;

	@Column
	private int bathroom;
	
	@Column
	private String amenities;
	
	@Column(length=100)
	private String title;
	
	@Lob
	private String explain;
	
	@Column
	private int fare;
	
	@ColumnDefault("0")
	private int reviewcount;
	
	@Lob
	private String img1;
	
	@Lob
	private String img2;
	
	@Lob
	private String img3;
	
	@Lob
	private String img4;
	
	@Lob
	private String img5;
	
	@OneToMany(mappedBy="house", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	private List<Review> review;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JoinColumn(name="Users")
	private Users users;
}
