package com.skillstorm.spyglass.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.skillstorm.spyglass.models.Goal;
import com.skillstorm.spyglass.models.User;

public interface GoalRepository extends CrudRepository<Goal, Integer>{

//	@Query(
//			value = "SELECT * FROM goals g INNER JOIN goals_per_user gpu ON g.goal_id = gpu.goal_id"
//					+ "WHERE gpu.username = :username",
//			nativeQuery=true
//			)
	List<Goal> findByUsers(User user);

	@Query(value = "SELECT * FROM goals g INNER JOIN goals_per_user gpu "
					+ "ON g.goal_id = gpu.goal_id "
					+ "WHERE gpu.username = :username "
					+ "AND g.goal_id = :id",
			nativeQuery=true)
	Goal findByIdAndUser(int id, String username);
}
