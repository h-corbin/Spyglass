package com.skillstorm.spyglass.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillstorm.spyglass.models.Goal;
import com.skillstorm.spyglass.models.User;
import com.skillstorm.spyglass.repositories.GoalRepository;
import com.skillstorm.spyglass.repositories.UserRepository;

@Service
@Transactional
public class GoalServiceImpl implements GoalService {
	
	@Autowired
	private GoalRepository goalRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Goal> findAll(String username) {
		User user = new User();
		user.setUsername(username);
		return (List<Goal>) goalRepository.findByUsers(user);
	}

	@Override
	public Goal findbyId(int id, String name) {
		return goalRepository.findByIdAndUser(id, name);
	}

	@Override
	public Goal save(@Valid Goal goal, String name) {
		Optional<User> user = this.userRepository.findById(name);
		if (user.isPresent()) {
			user.get().addGoal(goal);
			goal.addUser(user.get());;
			return goalRepository.save(goal);
		}
		return null;
	}

	@Override
	public Goal update(@Valid Goal goal, String name) {
		Goal oldGoal = this.findbyId(goal.getGoalId(), name); // verify user owns this goal
		if (oldGoal != null) {
			return goalRepository.save(goal);
		}
		return null;
	}
	
	@Override
	public Goal addUser(int goalId, String name, String newUser) {
		Goal goal = this.findbyId(goalId, name);
		if (goal != null) {
			Optional<User> user = this.userRepository.findById(newUser);
			if (user.isPresent()) {
				user.get().addGoal(goal);
				goal.addUser(user.get());
				return goalRepository.save(goal);
			}
		}
		return null;
	}

	@Override
	public void deleteById(int id, String name) {
		Goal goal = this.findbyId(id, name);
		if (goal != null) {
			Set<User> userList = goal.getUsers();
			int numUsers = userList.size();
			Optional<User> user = this.userRepository.findById(name);
			if (user.isPresent()) { 
				// remove from join table
				user.get().getGoals().remove(goal);
				userList.remove(user.get());
				userRepository.save(user.get());
				goalRepository.save(goal);
			}
			
			if (numUsers == 1) { 
				goalRepository.delete(goal);
			}
		}
	}

	@Override
	public void deleteAllGoals(String name) {
		Optional<User> user = this.userRepository.findById(name);
		if (user.isPresent()) { 
			Set<Goal> goalList = new HashSet<>(user.get().getGoals()); 
			for (Goal goal : goalList) {
				this.deleteById(goal.getGoalId(), name);
			}
		}
	}

}
