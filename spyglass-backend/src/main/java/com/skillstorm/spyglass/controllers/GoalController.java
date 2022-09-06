package com.skillstorm.spyglass.controllers;

import java.security.Principal;
import java.util.List;

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

import com.skillstorm.spyglass.models.Goal;
import com.skillstorm.spyglass.services.GoalService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/goals")
public class GoalController {
	@Autowired
	private GoalService goalService;

	@GetMapping
	public List<Goal> getAllGoals(Principal principal) {
		return goalService.findAll(principal.getName());
	}
	
	@GetMapping("/{id}")
	public Goal getById(Principal principal, @PathVariable int id) {
		return goalService.findbyId(id, principal.getName());
	}
	
	@PostMapping
	public ResponseEntity<Goal> create(Principal principal, @Valid @RequestBody Goal goal) {
		return new ResponseEntity<Goal>
		(goalService.save(goal, principal.getName()), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public Goal update(Principal principal, @Valid @RequestBody Goal goal, @PathVariable int id) {
		goal.setGoalId(id);
		return goalService.update(goal, principal.getName());
	}
	
	@PutMapping("/{id}/{username}")
	public Goal newUser(Principal principal, @PathVariable int id, @PathVariable String username) {
		return goalService.addUser(id, principal.getName(), username);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(Principal principal, @PathVariable int id) {
		goalService.deleteById(id, principal.getName());
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteAllGoals(Principal principal) {
		goalService.deleteAllGoals(principal.getName());
	}
}
