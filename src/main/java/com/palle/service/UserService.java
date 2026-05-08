package com.palle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.palle.dto.UsersDto;
import com.palle.entity.Users;
import com.palle.repository.UsersRepository;

@Service
public class UserService {
	
	@Autowired
	public UsersRepository usersRepository;
	
	@Autowired
	public AuthenticationManager authenticationManager; 
	
	@Autowired
	public JwtService jwtService;
	
	public Users register(UsersDto userDto) {
		Users user= new Users();
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setRoles(userDto.getRole());
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return  usersRepository.save(user);
	}
	
	public String verify(UsersDto userDto) {
		Users user=new Users();
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		try {
		Authentication authentication= authenticationManager
				.authenticate
				(new UsernamePasswordAuthenticationToken
						(user.getName(), user.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getName());
		}
		}
		catch(Exception e) {
		return "Wrong Username Or Password";
		}
		return "Login Failed";
	}
}
