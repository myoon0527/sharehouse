package com.cos.sharehouse.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cos.sharehouse.dto.uploadDto;
import com.cos.sharehouse.model.House;
import com.cos.sharehouse.model.Users;
import com.cos.sharehouse.repository.houseRepository;

@Service
public class houseService {
	
	@Autowired
	private houseRepository houseRepository;
	
	@Transactional
	public void save(uploadDto uploadDto, Users user) {
		House house = new House();
		
		house.setAddr(uploadDto.getAddr());
		house.setAddr2(uploadDto.getAddr2());
		house.setCategory(uploadDto.getCategory());
		house.setType(uploadDto.getType());
		house.setGuestMax(uploadDto.getGuestMax());
		house.setBed(uploadDto.getBed());
		house.setBedroom(uploadDto.getBedroom());
		house.setBathroom(uploadDto.getBathroom());
		house.setAmenities(uploadDto.getAmenities());
		house.setTitle(uploadDto.getTitle());
		house.setExplain(uploadDto.getExplain());
		house.setFare(uploadDto.getFare());
		
		MultipartFile[] file = uploadDto.getFile();
		System.out.println(file.length);
		
		UUID uuid = UUID.randomUUID();
		
		house.setImg1(uuid + file[0].getOriginalFilename());
		house.setImg2(uuid + file[1].getOriginalFilename());
		house.setImg3(uuid + file[2].getOriginalFilename());
		house.setImg4(uuid + file[3].getOriginalFilename());
		house.setImg5(uuid + file[4].getOriginalFilename());
		
		String fileUrl = "C:\\image\\";
		
		for(int i=0; i<file.length; i++) {
			try {
				file[i].transferTo(new File(fileUrl + uuid + file[i].getOriginalFilename()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		house.setUsers(user);
		houseRepository.save(house);
	}
}
