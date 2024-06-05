package com.springboot.osahaneat.payload.request;

public class OrderRequest {
	private int userId;
	private int restaurantId;
	private int[] orderIds;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int[] getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(int[] orderIds) {
		this.orderIds = orderIds;
	}

}
