package com.skillstorm.spyglass.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.skillstorm.spyglass.models.Role;
import com.skillstorm.spyglass.models.User;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	List<Role> deleteByUser(User user);

}
