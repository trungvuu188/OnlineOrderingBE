package com.springboot.osahaneat.service.imp;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.osahaneat.dto.RestaurantDTO;

public interface RestaurantServiceImp {

	boolean insertRestaurant( MultipartFile file,
								String title,
								String subtitle,
								String description,
								boolean isFreeship,
								String address,
								String openDate);
	
	List<RestaurantDTO> getHomePageRestaurant();
	
	RestaurantDTO getDetailRestaurantById(int id);
}
