package com.palle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palle.dto.UsersDto;
import com.palle.entity.Users;
import com.palle.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UsersController {
	@Autowired
	public UserService userService;
	
	@PostMapping("/register")
	public Users register(@Valid @RequestBody UsersDto userDto) {
		return userService.register(userDto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody  UsersDto userDto) {
	    String result = userService.verify(userDto);

	    if(result.equals("Wrong Username Or Password")) {
	        return ResponseEntity.status(401).body(result);
	    }

	    return ResponseEntity.ok(result);
	}
}
