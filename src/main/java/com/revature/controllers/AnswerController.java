package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Answer;
import com.revature.services.AnswerService;
import com.revature.util.JwtUtil;

@RestController
@RequestMapping(path = "answers")
public class AnswerController {

	@Autowired
	private AnswerService as;
	
	@Autowired
	private JwtUtil sJwtUtil;

	@PostMapping
	public Answer save(@RequestBody Answer pAnswer) {
		return as.save(pAnswer);
	}
	
	@GetMapping
	public List<Answer> findAll() {
		return as.findAll();
	}

	@GetMapping("{id}")
	public Answer findById(@PathVariable int id) {
		return as.findById(id);
	}

	@PutMapping
	public Answer update(@Valid @RequestBody Answer pAnswer) {
		return as.update(pAnswer);
	}
	

	@PatchMapping("{id}")
	public Boolean updateVote(@PathVariable int id, @RequestParam(value = "voteValue", required = true) int voteValue, HttpServletRequest req) throws IOException {
//			if(!JwtUtil.jwtVerify(req)) {
//				System.out.println("User not verified");
//				return false;
//			}
		
		int userId = sJwtUtil.extractUserId(req);
	
		return as.updateVote(id,userId,voteValue);
	}

}
