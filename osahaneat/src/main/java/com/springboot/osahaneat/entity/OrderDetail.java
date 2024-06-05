package com.springboot.osahaneat.entity;

import java.util.Date;

import com.springboot.osahaneat.entity.keys.KeyOrderDetail;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "order_detail")
public class OrderDetail {

	@EmbeddedId
	KeyOrderDetail keyOrderDetail;

	@ManyToOne
	@JoinColumn(name = "order_id", insertable = false, updatable = false)
	private Orders orders;
	
	@ManyToOne
	@JoinColumn(name = "food_id", insertable = false, updatable = false)
	private Food food;
	
	@Column(name = "create_date")
	private Date createDate;

	public KeyOrderDetail getKeyOrderDetail() {
		return keyOrderDetail;
	}

	public void setKeyOrderDetail(KeyOrderDetail keyOrderDetail) {
		this.keyOrderDetail = keyOrderDetail;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
