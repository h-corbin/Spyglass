package com.skillstorm.spyglass.services;

import javax.validation.Valid;

import com.skillstorm.spyglass.models.User;

public interface UserService {

	User findById(String username);

	User save(@Valid User user);

	User update(@Valid User user, String username);

	void deleteById(String username);

}
