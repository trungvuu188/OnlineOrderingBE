package com.springboot.osahaneat.dto;

import java.util.List;

public class CategoryDTO {
	private String name;
	List<FoodDTO> menus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FoodDTO> getMenus() {
		return menus;
	}

	public void setMenus(List<FoodDTO> menus) {
		this.menus = menus;
	}
}
