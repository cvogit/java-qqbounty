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
	public ResponseEntity<Map<String, Object>> save(@RequestBody Bounty pBounty) {
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(bs.save(pBounty)));
	}
	
	//check that registered user is logged in here
	@SuppressWarnings("unused")
	@GetMapping
	@JwtVerify //this is not working!
	public Page<Bounty> findAll(Pageable pageable){
		return bs.findAll(pageable);
  }
// 	public ResponseEntity<Map<String, Object>> findAll(HttpServletRequest req) throws IOException {
// 		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(bs.findAll()));
//   }
		
	//check for logged in as admin for this	
	@PatchMapping
	public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Bounty bounty) {
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(bs.save(bounty)));
	}
	
	//check for registered user is logged in here
	//get answers by bounty id
	@GetMapping("{id}/answers")
	public ResponseEntity<Map<String, Object>> findByBountyid(@PathVariable int id) { 
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(as.findByBountyId(id)));
	}
	
	//not sure when to use this...
	@GetMapping("{id}")
	public ResponseEntity<Map<String, Object>> findById(@PathVariable int id) {
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(bs.findById(id)));
	}
}
