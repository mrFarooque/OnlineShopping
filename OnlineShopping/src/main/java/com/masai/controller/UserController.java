package com.masai.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.masai.models.Cart;
import com.masai.models.OrderEntity;
import com.masai.models.Product;
import com.masai.models.User;
import com.masai.repository.CartRepo;
import com.masai.repository.OrderRepo;
import com.masai.repository.UserRepo;
import com.masai.service.CartService;
import com.masai.service.CategoryService;
import com.masai.service.OrderService;
import com.masai.service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired UserRepo userRepo;
	@Autowired CartRepo cartRepo;
	@Autowired CategoryService categoryService;
	@Autowired ProductService productService;
	@Autowired CartService cartService;
	@Autowired OrderService orderService;

	@GetMapping("/home")
	public String home(Model model,Principal principal) {
		String email =principal.getName();
		User user = userRepo.findByEmail(email);
		model.addAttribute("user", user);
		return "home";
	}

	@GetMapping("/shop")
	public String shopPage(Model model) {
		model.addAttribute("categories", categoryService.viewAllCategories());
		model.addAttribute("products", productService.viewAllProducts());
		return "shop";
	}

	@GetMapping("/shop/category/{id}")
	public String shopByCategory(@PathVariable Integer id,Model model) {
		model.addAttribute("categories", categoryService.viewAllCategories());
		model.addAttribute("products", productService.getAllProductByCategoryId(id));
		return "shop";
	}

	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable Integer id,Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "viewProduct";
	}


	//cart section

	@GetMapping("/cart")
	public String viewCart(Model model,Principal principal) {
		List<Product> products = cartProducts(principal);
		model.addAttribute("cart", products);
		model.addAttribute("cartCount", products.size());
		model.addAttribute("total",totalAmount(principal));
		return "cart";
	}

	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable Integer id,Principal principal) {
		String email =principal.getName();
		User user = userRepo.findByEmail(email);
		cartService.addToCart(id,user.getId());
		return "redirect:/user/shop?success";
	}
	
	@GetMapping("/cart/removeItem/{index}")
	public String removeCartItem(@PathVariable Integer index,Principal principal) {
		String email =principal.getName();
		User user = userRepo.findByEmail(email);
		Cart cart = cartRepo.findByUser_id(user.getId());
		cartService.removeItem(index,cart);
		return "redirect:/user/cart";
	}

	@GetMapping("/cart/checkout")
	public String checkout(Model model,Principal principal) {
		
		model.addAttribute("total",totalAmount(principal));
		model.addAttribute("orderEntity", new OrderEntity());
		return "checkout";
	}
	
	@PostMapping("/billing")
	public String biling(@ModelAttribute("orderEntity") OrderEntity orderEntity,Principal principal) {
		List<Product> cartProducts = cartProducts(principal);
		List<Product> orderProducts = orderEntity.getProducts(); 
		
		for(Product prod:cartProducts) {
			orderProducts.add(prod);
		}
		
		orderEntity.setTotalAmount(totalAmount(principal));
		orderEntity.setUser(getUser(principal));
		orderService.saveBill(orderEntity);
		
		return "redirect:/user/receipt";
	}
	
	@GetMapping("/receipt")
	public String receipt(Model model,Principal p) {
		model.addAttribute("products", cartProducts(p));
		model.addAttribute("total", totalAmount(p));
		OrderEntity entity = findOrderByUser(p);
		model.addAttribute("order", entity);
		return "receipt";
	}
	
	
	
//	UTILITY METHODS
	public int totalAmount(Principal principal) {
		String email =principal.getName();
		User user = userRepo.findByEmail(email);
		Cart cart = cartRepo.findByUser_id(user.getId());

		List<Product> products = cart.getProducts();
		int total = 0;
		for(Product product : products) {
			total += product.getPrice();
		}
		return total;
	}
	
	public List<Product> cartProducts(Principal principal) {
		String email =principal.getName();
		User user = userRepo.findByEmail(email);
		Cart cart = cartRepo.findByUser_id(user.getId());

		return cart.getProducts();
	}
	public Cart getUserCart(Principal principal) {
		String email =principal.getName();
		User user = userRepo.findByEmail(email);
		return cartRepo.findByUser_id(user.getId());

	}
	public User getUser(Principal principal) {
		String email =principal.getName();
		return userRepo.findByEmail(email);
	}
	public OrderEntity findOrderByUser(Principal p) {
		Integer id = getUser(p).getId();
		return orderService.findByUser_id(id);
	}
}
