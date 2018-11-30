package com.revature.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.UserProduct;
import com.revature.repos.UserProductRepo;
import com.revature.util.ResponseMap;

@Service
public class UserProductService {

	@Autowired
	private UserProductRepo sUserProductRepo;
		
	public Map<String, Object> findAll() {
		return ResponseMap.getNewMap("user_product_list", sUserProductRepo.findAll());
	}
		
	//get user wallet balance
	public Map<String, Object>  findById(int id) {
		return ResponseMap.getNewMap("user_product", sUserProductRepo.findById(id));
	}
		
	public Map<String, Object> save(UserProduct userProduct) {
		
		return ResponseMap.getNewMap("user_product", sUserProductRepo.save(userProduct));
	}	
	
}
