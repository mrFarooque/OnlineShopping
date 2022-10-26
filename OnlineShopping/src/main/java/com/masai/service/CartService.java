package com.masai.service;

import java.util.List;

import com.masai.models.Cart;
import com.masai.models.Product;

public interface CartService {
	List<Product> cartAllProducts(Integer id);
	void addToCart(Integer productId,Integer userId);
	void removeItem(Integer index,Cart cart);
	void emptyCart(Cart cart);
}
