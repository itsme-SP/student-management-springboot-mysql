package com.palle.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
		Set<SimpleGrantedAuthority> simpleGrantedAuthorities=new HashSet<>();
		simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRoles().name()));
		Set<SimpleGrantedAuthority> permissionsAuthorities= user.getRoles().getPermissions().stream()
				.map(permissions->new SimpleGrantedAuthority(permissions.name())).collect(Collectors.toSet());
		simpleGrantedAuthorities.addAll(permissionsAuthorities);
		return simpleGrantedAuthorities;
		
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
