package com.masai.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private LocalDate orderDate;
	private String orderStatus;
	private String firstName;
	private String LastName;
	private String email;
	private String Address;
	private String zip;
	private String town;
	private String phone;
	private Integer totalAmount;
	private Integer receiptNo;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<>();

}
