package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	@SuppressWarnings("unchecked")
	User save(User pUser);
	List<User> findAll();

	User getOne(Integer id);
	
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	User findByUsername(String pUsername);
//	User update(User pUser);
}
