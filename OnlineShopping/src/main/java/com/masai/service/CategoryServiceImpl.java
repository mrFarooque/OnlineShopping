package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.models.Category;
import com.masai.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired private CategoryRepo categoryRepo;
	@Override
	public Category addCategory(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public List<Category> viewAllCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public void removeCategory(Integer id) {
		categoryRepo.deleteById(id);
	}

	@Override
	public void updateCategory(Category category) {
		categoryRepo.save(category);
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryRepo.findById(id);
	}

}
