package com.palle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palle.entity.Users;
import com.palle.service.UserService;

@RestController
@RequestMapping("/user")
public class UsersController {
	@Autowired
	public UserService userService;
	
	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		return userService.register(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Users user) {
	    String result = userService.verify(user);

	    if(result.equals("Wrong Username Or Password")) {
	        return ResponseEntity.status(401).body(result);
	    }

	    return ResponseEntity.ok(result);
	}
}
