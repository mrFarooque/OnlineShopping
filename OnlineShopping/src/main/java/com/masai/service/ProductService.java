package com.masai.service;

import java.util.List;

import com.masai.models.Product;

public interface ProductService {
	List<Product> viewAllProducts();
	Product addProduct(Product product);
	Product removeProductById(int id);
	Product getProductById(int id);
	List<Product> getAllProductByCategoryId(int id);
}
