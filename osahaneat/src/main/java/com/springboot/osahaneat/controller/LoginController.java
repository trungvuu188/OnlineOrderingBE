package com.springboot.osahaneat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.osahaneat.payload.ResponseData;
import com.springboot.osahaneat.payload.request.SignUpRequest;
import com.springboot.osahaneat.service.imp.LoginServiceImp;
import com.springboot.osahaneat.utils.JwtUtilsHelper;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginServiceImp loginServiceImp;
	
	@Autowired
	JwtUtilsHelper jwtUtilsHelper;

	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password) {
		ResponseData responseData = new ResponseData();
		if (loginServiceImp.checkLogin(username, password)) {
			String token = jwtUtilsHelper.generateToken(username);
			responseData.setData(token);
		} else {
			responseData.setData("");
			responseData.setSuccess(false);
		}

		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
		ResponseData responseData = new ResponseData();
		responseData.setData(loginServiceImp.addUser(signUpRequest));

		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<?> getAll() {
		
		ResponseData responseData = new ResponseData();
		responseData.setData(loginServiceImp.getAllUsers());
		
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

}
