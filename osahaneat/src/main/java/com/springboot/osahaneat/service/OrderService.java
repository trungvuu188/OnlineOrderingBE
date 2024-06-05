package com.springboot.osahaneat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.osahaneat.entity.Food;
import com.springboot.osahaneat.entity.OrderDetail;
import com.springboot.osahaneat.entity.Orders;
import com.springboot.osahaneat.entity.Restaurant;
import com.springboot.osahaneat.entity.Users;
import com.springboot.osahaneat.entity.keys.KeyOrderDetail;
import com.springboot.osahaneat.payload.request.OrderRequest;
import com.springboot.osahaneat.repository.OrderDetailRepository;
import com.springboot.osahaneat.repository.OrderRepository;
import com.springboot.osahaneat.service.imp.OrderServiceImp;

import jakarta.transaction.Transactional;

@Service
public class OrderService implements OrderServiceImp {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Override
	public boolean insertOrder(OrderRequest orderRequest) {

		try {
			Users users = new Users();
			users.setId(orderRequest.getUserId());

			Restaurant restaurant = new Restaurant();
			restaurant.setId(orderRequest.getRestaurantId());

			Orders orders = new Orders();
			orders.setUsers(users);
			orders.setRestaurant(restaurant);
			orderRepository.save(orders);

			List<OrderDetail> listOrder = new ArrayList<>();
			for (int foodId : orderRequest.getOrderIds()) {
				Food food = new Food();
				food.setId(foodId);

				KeyOrderDetail keyOrderDetail = new KeyOrderDetail();
				keyOrderDetail.setFoodId(foodId);
				keyOrderDetail.setOrderId(orders.getId());
				
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setFood(food);
				orderDetail.setOrders(orders);
				orderDetail.setKeyOrderDetail(keyOrderDetail);

				listOrder.add(orderDetail);
			}

			orderDetailRepository.saveAll(listOrder);
			return true;

		} catch (Exception e) {
			System.out.println("Error insert order" + e.getMessage());
			return false;
		}
	}
}
