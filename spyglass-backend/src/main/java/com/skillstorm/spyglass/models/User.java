package com.skillstorm.spyglass.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
@Transactional
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
	private Set<Goal> goals = new HashSet<>();
	
	public void addGoal(Goal goal) {
		this.goals.add(goal);
	}
	
	@OneToMany(mappedBy="user")
    private Set<Role> roles;
	

	public User() {
		super();
	}

	public User(String username, @NotBlank String password, @NotNull boolean enabled, @NotBlank String firstName,
			@NotBlank String lastName, @NotBlank @Email String email, Set<Goal> goals) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.firstName = firstName;
		LastName = lastName;
		this.email = email;
		this.goals = goals;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Goal> getGoals() {
		return goals;
	}

	public void setGoals(Set<Goal> goals) {
		this.goals = goals;
	}
	
	
}
