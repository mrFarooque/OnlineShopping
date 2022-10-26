package com.masai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.masai.models.User;
import com.masai.repository.UserRepo;

public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("user does not exits");
		}
		CustomUserDetails userDetails = new CustomUserDetails(user);
		return userDetails;
	}

}
