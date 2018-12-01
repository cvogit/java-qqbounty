package com.revature.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.JwtVerify;
import com.revature.services.UserProductService;
import com.revature.services.UserService;
import com.revature.services.WalletService;
import com.revature.util.ResponseMap;

@RestController
@RequestMapping(path = "userproducts")
public class UserProductController {

	@Autowired
	  private UserProductService ups;
	
	//Get all purchase history
	@GetMapping
	@JwtVerify
	public ResponseEntity<Map<String, Object>> findAll() {
	Map<String, Object> tResult = (Map<String, Object>) ups.findAll();
		if(tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
			return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}
			
	//Get user purchase history
		@GetMapping("{id}")
		@JwtVerify
		public ResponseEntity<Map<String, Object>> findById(@PathVariable int id) {
			Map<String, Object> tResult = (Map<String, Object>) ups.findById(id);
			if(tResult == null) {
				return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
			}
			return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
		}
		
}