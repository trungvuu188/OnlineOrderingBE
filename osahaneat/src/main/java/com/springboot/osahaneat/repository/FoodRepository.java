package com.springboot.osahaneat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.osahaneat.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

}
