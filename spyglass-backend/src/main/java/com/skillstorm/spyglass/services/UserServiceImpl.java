package com.skillstorm.spyglass.services;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillstorm.spyglass.models.Role;
import com.skillstorm.spyglass.models.User;
import com.skillstorm.spyglass.repositories.RoleRepository;
import com.skillstorm.spyglass.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private GoalService goalService;

	@Override
	public User findById(String name) {
		Optional<User> user = userRepository.findById(name);
		return user.isPresent()? user.get() : null;
	}

	@Override
	public User save(@Valid User user) {
		user = userRepository.save(user);
		Set<Role> roleList = user.getRoles();
		for (Role role : roleList) {
			role.setUser(user);
			roleRepository.save(role);
		}
		return user;
	}

	@Override
	public User update(@Valid User user, String name) {
		return userRepository.save(user);
	}

	@Override
	public void deleteById(String name) {
		Optional<User> user = this.userRepository.findById(name);
		if (user.isPresent()) { 
			roleRepository.deleteByUser(user.get()); // delete roles from authority table
		}
		
		goalService.deleteAllGoals(name); // then delete goals from goals table
		userRepository.deleteById(name); // then delete user
	}

}
