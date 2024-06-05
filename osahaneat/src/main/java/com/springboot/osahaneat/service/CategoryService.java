package com.springboot.osahaneat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.osahaneat.dto.CategoryDTO;
import com.springboot.osahaneat.dto.FoodDTO;
import com.springboot.osahaneat.entity.Category;
import com.springboot.osahaneat.entity.Food;
import com.springboot.osahaneat.repository.CategoryRepository;
import com.springboot.osahaneat.service.imp.CategoryServiceImp;

@Service
public class CategoryService implements CategoryServiceImp{
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<CategoryDTO> getCategoryHomePage() {
		
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("id"));
		Page<Category> listCategory = categoryRepository.findAll(pageRequest);
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		
		for(Category data : listCategory) {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setName(data.getCateName());
			
			List<FoodDTO> menuDTOs = new ArrayList<FoodDTO>();
			
			for(Food food : data.getListFood()) {
				FoodDTO menuDTO = new FoodDTO();
				menuDTO.setTitle(food.getTitle());
				menuDTO.setFreeship(food.isFreeship());
				menuDTO.setImage(food.getImage());
				menuDTOs.add(menuDTO);
			}
			
			categoryDTO.setMenus(menuDTOs);
			categoryDTOs.add(categoryDTO);
			
		}
		
		return categoryDTOs;
	}

}
