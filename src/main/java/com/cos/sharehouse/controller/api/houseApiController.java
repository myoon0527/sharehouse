package com.cos.sharehouse.controller.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.sharehouse.config.auth.PrincipalDetail;
import com.cos.sharehouse.dto.ResponseDto;
import com.cos.sharehouse.dto.uploadDto;
import com.cos.sharehouse.model.Review;
import com.cos.sharehouse.model.reserv;
import com.cos.sharehouse.service.houseService;

@RestController
public class houseApiController {
	
	@Autowired
	private houseService houseService;
	
	//숙소 등록
//	@PostMapping("/api/house")
//	public ResponseDto<Integer> houseSave(@RequestBody House house, @AuthenticationPrincipal PrincipalDetail principal) {
//		houseService.save(house, principal.getUser());
//		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//	}
	
	@PostMapping("/api/houseSave")
	public ResponseDto<Integer> Upload(uploadDto uploadDto, @AuthenticationPrincipal PrincipalDetail principal) {
		
		houseService.save(uploadDto, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
				
	}
	
	//사진 출력
	@GetMapping("/auth/img")
	public ResponseEntity<Resource> display(@RequestParam("imgPath") String img) {
		String path = "C:\\image\\";
		Resource resource = new FileSystemResource(path + img);
		
		if(!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders header = new HttpHeaders();
		
		Path filePath = null;
		try {
			filePath = (Path) Paths.get(path + img);
			header.add("Content-Type", Files.probeContentType(filePath));

		} catch (IOException e) {
			e.printStackTrace();

		}
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	//리뷰 작성
	@PostMapping("/api/house/{id}/review")
	public ResponseDto<Integer> reviewSave(@PathVariable int id, @RequestBody Review review, @AuthenticationPrincipal PrincipalDetail principal) {
		houseService.writeReview(id, review, principal.getUser());
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//예약
	@PostMapping("/api/house/{id}/reserv")
	public ResponseDto<Integer> reservSave(@PathVariable int id, @RequestBody reserv reserv, @AuthenticationPrincipal PrincipalDetail principal) {
		houseService.saveReserv(id, reserv, principal.getUser());
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
