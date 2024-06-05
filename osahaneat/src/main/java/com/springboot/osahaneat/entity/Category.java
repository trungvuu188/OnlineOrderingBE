package com.springboot.osahaneat.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "cate_name")
	private String cateName;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@OneToMany(mappedBy = "category")
	private Set<Food> listFood;

	@OneToMany(mappedBy = "category")
	private Set<MenuRestaurant> listMenuRestaurant;
	
	public Set<MenuRestaurant> getListMenuRestaurant() {
		return listMenuRestaurant;
	}

	public void setListMenuRestaurant(Set<MenuRestaurant> listMenuRestaurant) {
		this.listMenuRestaurant = listMenuRestaurant;
	}

	public Set<Food> getListFood() {
		return listFood;
	}

	public void setListFood(Set<Food> listFood) {
		this.listFood = listFood;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
