package com.springboot.osahaneat.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_name")
	private String username;
	
	@Column(name = "pass_word")
	private String password;

	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Roles roles;
	
	@OneToMany(mappedBy = "users")
	private Set<RatingFood> listRatingFood;
	
	@OneToMany(mappedBy = "users")
	private Set<RatingRestaurant> listRatingRestaurant;
	
	@OneToMany(mappedBy = "users")
	private Set<Orders> listOrder;
	

	public Set<Orders> getListOrder() {
		return listOrder;
	}

	public void setListOrder(Set<Orders> listOrder) {
		this.listOrder = listOrder;
	}

	public Set<RatingFood> getListRatingFood() {
		return listRatingFood;
	}

	public Set<RatingRestaurant> getListRatingRestaurant() {
		return listRatingRestaurant;
	}

	public void setListRatingRestaurant(Set<RatingRestaurant> listRatingRestaurant) {
		this.listRatingRestaurant = listRatingRestaurant;
	}

	public void setListRatingFood(Set<RatingFood> listRatingFood) {
		this.listRatingFood = listRatingFood;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	
	
}
