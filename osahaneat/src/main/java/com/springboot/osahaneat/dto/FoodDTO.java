package com.springboot.osahaneat.dto;

public class FoodDTO {
	private String title;
	private String image;
	private boolean isFreeship;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isFreeship() {
		return isFreeship;
	}

	public void setFreeship(boolean isFreeship) {
		this.isFreeship = isFreeship;
	}

}
