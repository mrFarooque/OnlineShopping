package com.masai.service;

import com.masai.models.Orders;

public interface OrderService {
	Orders saveBill(Orders orderEntity);
	Orders findByUser_id(Integer id);
}
