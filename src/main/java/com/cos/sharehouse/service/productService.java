package com.cos.sharehouse.service;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.sharehouse.model.House;
import com.cos.sharehouse.repository.houseRepository;

@Service
public class productService {
	@Autowired
	private houseRepository houseRepository;
	
	@Transactional(readOnly=true)
	public Page<House> 제품검색(String[] type,String[] category,int[] bedroom,String amenities,Pageable page) {
		
		Page<House> houselist =null;
//		//repository에 가서 직접 sql 문 입력 해주기
//		//타입은 or  침실수 or 건물도 or  편의 시설은 and 
		amenities = amenities.replace(",", ",%"); //amenities like 와일드카드 사용하기위함
		System.out.println("type"+type.length);
		if(type.length==0) {
			type =new String[] {"privateRoom","hometypeshared"};
		}
		System.out.println("category"+category);
		if(category.length==0) {
			category=new String[] {"housing","apartment","hotel","separate"};
		}
		System.out.println("bedroom"+bedroom);
		if(bedroom.length==0) {
			bedroom=new int[] {1,2,3,4};
		}
		System.out.println("amenities"+amenities);
		if(amenities.equals("")) {
			amenities ="%%";
		}
		
		
		System.out.println(houseRepository.searchhouse(type,category,bedroom,amenities,page));
		
		
		//----성공----
//		System.out.println(houseRepository.searchtype(type,page));
//		System.out.println(houseRepository.searchcategory(category,page));
//		System.out.println( houseRepository.search(amenities,page));
//		System.out.println(houseRepository.searchbedroom(bedroom, page));// 방갯수 찾기는 성공 
		
		return houselist;
	}


	
}
