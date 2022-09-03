package com.skillstorm.spyglass.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.spyglass.models.Goal;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/goals")
public class GoalController {

	@GetMapping
	public Goal getAllGoals() {
		Goal goal = new Goal();
		goal.setName("new goal");
		return goal;
	}
}
