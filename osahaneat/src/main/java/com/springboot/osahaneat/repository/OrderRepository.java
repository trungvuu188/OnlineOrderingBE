package com.springboot.osahaneat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.osahaneat.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{

}
