package com.revature.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Answer;
import com.revature.models.Bounty;
import com.revature.services.AnswerService;
import com.revature.services.BountyService;
import com.revature.util.JwtUtil;
import com.revature.util.ResponseMap;

@RestController
@RequestMapping(path = "bounties")
public class BountyController {

	@Autowired
	private ResponseMap sResponseMap;
	
	@Autowired
	private BountyService sBountyService;
	
	@Autowired
	private AnswerService sAnswerService;
	
	@Autowired
	private JwtUtil sJwtUtil;
	
	@PostMapping
	public Bounty save(@RequestBody Bounty pBounty) {
		return sBountyService.save(pBounty);
	}
	
	@PostMapping("{bountyId}/answers")
	public ResponseEntity<Map<String, Object>> chooseAnswer(@PathVariable int bountyId, @RequestParam(value = "answer_id", required = true) Integer pAnswerId, HttpServletRequest req) throws IOException {
		if(!sJwtUtil.isBountyOwner(req, bountyId)) {
			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
		}
		Map<String, Object> tServiceResult = (Map<String, Object>) sBountyService.update(bountyId, pAnswerId);
		if(tServiceResult == null) {
			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(sResponseMap.getGoodResponse(tServiceResult));
	}
	
	@GetMapping
	public List<Bounty> findAll() {
		return sBountyService.findAll();
	}

	@PutMapping
	public Bounty update(@Valid @RequestBody Bounty bounty) {
		return sBountyService.save(bounty);
	}
	
	//get answers by bounty id
	@GetMapping("{id}/answers")
	public List<Answer> findByBountyid(@PathVariable int id) {
		System.out.println("here"); 
		return sAnswerService.findByBountyId(id);
	}
	

	@GetMapping("{id}")
	public Bounty findById(@PathVariable int id) {
		return sBountyService.findById(id);
	}
}
