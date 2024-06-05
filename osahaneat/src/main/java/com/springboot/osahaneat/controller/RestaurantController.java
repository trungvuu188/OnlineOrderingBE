package com.springboot.osahaneat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.osahaneat.payload.ResponseData;
import com.springboot.osahaneat.service.imp.FileServiceImp;
import com.springboot.osahaneat.service.imp.RestaurantServiceImp;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	FileServiceImp fileServiceImp;
	
	@Autowired
	RestaurantServiceImp restaurantServiceImp;
	
	@PostMapping()
	public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,
												@RequestParam String title,
												@RequestParam String subtitle,
												@RequestParam String description,
												@RequestParam boolean isFreeship,
												@RequestParam String address,
												@RequestParam String openDate
												){
		
		ResponseData responseData = new ResponseData();
		boolean isSuccess = restaurantServiceImp.insertRestaurant(file, title, subtitle, description, isFreeship, address, openDate);
		responseData.setData(isSuccess);
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<?> getHomePageRestaurant() {
		
		ResponseData responseData = new ResponseData();
		responseData.setData(restaurantServiceImp.getHomePageRestaurant());
		
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
	@GetMapping("/file/{filename:.+}")
	public ResponseEntity<?> getFileRestaurant(@PathVariable String filename) {
		
		Resource resource = fileServiceImp.load(filename);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
	}
	
	@GetMapping("/detail")
	public ResponseEntity<?> getDetailRestaurantById(@RequestParam int id){
		
		ResponseData responseData = new ResponseData();
		responseData.setData(restaurantServiceImp.getDetailRestaurantById(id));
		
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
 

}

