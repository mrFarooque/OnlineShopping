package com.masai.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
public class ProductDTO {
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Integer price;
	private String color;
	private String dimension;
	private String specification;
	private String manufacturer;
	private Integer quantity;
	private String description;
	private String imageName;
	
	private Integer categoryId;
}
