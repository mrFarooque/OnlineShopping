package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Cart;
import com.masai.models.User;

public interface CartRepo extends JpaRepository<Cart, Integer>{
	Cart findByUser_id(Integer id);
}
