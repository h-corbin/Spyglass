package com.skillstorm.spyglass.services;

import java.util.List;

import javax.validation.Valid;

import com.skillstorm.spyglass.models.Goal;

public interface GoalService {

	List<Goal> findAll(String user);

	Goal findbyId(int id, String name);

	Goal save(@Valid Goal goal, String string);

	Goal update(@Valid Goal goal, String name);

	void deleteById(int id, String name);

	void deleteAllGoals(String name);
	
	Goal addUser(int goalId, String username, String newUser);

}
