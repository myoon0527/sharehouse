package com.cos.sharehouse.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.sharehouse.config.auth.PrincipalDetail;
import com.cos.sharehouse.dto.ResponseDto;
import com.cos.sharehouse.dto.uploadDto;
import com.cos.sharehouse.model.House;
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
}
