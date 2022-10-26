package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.models.Product;
import com.masai.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired private ProductRepo productRepo;

	@Override
	public List<Product> viewAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Product removeProductById(int id) {
		Optional<Product> opt = productRepo.findById(id);
		if(opt.isPresent()) {
			Product product = opt.get();
			productRepo.delete(product);
			return product;
		}
		else return null;
	}

	@Override
	public Product getProductById(int id) {
		Optional<Product> opt = productRepo.findById(id);
		return opt.get();
	}

	@Override
	public List<Product> getAllProductByCategoryId(int id) {
		return productRepo.findAllByCategory_id(id);
	}
	
}
