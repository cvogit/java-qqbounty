package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Bounty;
import com.revature.repos.BountyRepo;

@Service
public class BountyService {

	@Autowired
	private BountyRepo bountyRepo;

	public List<Bounty> findAll() {
		return bountyRepo.findAll();
	}

	public Bounty findById(int id) {
		return bountyRepo.getOne(id);
	}
	
	public Bounty save(Bounty bounty) {
		return bountyRepo.save(bounty);
	}
}
