package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.models.User;
import com.masai.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	@Autowired UserRepo userRepo;
	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}

}
