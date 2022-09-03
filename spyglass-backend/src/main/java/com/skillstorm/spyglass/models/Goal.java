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
	private Long goalId;
	
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
}
