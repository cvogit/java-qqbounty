package com.revature.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.JwtUserOwnBounty;
import com.revature.annotations.JwtVerify;
import com.revature.models.Answer;
import com.revature.services.AnswerService;
import com.revature.util.JwtUtil;
import com.revature.util.ResponseMap;

@RestController
@RequestMapping(path = "answers")
public class AnswerController {

	@Autowired
	private AnswerService as;
	
	@Autowired
	private JwtUtil sJwtUtil;

	@Autowired
	private ResponseMap sResponseMap;
		
		
	@GetMapping
	public ResponseEntity<Map<String, Object>> findAll(HttpServletRequest req) throws IOException {
		Map<String, Object> aResult = (Map<String, Object>) as.findAll();
		if(aResult == null) {
			System.out.println("No Answer List Found");
			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(sResponseMap.getGoodResponse(aResult));
	}
	
	

	@GetMapping("{id}")
	public ResponseEntity<Map<String, Object>> findById(@PathVariable int id) {
		
		Map<String, Object> aResult = (Map<String, Object>) as.findById(id);
		if(aResult == null) {
			System.out.println("Answer could not be found with id:" + id);
			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(sResponseMap.getGoodResponse(aResult));
	}

	@JwtUserOwnBounty
	@PostMapping
	public  ResponseEntity<Map<String, Object>> save(@RequestBody Answer pAnswer,HttpServletRequest req) throws IOException{
		
		int userId = sJwtUtil.extractUserId(req);
	    if (userId == 0) {
	    	System.out.println("Could not find user, check token.");
	    	return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
	    }
	    //pAnswer.setUserId(2);
		Map<String, Object> aResult = (Map<String, Object>) as.save(pAnswer);
		if(aResult == null) {
			System.out.println("Answer could not be saved");
			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(sResponseMap.getGoodResponse(aResult));
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
			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(sResponseMap.getGoodResponse(aResult));
	}

}
