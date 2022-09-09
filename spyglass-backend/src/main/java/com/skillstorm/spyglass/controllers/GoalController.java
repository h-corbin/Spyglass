package com.skillstorm.spyglass.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skillstorm.spyglass.models.Goal;
import com.skillstorm.spyglass.services.GoalService;
import com.skillstorm.spyglass.services.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/goals")
@Tag(name = "Goals", description="All requests to this controller must contain the base64-encrypted username and password of the requesting user for Basic Authorization.")
public class GoalController {
	@Autowired
	private GoalService goalService;

	@GetMapping
	@Operation(summary = "Get all goals for the requesting user")
	public List<Goal> getAllGoals(Principal principal) {
		return goalService.findAll(principal.getName());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get goal with the specified id")
	public Goal getById(Principal principal, @PathVariable int id) {
		return goalService.findbyId(id, principal.getName());
	}
	
	@PostMapping
	@Operation(summary = "Create new goal for the requesting user")
	public ResponseEntity<Goal> create(Principal principal, @Valid @RequestBody Goal goal) {
		return new ResponseEntity<Goal>
		(goalService.save(goal, principal.getName()), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Update goal with the specified id")
	public Goal update(Principal principal, @Valid @RequestBody Goal goal, @PathVariable int id) {
		goal.setGoalId(id);
		return goalService.update(goal, principal.getName());
	}
	
	@PutMapping("/{id}/{username}")
	@Operation(summary = "Add an existing user as a partner for the specified goal")
	public ResponseEntity<Goal> newUser(Principal principal, @PathVariable int id, @PathVariable String username) {
		Goal goal = goalService.addUser(id, principal.getName(), username);
		return goal != null ? 
				new ResponseEntity<Goal>(goal, HttpStatus.CREATED)
				: new ResponseEntity<Goal>(goal, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Delete goal with the specified id")
	public void deleteById(Principal principal, @PathVariable int id) {
		goalService.deleteById(id, principal.getName());
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Delete all goals for the requesting user")
	public void deleteAllGoals(Principal principal) {
		goalService.deleteAllGoals(principal.getName());
	}
	
	@PostMapping("/image")
	@Operation(summary = "Upload an image to local disk")
    public void saveImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "../spyglass-frontend/src/assets/uploaded_photos";
        ImageService.save(uploadDir, fileName, multipartFile);
    }
	
	
}
