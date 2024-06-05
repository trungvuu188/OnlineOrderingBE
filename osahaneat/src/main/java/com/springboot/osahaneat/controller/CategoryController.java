package com.springboot.osahaneat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.osahaneat.payload.ResponseData;
import com.springboot.osahaneat.service.imp.CategoryServiceImp;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryServiceImp categoryServiceImp;

	@GetMapping()
	public ResponseEntity<?> getHomePageCategory(){
			
		ResponseData responseData = new ResponseData();
		responseData.setData(categoryServiceImp.getCategoryHomePage());
		
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
}	
