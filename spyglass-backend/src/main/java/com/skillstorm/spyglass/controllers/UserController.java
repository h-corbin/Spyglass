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

	@GetMapping
	public UserDTO getUser(Principal principal) {
		User user = userService.findById(principal.getName());
		return UserDTO.convertToDTO(user);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> newUser(@Valid @RequestBody User user) {
		User newUser = userService.save(user);
		return newUser != null ? 
				new ResponseEntity<UserDTO>(UserDTO.convertToDTO(newUser), HttpStatus.CREATED)
				: new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public UserDTO update(Principal principal, @Valid @RequestBody User user) {
		user.setUsername(principal.getName());
		user = userService.update(user, principal.getName());
		return UserDTO.convertToDTO(user);
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteUser(Principal principal) {
		userService.deleteById(principal.getName());
	}
}
