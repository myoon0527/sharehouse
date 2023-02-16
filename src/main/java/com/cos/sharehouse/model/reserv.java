package com.cos.sharehouse.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name="checkin",
						columnNames = {"House", "checkIn"}
			),
				@UniqueConstraint(
						name="checkout",
						columnNames = {"House", "checkOut"}
						)
				
		}
)
@SequenceGenerator(
		name = "RESERV_SEQ_GENERATOR"
		, sequenceName = "RESERV_SEQ"
		, initialValue = 1
		, allocationSize = 1
		)

public class reserv {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RESERV_SEQ_GENERATOR")
	private int id;
	
	@Column(nullable=false, length=100)
	private String checkIn;
	
	@Column(nullable=false, length=100)
	private String checkOut;
	
	
	@JsonBackReference
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="House")
	private House house;
	
	
	@JsonBackReference
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JoinColumn(name="Users")
	private Users users;
	
}
