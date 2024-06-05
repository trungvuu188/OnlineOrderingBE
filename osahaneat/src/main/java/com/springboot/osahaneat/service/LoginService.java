package com.springboot.osahaneat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.osahaneat.dto.UsersDTO;
import com.springboot.osahaneat.entity.Roles;
import com.springboot.osahaneat.entity.Users;
import com.springboot.osahaneat.payload.request.SignUpRequest;
import com.springboot.osahaneat.repository.UserRepository;
import com.springboot.osahaneat.service.imp.LoginServiceImp;

@Service
public class LoginService implements LoginServiceImp {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public List<UsersDTO> getAllUsers() {
		List<Users> listUser = userRepository.findAll();
		List<UsersDTO> listUserDto = new ArrayList<>();

		for (Users user : listUser) {
			UsersDTO userDTO = new UsersDTO();
			userDTO.setId(user.getId());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setFullname(user.getFullname());
			listUserDto.add(userDTO);
		}

		return listUserDto;
	}

	@Override
	public boolean checkLogin(String username, CharSequence password) {

		Users user = userRepository.findByUsername(username);

		return passwordEncoder.matches(password, user.getPassword());
	}

	@Override
	public boolean addUser(SignUpRequest signUpRequest) {
		
		Roles roles = new Roles();
		roles.setId(signUpRequest.getRoleId());
		
		Users users = new Users();
		users.setFullname(signUpRequest.getFullname());
		users.setUsername(signUpRequest.getUsername());
		users.setPassword(signUpRequest.getPassword());
		users.setRoles(roles);

		try {
			userRepository.save(users);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
