package com.skillstorm.spyglass.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.spyglass.models.Goal;
import com.skillstorm.spyglass.models.User;
import com.skillstorm.spyglass.repositories.GoalRepository;
import com.skillstorm.spyglass.repositories.UserRepository;

@Service
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllGoals(String name) {
		// TODO Auto-generated method stub
		
	}

}
