package com.skillstorm.spyglass.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "goals")
public class Goal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int goalId;
	
	@NotBlank
	private String name;
	
	private String description;
	private String picture;
	
	@NotNull
	private LocalDate targetDate;
	
	@NotNull
	@DecimalMin(value = "0.0")
	@Digits(integer=10, fraction=2)
	private BigDecimal targetAmount;
	
	@NotNull
	@DecimalMin(value = "0.0")
	@Digits(integer=10, fraction=2)
	private double currentAmount;
	
	@ManyToMany(mappedBy = "goals")
	private Set<User> users;

	
	public Goal() {
		super();
	}

	public Goal(@NotBlank String name, String description, String picture, @NotNull LocalDate targetDate,
			@NotNull @DecimalMin("0.0") @Digits(integer = 10, fraction = 2) BigDecimal targetAmount,
			@NotNull @DecimalMin("0.0") @Digits(integer = 10, fraction = 2) double currentAmount, Set<User> users) {
		super();
		this.name = name;
		this.description = description;
		this.picture = picture;
		this.targetDate = targetDate;
		this.targetAmount = targetAmount;
		this.currentAmount = currentAmount;
		this.users = users;
	}

	public int getGoalId() {
		return goalId;
	}

	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public BigDecimal getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(BigDecimal targetAmount) {
		this.targetAmount = targetAmount;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
