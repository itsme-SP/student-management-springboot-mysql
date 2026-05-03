package com.palle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.palle.entity.Users;
import com.palle.repository.UsersRepository;

@Service
public class UserService {
	
	@Autowired
	public UsersRepository usersRepository;
	
	public Users register(Users user) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return  usersRepository.save(user);
	}
}
