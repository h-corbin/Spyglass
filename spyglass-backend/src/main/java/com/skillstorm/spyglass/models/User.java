package com.skillstorm.spyglass.models;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	private String username;
	
	@NotBlank
	private String password;
	
	@NotNull
	private boolean enabled;
	
	@NotBlank
	private String firstName;

	@NotBlank
	private String LastName;
	
	@NotBlank
	@Email
	private String email;
	
	@ManyToMany
	@JoinTable(
			  name = "goals_per_user", 
			  joinColumns = @JoinColumn(name = "username"), 
			  inverseJoinColumns = @JoinColumn(name = "goal_id"))
	private Set<Goal> goals;
}
