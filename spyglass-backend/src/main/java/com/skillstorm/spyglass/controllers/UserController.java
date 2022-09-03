package com.skillstorm.spyglass.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.spyglass.models.User;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

	@GetMapping("/{username}")
	public User getUser(@PathVariable String username) {
		User user = new User();
		user.setUsername(username);
		return user;
	}
}
