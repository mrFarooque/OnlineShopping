package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.OrderEntity;

public interface OrderRepo extends JpaRepository<OrderEntity, Integer>{

	OrderEntity findByUser_id(Integer id);
}
