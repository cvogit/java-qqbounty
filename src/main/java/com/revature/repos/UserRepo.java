package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	@SuppressWarnings("unchecked")
	User save(User pUser);
	List<User> findAll();

	User getOne(Integer id);
	
	User findByUsername(String pUsername);
	
	@Query(value = "SELECT username FROM User s where userId in :userId")
	List<String> findUsernames(@Param("userId") List<Integer> userIds);
	
	@Query(value = "SELECT u FROM User u where userId = :pId")
	User findUser(Integer pId);
}
