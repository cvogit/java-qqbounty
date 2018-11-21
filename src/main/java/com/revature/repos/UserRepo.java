package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User save(User pUser);
	List<User> findAll();
}
