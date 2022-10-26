package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.models.Cart;
import com.masai.models.Product;
import com.masai.repository.CartRepo;
import com.masai.repository.ProductRepo;
import com.masai.repository.UserRepo;

@Service
public class CartServiceImpl implements CartService{

	@Autowired CartRepo cartRepo;
	@Autowired ProductRepo productRepo;
	
	@Override
	public List<Product> cartAllProducts(Integer id) {
		Cart cart = cartRepo.findById(id).get();
		return cart.getProducts();
	}

	@Override
	public void addToCart(Integer productId, Integer userId) {
		 Cart cart = cartRepo.findByUser_id(userId);
		 Product product = productRepo.findById(productId).get();
		 cart.getProducts().add(product);
		cartRepo.save(cart);
	}

	@Override
	public void removeItem(Integer index,Cart cart) {
		List<Product> products = cart.getProducts();
		products.remove((int)index);
		cartRepo.save(cart);
	}

	@Override
	public void emptyCart(Cart cart) {
		List<Product> list = cart.getProducts();
		for(int i=0;i<list.size();i++) {
			list.remove(i);
		}
		cartRepo.save(cart);
	}

}

