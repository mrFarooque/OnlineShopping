package com.masai.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.masai.dto.ProductDTO;
import com.masai.models.Category;
import com.masai.models.Product;
import com.masai.models.User;
import com.masai.repository.UserRepo;
import com.masai.service.CategoryService;
import com.masai.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired UserRepo userRepo;
	@Autowired CategoryService categoryService;
	@Autowired ProductService productService;
	
	public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	
	
	
	@GetMapping("/home")
	public String adminHome(Model model,Principal principal) {
		String email =principal.getName();
		User user = userRepo.findByEmail(email);
		model.addAttribute("user", user);
		return "adminHome";
	}
	
	@GetMapping("/categories")
	public String allCategories(Model model) {
		model.addAttribute("categories",categoryService.viewAllCategories());
		return "categories";
	}

	@GetMapping("/categories/add")
	public String addCategoryView(Model model) {
		model.addAttribute("category", new Category());
		return "categoryAdd";
	}

	@PostMapping("/categories/add")
	public String addCategory(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/categories/delete/{id}")
	public String removeCategory(@PathVariable Integer id) {
		categoryService.removeCategory(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/categories/update/{id}")
	public String updateCategory(@PathVariable Integer id, Model model) {
		Optional<Category> opt = categoryService.findById(id);
		if(opt.isPresent()) {
			model.addAttribute("category", opt.get());
			return "categoryAdd";
		}
		else return "404";
	}
	
	//product section
		@GetMapping("/products")
		public String productsView(Model model) {
			model.addAttribute("products", productService.viewAllProducts() );
			return "products";
		}
		
		@GetMapping("/products/add")
		public String productsAddView(Model model) {
			model.addAttribute("productDTO", new ProductDTO());
			model.addAttribute("categories", categoryService.viewAllCategories());
			return "productsAdd";
		}
		
		@PostMapping("/products/add")
		public String productAddPost(@ModelAttribute("productDTO") ProductDTO productDTO,
				@RequestParam("productImage") MultipartFile file,
				@RequestParam("imgName") String imgName) throws IOException 
		{
			Product product = new Product();
			product.setId(productDTO.getId());
			product.setName(productDTO.getName());
			product.setPrice(productDTO.getPrice());
			product.setQuantity(productDTO.getQuantity());
			product.setSpecification(productDTO.getSpecification());
			product.setColor(productDTO.getColor());
			product.setManufacturer(productDTO.getManufacturer());
			product.setDescription(productDTO.getDescription());
			product.setDimension(productDTO.getDimension());
			product.setCategory(categoryService.findById(productDTO.getCategoryId()).get());
			
			String imageUUID;
			if(!file.isEmpty()) {
				imageUUID = file.getOriginalFilename();
				Path fileNameAndPath = Paths.get(uploadDir,imageUUID);
				Files.write(fileNameAndPath,file.getBytes());
			}
			else {
				imageUUID = imgName;
			}
			product.setImageName(imageUUID);
			productService.addProduct(product);
			return "redirect:/admin/products";
		}
		
		@GetMapping("/product/delete/{id}")
		public String removeProduct(@PathVariable Integer id) {
			productService.removeProductById(id);
			return "redirect:/admin/products";
		}
		
		@GetMapping("/product/update/{id}")
		public String updateProduct(@PathVariable Integer id, Model model) {
			Product product = productService.getProductById(id);
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setQuantity(product.getQuantity());
			productDTO.setSpecification(product.getSpecification());
			productDTO.setColor(product.getColor());
			productDTO.setDescription(product.getDescription());
			productDTO.setDimension(product.getDimension());
			productDTO.setImageName(product.getImageName());
			productDTO.setManufacturer(product.getManufacturer());
			productDTO.setPrice(product.getPrice());
			productDTO.setCategoryId(product.getCategory().getId());
			
			model.addAttribute("productDTO", productDTO);
			model.addAttribute("categories", categoryService.viewAllCategories());
			
			return "productsAdd";
		}
	
	
	
	
}	
