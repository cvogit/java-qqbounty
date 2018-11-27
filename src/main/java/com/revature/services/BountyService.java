package com.revature.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Bounty;
import com.revature.repos.AnswerRepo;
import com.revature.repos.BountyRepo;
import com.revature.util.ResponseMap;
import com.revature.util.TsUtil;

@Service
public class BountyService {

	@Autowired
	private BountyRepo bountyRepo;
	
	@Autowired
	private AnswerRepo sAnswerRepo;

	public List<Bounty> findAll() {
		return bountyRepo.findAll();
	}

	public Bounty findById(int id) {
		return bountyRepo.getOne(id);
	}
	
	@Transactional
	public Map<String, Object> update(int pBountyId, int pAnswerId) {
		if(sAnswerRepo.getOne(pAnswerId) == null)
			return null;
		Bounty tBounty = bountyRepo.getOne(pBountyId);
		tBounty.setCorrectAnswerId(pAnswerId);
		return ResponseMap.getNewMap("bounty", tBounty);
	}
	
  
  /*  EXAMPLE SAVE REQUEST
		 * {
         *   "amount": 150, 	     	any-amount
         *   "description": "string",   any-string
         *   "picture": "string",       any-string
         *    "submitted": 0,           optional, auto sets to current date
         *    "status_id": 1,           Set to one
         *    "subject_id": 1,          NEED TO MODIFY, only takes in one subject right now, no junction table yet
         *    "timer": 0,               any-int
         *    "user_id": 1,            valid user id
         *    "votes": 0                Set to 0
         * }
		 */
	//set Timestamp upon new bounty creation
	public Bounty save(Bounty bounty) {
		bounty.setSubmitted(TsUtil.stampIt());
		return bountyRepo.save(bounty);
	}
	
}

