package com.skillstorm.spyglass.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.spyglass.models.User;
import com.skillstorm.spyglass.services.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/authenticate")
	public Principal authenticate(Principal principal) {
		return principal;
	}

	@GetMapping("/{username}")
	public User getUser(Principal principal, @PathVariable String username) {
		return userService.findById(username, principal.getName());
	}
	
	@PostMapping
	public ResponseEntity<User> create(Principal principal, @Valid @RequestBody User user) {
		return new ResponseEntity<User>
		(userService.save(user, principal.getName()), HttpStatus.CREATED);
	}
	
	@PutMapping("/{username}")
	public User update(Principal principal, @Valid @RequestBody User user, @PathVariable String username) {
		user.setUsername(username);
		return userService.update(user, principal.getName());
	}
	
	@DeleteMapping("/{username}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(Principal principal, @PathVariable int username) {
		userService.deleteById(username, principal.getName());
	}
}
