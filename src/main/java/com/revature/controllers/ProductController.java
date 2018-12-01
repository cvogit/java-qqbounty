package com.revature.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.JwtVerify;
import com.revature.services.ProductService;
import com.revature.util.ResponseMap;

@RestController
@RequestMapping(path = "products")
public class ProductController {

	@Autowired
	  private ProductService ps;
	
	//Get all products
	@GetMapping
	@JwtVerify
	public ResponseEntity<Map<String, Object>> findAll() {
	Map<String, Object> tResult = (Map<String, Object>) ps.findAll();
		if(tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
			return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}
			
	//Get a product
		@GetMapping("{id}")
		@JwtVerify
		public ResponseEntity<Map<String, Object>> findById(@PathVariable int id) {
			Map<String, Object> tResult = (Map<String, Object>) ps.findById(id);
			if(tResult == null) {
				return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
			}
			return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
		}
		
		
		@PostMapping("{productId}")
		@JwtVerify
		public ResponseEntity<Map<String, Object>> save(@PathVariable int productId, HttpServletRequest req) {
			
			Map<String, Object> tResult = (Map<String, Object>) ps.save(productId, req);
			if (tResult == null) {
				return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
			}
			
			return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
		}
		
}