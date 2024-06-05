package com.springboot.osahaneat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.osahaneat.payload.ResponseData;
import com.springboot.osahaneat.service.imp.FoodServiceImp;

@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	FoodServiceImp foodServiceImp;
	
	@PostMapping()
	public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,
												@RequestParam String title,
												@RequestParam boolean isFreeship,
												@RequestParam String timeShip,
												@RequestParam double price,
												@RequestParam int cateId
												){
		
		ResponseData responseData = new ResponseData();
		boolean isSuccess = foodServiceImp.createMenu(file, title, isFreeship, timeShip, price, cateId);
		responseData.setData(isSuccess);
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
}
