package com.springboot.osahaneat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.osahaneat.entity.Category;
import com.springboot.osahaneat.entity.Food;
import com.springboot.osahaneat.repository.FoodRepository;
import com.springboot.osahaneat.service.imp.FileServiceImp;
import com.springboot.osahaneat.service.imp.FoodServiceImp;

@Service
public class FoodService implements FoodServiceImp{
	
	@Autowired
	FoodRepository foodRepository;
	
	@Autowired
	FileServiceImp fileServiceImp;
	
	@Override
	public boolean createMenu(MultipartFile file, String title, boolean isFreeship, String timeShip, double price, int cateId) {
		boolean isMenuCreated = false;
		try {
			boolean isFileSaved = fileServiceImp.save(file);
			if (isFileSaved) {
				Food food = new Food();
				food.setTitle(title);
				food.setFreeship(isFreeship);
				food.setImage(file.getOriginalFilename());
				food.setTimeShip(timeShip);
				food.setPrice(price);
				
				Category category = new Category();
				category.setId(cateId);
				food.setCategory(category);
				
				foodRepository.save(food);
				
				isMenuCreated = true;

			}
		} catch (Exception e) {
			System.out.println("Couldn't save file" + e.getMessage());
		}
		

		return isMenuCreated;
	}

}
