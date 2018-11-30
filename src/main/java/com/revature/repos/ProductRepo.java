package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	Product save(int productId);
	
	Product getOne(int id);
	
	List <Product> findAll();
	
}