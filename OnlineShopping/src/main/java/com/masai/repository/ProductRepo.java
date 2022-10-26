package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	List<Product> findAllByCategory_id(Integer id);
}	
