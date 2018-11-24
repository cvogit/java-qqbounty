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
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Bounty;
import com.revature.services.BountyService;


	@RestController
	@RequestMapping(path = "bounties")
	public class BountyController {

		@Autowired
		private BountyService bs;

		@PostMapping
		public Bounty save(@RequestBody Bounty pBounty) {
			return bs.save(pBounty);
		}
		
		
		@GetMapping
		public List<Bounty> findAll() {
			return bs.findAll();
		}

		@GetMapping("{id}")
		public Bounty findById(@PathVariable int id) {
			return bs.findById(id);
		}

		@PutMapping
		public Bounty update(@Valid @RequestBody Bounty bounty) {
			return bs.save(bounty);
		}
	}

