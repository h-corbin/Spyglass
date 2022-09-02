package com.skillstorm.spyglass.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goals")
public class GoalController {

	@GetMapping
	public String getAllGoals() {
		return "list of goals";
	}
}
