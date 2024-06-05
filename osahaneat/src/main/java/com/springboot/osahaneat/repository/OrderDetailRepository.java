package com.springboot.osahaneat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.osahaneat.entity.OrderDetail;
import com.springboot.osahaneat.entity.keys.KeyOrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, KeyOrderDetail>{

}
