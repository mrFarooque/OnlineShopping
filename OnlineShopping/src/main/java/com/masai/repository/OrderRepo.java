package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Orders;

public interface OrderRepo extends JpaRepository<Orders, Integer>{

	Orders findByUser_id(Integer id);
}
