package com.springboot.osahaneat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.osahaneat.payload.ResponseData;
import com.springboot.osahaneat.payload.request.OrderRequest;
import com.springboot.osahaneat.service.imp.OrderServiceImp;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderServiceImp orderServiceImp;
	
	@PostMapping
	public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
		
		ResponseData responseData = new ResponseData();
		responseData.setData(orderServiceImp.insertOrder(orderRequest));
		
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
}
