package com.masai.service;

import java.util.List;
import java.util.Optional;

import com.masai.models.Category;

public interface CategoryService {
	Category addCategory(Category category);
	List<Category> viewAllCategories();
	void removeCategory(Integer id);
	void updateCategory(Category category);
	Optional<Category> findById(Integer id);

}
