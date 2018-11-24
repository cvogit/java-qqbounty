package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Bounty;
import com.revature.repos.BountyRepo;
import com.revature.util.TsUtil;

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
	
	//set Timestamp upon new bounty creation
	public Bounty save(Bounty bounty) {
		bounty.setSumbitted(TsUtil.stampIt());
		return bountyRepo.save(bounty);
	}
	
}
	