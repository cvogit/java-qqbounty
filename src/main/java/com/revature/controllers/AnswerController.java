package com.revature.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.JwtVerify;
import com.revature.models.Answer;
import com.revature.models.Bounty;
import com.revature.services.AnswerService;
import com.revature.services.BountyService;
import com.revature.util.JwtUtil;
import com.revature.util.ResponseMap;

@RestController
@RequestMapping(path = "answers")
public class AnswerController {

	@Autowired
	private AnswerService as;
	
	@Autowired
	private BountyService bs;
	
	@Autowired
	private JwtUtil sJwtUtil;
		
	@GetMapping
	public ResponseEntity<Map<String, Object>> findAll() throws IOException {
		Map<String, Object> aResult = (Map<String, Object>) as.findAll();
		if(aResult == null) {
			System.out.println("No Answer List Found");
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("No answer list found"));
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(aResult));
	}
	
	

	@GetMapping("{id}")
	public ResponseEntity<Map<String, Object>> findById(@PathVariable int id) {
		
		Map<String, Object> aResult = (Map<String, Object>) as.findById(id);
		if(aResult == null) {
			System.out.println("Answer could not be found with id:" + id);
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Answer not found"));
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(aResult));
	}

	@PostMapping
    @JwtVerify
	public  ResponseEntity<Map<String, Object>> save(@RequestBody Answer pAnswer,HttpServletRequest req) throws IOException{
		
	  int userId = sJwtUtil.extractUserId(req);
	  if (userId == 0) {
	   	 System.out.println("Could not find user, check token.");
	     return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Token not valid."));
	  }
	  
	  Bounty bounty = bs.findById(pAnswer.getBountyId());
	  if (bounty == null) {
		 System.out.println("Bounty not found.");
		 return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Bounty not found."));
	  }
	  if (bounty.getStatusId() != 1) {
		  System.out.println("Bounty already answered or expired.");
			 return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Bounty already answered or expired."));
	  }
	  if (bounty.getUserId() == userId) {
		  System.out.println("Bounty User is same as Answer User");
			 return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Bounty User is same as Answer User"));
	  }
	  
	  
	  
	  pAnswer.setUserId(userId);
  
		Map<String, Object> aResult = (Map<String, Object>) as.save(pAnswer);
		if(aResult == null) {
			System.out.println("Answer could not be saved");
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Answer could not be saved"));
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(aResult));
	}
	
//	@PatchMapping
//	public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Answer pAnswer) {
//		Map<String, Object> aResult = (Map<String, Object>) as.update(pAnswer);
//		if(aResult == null) {
//			System.out.println("Answer could not be updated");
//			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
//		}
//		return ResponseEntity.ok().body(sResponseMap.getGoodResponse(aResult));
//	}
	
	@JwtVerify
	@PatchMapping("{id}")
	public ResponseEntity<Map<String, Object>> updateVote(@PathVariable int id, @RequestParam(value = "voteValue", required = true) int voteValue, HttpServletRequest req) throws IOException {
		int userId = sJwtUtil.extractUserId(req);
		
		Map<String, Object> aResult = (Map<String, Object>) as.updateVote(id,userId,voteValue);
		
		System.out.println("Vote request is " + (boolean) aResult.get("vote_status") );
		
		
		if((boolean) aResult.get("vote_status") == false) {
			System.out.println("Vote did not go through");
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Already Voted"));
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(aResult));
	}

}
