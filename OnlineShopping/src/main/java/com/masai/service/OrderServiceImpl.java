package com.masai.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.models.Orders;
import com.masai.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired OrderRepo orderRepo;

	@Override
	public Orders saveBill(Orders orderEntity) {
		orderEntity.setOrderDate(LocalDate.now());
		orderEntity.setOrderStatus("processing");
		orderEntity.setReceiptNo((int)Math.floor(10000 + Math.random() * 90000));
		
		//cart should be empty now
		
		
		return orderRepo.save(orderEntity);
	}

	@Override
	public Orders findByUser_id(Integer id) {
		return orderRepo.findByUser_id(id);
	}

}
