package com.skillstorm.spyglass.models;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for http responses")
public class UserDTO {
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	
	

	public static UserDTO convertToDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setUsername(user.getUsername());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setEmail(user.getEmail());
		return dto;
	}

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
