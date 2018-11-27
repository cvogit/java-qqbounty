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
	
  
  /*  EXAMPLE SAVE/UPDATE REQUEST 
		 * {
         *   "amount": 150, 	     	any-amount
         *   "description": "string",   any-string
         *   "picture": "string",       any-string
         *    "submitted": 0,           optional, auto sets to current date
         *    "status_id": 1,           Set to one
         *    "subject_id": 1,          Testing junction table 11/26 @ 2108
         *    "timer": 0,               any-int
         *    "user_id": 1,            valid user id 
         *    "votes": 0                Set to 0
         * }
		 */
	//user id will be set in controller when verifying jwt
	//set Timestamp upon new bounty creation
	public Bounty save(Bounty bounty) {
		bounty.setSubmitted(TsUtil.stampIt());
		bounty.setStatusId(1);
		bounty.setCorrectAnswerId(null);
		bounty.setVotes(0);
		return bountyRepo.save(bounty);
	}
	
}

