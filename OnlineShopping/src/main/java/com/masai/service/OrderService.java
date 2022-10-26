package com.masai.service;

import com.masai.models.OrderEntity;

public interface OrderService {
	OrderEntity saveBill(OrderEntity orderEntity);
	OrderEntity findByUser_id(Integer id);
}
