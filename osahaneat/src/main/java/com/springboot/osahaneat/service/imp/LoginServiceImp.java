package com.springboot.osahaneat.service.imp;

import java.util.List;

import com.springboot.osahaneat.dto.UsersDTO;
import com.springboot.osahaneat.payload.request.SignUpRequest;

public interface LoginServiceImp {
	List<UsersDTO> getAllUsers();
	boolean checkLogin(String username, CharSequence password);
	boolean addUser(SignUpRequest signUpRequest);
}
