package com.revature.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.JwtUserIsAdmin;
import com.revature.annotations.JwtUserOwnBounty;
import com.revature.annotations.JwtVerify;
import com.revature.models.Answer;
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

	// check for logged in user here
	// will pass user id from extracted jwt here
	// add subjects after creating bounty
	@PostMapping
	@JwtVerify
	public ResponseEntity<Map<String, Object>> save(@RequestBody Bounty pBounty) {
		Map<String, Object> tResult = (Map<String, Object>) bs.save(pBounty);
		if (tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}

	// check that registered user is logged in here
	@GetMapping
	@JwtVerify
	public ResponseEntity<Map<String, Object>> findAll(Pageable pageable) {
		Map<String, Object> tResult = (Map<String, Object>) bs.findAll(pageable);
		if (tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}
	
	@GetMapping("subject")
	public ResponseEntity<Map<String, Object>> findAllBySubjectTags(Pageable pageable, @RequestParam(value = "subjects", required = true) String[] subjects) {
		
		List<String> subjectsList = Arrays.asList(subjects);
		 
		System.out.println(subjectsList);

		
		Map<String, Object> tResult = (Map<String, Object>) bs.findAllBySubjectTag(pageable,subjectsList);
		if (tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}
	
	

	@GetMapping("popular")
	public ResponseEntity<Map<String, Object>> findAllByOrderByVotes(Pageable pageable) {
		Map<String, Object> tResult = (Map<String, Object>) bs.findAllByOrderByVotes(pageable);
		if (tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}

	@GetMapping("cost")
	public ResponseEntity<Map<String, Object>> findAllByOrderByAmount(Pageable pageable) {
		Map<String, Object> tResult = (Map<String, Object>) bs.findAllByOrderByAmount(pageable);
		if (tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));

	}
	
	@GetMapping("newest")
	@JwtVerify
	public ResponseEntity<Map<String, Object>> findAllByOrderByNewest(Pageable pageable) {
		Map<String, Object> tResult = (Map<String, Object>) bs.findAllByOrderByNewest(pageable);
		if (tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}

// 	public ResponseEntity<Map<String, Object>> findAll(HttpServletRequest req) throws IOException {
// 		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(bs.findAll()));
//   }

	// check for logged in as admin for this
	@PatchMapping
	@JwtUserIsAdmin
	public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Bounty bounty) {
		Map<String, Object> tResult = (Map<String, Object>) bs.save(bounty);
		if (tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}

	// check for registered user is logged in here
	// get answers by bounty id
	@GetMapping("{id}/answers")
	@JwtVerify
	public ResponseEntity<Map<String, Object>> findByBountyid(@PathVariable int id, Pageable page) {
		Map<String, Object> tResult = (Map<String, Object>) as.findByBountyId(id,page);
		if (tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}
	
	
	// check for registered user is logged in here
		// get correct answer
		@PatchMapping("{bid}/answers/{aid}")
		@JwtUserOwnBounty
		@Transactional
		public ResponseEntity<Map<String, Object>> chooseBestAnswer(@PathVariable int bid,@PathVariable int aid) {
		    Bounty bounty = bs.findById(bid);
		    Answer answer = (Answer)as.findById(aid).get("answer");
			bounty.setCorrectAnswerId(aid); //Set correct answer to answer id.
			bounty.setStatusId(2); // Answer
			answer.setStatusId(3); // Best Answer
			Map<String, Object> tResult = (Map<String, Object>) bs.save(bounty);
		    if (tResult == null) {
				return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
			}
			return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
		}

}
