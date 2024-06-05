package com.springboot.osahaneat.service.imp;

import org.springframework.web.multipart.MultipartFile;

public interface FoodServiceImp {

	boolean createMenu(MultipartFile file, String title, boolean isFreeship, String timeShip, double price, int cateId);
}
