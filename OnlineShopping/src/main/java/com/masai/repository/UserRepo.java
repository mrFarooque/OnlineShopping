package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
