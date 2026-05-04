package com.palle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.palle.entity.Users;
import com.palle.repository.UsersRepository;

@Service
public class UserService {
	
	@Autowired
	public UsersRepository usersRepository;
	
	@Autowired
	public AuthenticationManager authenticationManager; 
	
	public Users register(Users user) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return  usersRepository.save(user);
	}
	
	public String verify(Users user) {
		try {
		Authentication authentication= authenticationManager
				.authenticate
				(new UsernamePasswordAuthenticationToken
						(user.getName(), user.getPassword()));
		if(authentication.isAuthenticated()) {
			return "Authentication Successful";
		}
		}
		catch(Exception e) {
		return "Wrong Username Or Password";
		}
		return "Login Failed";
	}
}
