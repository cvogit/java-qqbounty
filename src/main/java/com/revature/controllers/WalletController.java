package com.revature.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.JwtVerify;
import com.revature.models.Wallet;
import com.revature.services.WalletService;
import com.revature.util.ResponseMap;

@RestController
@RequestMapping(path = "wallets")
public class WalletController {

	@Autowired
	  private WalletService ws;
	
	//Get all wallets
	@GetMapping
	@JwtVerify
	public ResponseEntity<Map<String, Object>> findAll() {
	Map<String, Object> tResult = (Map<String, Object>) ws.findAll();
		if(tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
			return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}
			
	//Get users wallet balance
		@GetMapping("{id}")
		@JwtVerify
		public ResponseEntity<Map<String, Object>> findById(@PathVariable int id) {
			Map<String, Object> tResult = (Map<String, Object>) ws.findById(id);
			if(tResult == null) {
				return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
			}
			return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
		}
		
		//Get users wallet balance
		@PatchMapping
		@JwtVerify
		public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Wallet wallet) {
			Map<String, Object> tResult = (Map<String, Object>) ws.update(wallet);
				if(tResult == null) {
					return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
				}
					return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
			}
		}
