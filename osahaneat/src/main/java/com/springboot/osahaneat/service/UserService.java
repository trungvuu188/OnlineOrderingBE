package com.springboot.osahaneat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.osahaneat.dto.UsersDTO;
import com.springboot.osahaneat.entity.Users;
import com.springboot.osahaneat.repository.UserRepository;
import com.springboot.osahaneat.service.imp.UserServiceImp;

@Service
public class UserService implements UserServiceImp{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<UsersDTO> getAllUser() {
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
}
