package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.UserProduct;

@Repository
public interface UserProductRepo extends JpaRepository<UserProduct, Integer> {

	@SuppressWarnings("unchecked")
	UserProduct save(UserProduct userProduct);
	
	UserProduct getOne(int id);
	
	List <UserProduct> findAll();
	
}