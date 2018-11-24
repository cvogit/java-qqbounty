package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Bounty;
import com.revature.services.BountyService;
import com.revature.util.ResponseMap;

@RestController
@RequestMapping(path = "bounties")
public class BountyController {
	@Autowired
	private BountyService sBountyService;
	
	@Autowired
	private ResponseMap sResponseMap;

	@PostMapping
	public Bounty save(@RequestBody Bounty pBounty) {
		return sBountyService.save(pBounty);
	}
	
	
	@GetMapping
	@ResponseBody
	public List<Bounty> findAll() {
		return sBountyService.findAll();
		
//		// Set response map fields
//		sResponseMap.setMessage("Success");
//		sResponseMap.setResult(tUserList);
//		return sResponseMap.getResponse();
	}

	@GetMapping("{id}")
	public Bounty findById(@PathVariable int id) {
		return sBountyService.getOne(id);
	}

}
