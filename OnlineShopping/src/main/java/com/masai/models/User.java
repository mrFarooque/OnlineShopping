package com.masai.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(unique = true)
	@Email
	private String email;
	private String password;
	private String role;
	@Size(min = 3,max = 12,message = "size should be greater than 3 and less than 12")
	private String firstName;
	private String LastName;
	
}
