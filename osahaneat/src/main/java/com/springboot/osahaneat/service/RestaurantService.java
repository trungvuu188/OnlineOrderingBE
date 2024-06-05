package com.springboot.osahaneat.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.osahaneat.dto.CategoryDTO;
import com.springboot.osahaneat.dto.FoodDTO;
import com.springboot.osahaneat.dto.RestaurantDTO;
import com.springboot.osahaneat.entity.Food;
import com.springboot.osahaneat.entity.MenuRestaurant;
import com.springboot.osahaneat.entity.RatingRestaurant;
import com.springboot.osahaneat.entity.Restaurant;
import com.springboot.osahaneat.repository.RestaurantRepository;
import com.springboot.osahaneat.service.imp.FileServiceImp;
import com.springboot.osahaneat.service.imp.RestaurantServiceImp;

@Service
public class RestaurantService implements RestaurantServiceImp {

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	FileServiceImp fileServiceImp;

	@Override
	public boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description,
			boolean isFreeship, String address, String openDate) {

		boolean isFileSaved = fileServiceImp.save(file);
		boolean isRestaurantSaved = false;
		if (isFileSaved) {
			Restaurant restaurant = new Restaurant();
			restaurant.setTitle(title);
			restaurant.setSubtitle(subtitle);
			restaurant.setDescription(description);
			restaurant.setImage(file.getOriginalFilename());
			restaurant.setFreeship(isFreeship);
			restaurant.setAddress(address);

			try {
				Date formatedDate = parseDate(openDate);
				restaurant.setOpenDate(formatedDate);
			} catch (ParseException e) {
				System.out.println("Couldn't parse String to Date" + e.getMessage());
			}

			restaurantRepository.save(restaurant);
			isRestaurantSaved = true;
		}

		return isRestaurantSaved;
	}

	private Date parseDate(String date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
		return simpleDateFormat.parse(date);
	}

	@Override
	public List<RestaurantDTO> getHomePageRestaurant() {

		List<RestaurantDTO> restaurantDTOs = new ArrayList<RestaurantDTO>();
		PageRequest pageRequest = PageRequest.of(0, 6);
		Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);

		for (Restaurant data : listData) {
			RestaurantDTO restaurantDTO = new RestaurantDTO();
			restaurantDTO.setImage(data.getImage());
			restaurantDTO.setTitle(data.getTitle());
			restaurantDTO.setSubtitle(data.getSubtitle());
			restaurantDTO.setFreeship(data.isFreeship());
			restaurantDTO.setRating(averageRating(data.getListRatingRestaurant()));
			restaurantDTOs.add(restaurantDTO);
		}

		return restaurantDTOs;
	}

	private double averageRating(Set<RatingRestaurant> listRating) {
		double averagePoint = 0;
		for (RatingRestaurant data : listRating) {
			averagePoint += data.getRatingPoint();
		}

		return averagePoint / listRating.size();

	}

	@Override
	public RestaurantDTO getDetailRestaurantById(int id) {
		
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		RestaurantDTO restaurantDTO = new RestaurantDTO();
		if(restaurant.isPresent()) {
			
			List<CategoryDTO> categoryDTOs = new ArrayList<>();
			Restaurant data = restaurant.get();
			
			restaurantDTO.setImage(data.getImage());
			restaurantDTO.setTitle(data.getTitle());
			restaurantDTO.setSubtitle(data.getSubtitle());
			restaurantDTO.setDesc(data.getDescription());
			restaurantDTO.setAddress(data.getAddress());
			restaurantDTO.setRating(averageRating(data.getListRatingRestaurant()));
			restaurantDTO.setFreeship(data.isFreeship());
			restaurantDTO.setOpenDate(data.getOpenDate());
			
			for(MenuRestaurant menuRestaurant : data.getListMenuRestaurant()) {
				CategoryDTO categoryDTO = new CategoryDTO();
				List<FoodDTO> listFoodDTO = new ArrayList<>();

				categoryDTO.setName(menuRestaurant.getCategory().getCateName());
				for(Food food : menuRestaurant.getCategory().getListFood()) {

					FoodDTO foodDTO = new FoodDTO();
					foodDTO.setImage(food.getImage());
					foodDTO.setTitle(food.getTitle());;
					foodDTO.setFreeship(food.isFreeship());
					
					listFoodDTO.add(foodDTO);
				}
				categoryDTO.setMenus(listFoodDTO);
				categoryDTOs.add(categoryDTO);
			}
			restaurantDTO.setCategoryList(categoryDTOs);
		}
		return restaurantDTO;
	}

}
