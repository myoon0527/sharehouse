package com.cos.sharehouse.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class uploadDto {
	private MultipartFile[] file;
	private String category;
	private String type;
	private String addr;
	private String addr2;
	private int guestMax;

	private int bed;

	private int bedroom;

	private int bathroom;

	private String amenities;

	private String title;

	private String explain;

	private int fare;
	
//	public House toEntity(String category, String type, String addr, 
//			String addr2, int guestMax, int bed, int bedroom,
//			int bathroom, String amenities, String title, String explain, int fare
//			) {
//		return House.builder()
//				.category(category)
//				.type(type)
//				.addr(addr)
//				.addr2(addr2)
//				.guestMax(guestMax)
//				.bed(bed)
//				.bedroom(bedroom)
//				.bathroom(bathroom)
//				.amenities(amenities)
//				.title(title)
//				.explain(explain)
//				.fare(fare)
//				.build();
//	}
}