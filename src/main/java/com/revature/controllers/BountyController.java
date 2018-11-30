package com.revature.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.JwtUserIsAdmin;
import com.revature.annotations.JwtUserIsSelf;
import com.revature.annotations.JwtVerify;
import com.revature.models.Bounty;
import com.revature.services.AnswerService;
import com.revature.services.BountyService;
import com.revature.util.ResponseMap;


@RestController
@RequestMapping(path = "bounties")
public class BountyController {
		
  @Autowired
  private BountyService bs;

  @Autowired
  private AnswerService as;

	//check for logged in user here
	//will pass user id from extracted jwt here
	//add subjects after creating bounty
	@PostMapping
	@JwtUserIsSelf
	public ResponseEntity<Map<String, Object>> save(@RequestBody Bounty pBounty) {
		Map<String, Object> tResult = (Map<String, Object>) bs.save(pBounty);
		if(tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}
	
	//check that registered user is logged in here
	@GetMapping
	@JwtVerify
	public ResponseEntity<Map<String, Object>> findAll(Pageable pageable){
		Map<String, Object> tResult = (Map<String, Object>) bs.findAll(pageable);
		if(tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
  }

		
	//check for logged in as admin for this	
	@PatchMapping
	@JwtUserIsAdmin
	public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Bounty bounty) {
		Map<String, Object> tResult = (Map<String, Object>) bs.save(bounty);
		if(tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}
	
	//check for registered user is logged in here
	//get answers by bounty id
	@GetMapping("{id}/answers")
	@JwtVerify
	public ResponseEntity<Map<String, Object>> findByBountyid(@PathVariable int id) { 
		Map<String, Object> tResult = (Map<String, Object>) as.findByBountyId(id);
		if(tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}
	
	//not sure when to use this...
	@GetMapping("{id}")
	@JwtVerify
	public ResponseEntity<Map<String, Object>> findById(@PathVariable int id) {
		Map<String, Object> tResult = (Map<String, Object>) bs.findById(id);
		if(tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}
}
