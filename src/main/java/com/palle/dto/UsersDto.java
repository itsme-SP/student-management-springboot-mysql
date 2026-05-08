package com.palle.dto;


import com.palle.entity.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsersDto {
	@NotBlank(message = "name cannot be blank")
	private String name;
	@Size(min=5,max = 12,message = "Password must be between 5 and 12 characters")
	private String password;
	@NotNull(message = "Role cannot be null")
	private Role role;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}

	

	public UsersDto(@NotBlank(message = "name cannot be blank") String name,
			@Size(min = 5, max = 12, message = "Password must be between 5 and 12 characters") String password,
			Role role) {
		super();
		this.name = name;
		this.password = password;
		this.role = role;
	}


	public UsersDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
