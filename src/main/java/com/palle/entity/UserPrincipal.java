package com.palle.entity;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{
	private Users user;
	
	public UserPrincipal(Users user) {
		this.user=user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //this method is used to set the roles of the individuals
		
		//return type is collection of granted authority type 
		//that is why we set singleton becuase we are passing only a single value
		return Collections.singleton(new SimpleGrantedAuthority("User")) ; 
		
	}

	@Override
	public @Nullable String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getName();
	}

}
