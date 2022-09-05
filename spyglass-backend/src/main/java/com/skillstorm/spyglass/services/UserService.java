package com.skillstorm.spyglass.services;

import javax.validation.Valid;

import com.skillstorm.spyglass.models.Goal;
import com.skillstorm.spyglass.models.User;

public interface UserService {

	User findById(String username, String name);

	User save(@Valid User user, String name);

	User update(@Valid User user, String name);

	void deleteById(int username, String name);

}
