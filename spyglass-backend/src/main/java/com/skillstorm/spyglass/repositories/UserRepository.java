package com.skillstorm.spyglass.repositories;

import org.springframework.data.repository.CrudRepository;

import com.skillstorm.spyglass.models.User;

public interface UserRepository extends CrudRepository<User, String> {

}
