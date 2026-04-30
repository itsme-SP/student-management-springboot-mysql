package com.palle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.palle.entity.UserPrincipal;
import com.palle.entity.Users;
import com.palle.repository.UsersRepository;

@Service
public class StudentUserDetailsService implements UserDetailsService {
	@Autowired
	public UsersRepository usersRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user= usersRepository.findByName(username);
		if(user==null) {
			System.out.println("User Not found");
			throw new UsernameNotFoundException("User Not Found");
		}
		return new UserPrincipal(user);
	}

}
