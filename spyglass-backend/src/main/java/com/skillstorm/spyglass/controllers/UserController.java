package com.skillstorm.spyglass.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.spyglass.models.User;
import com.skillstorm.spyglass.models.UserDTO;
import com.skillstorm.spyglass.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/authenticate")
	@Operation(summary = "Check that request Principal matches a user in the database")
	public Principal authenticate(Principal principal) {
		return principal;
	}

	@GetMapping
	@Operation(summary = "Get the user from request Principal")
	public UserDTO getUser(Principal principal) {
		User user = userService.findById(principal.getName());
		return UserDTO.convertToDTO(user);
	}
	
	@PostMapping
	@Operation(summary = "Create a new user in the database")
	public ResponseEntity<UserDTO> newUser(@Valid @RequestBody User user) {
		User newUser = userService.save(user);
		return newUser != null ? 
				new ResponseEntity<UserDTO>(UserDTO.convertToDTO(newUser), HttpStatus.CREATED)
				: new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	@Operation(summary = "Update the user")
	public UserDTO update(Principal principal, @Valid @RequestBody User user) {
		user.setUsername(principal.getName());
		user = userService.update(user, principal.getName());
		return UserDTO.convertToDTO(user);
	}
	
	@DeleteMapping
	@Operation(summary = "Delete user and their goals")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteUser(Principal principal) {
		userService.deleteById(principal.getName());
	}
}
