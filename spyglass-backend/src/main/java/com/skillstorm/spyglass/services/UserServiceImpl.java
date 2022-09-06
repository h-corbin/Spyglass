package com.skillstorm.spyglass.services;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.skillstorm.spyglass.models.User;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public User findById(String username, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(@Valid User user, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(@Valid User user, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int username, String name) {
		// TODO Auto-generated method stub
		
	}

}
