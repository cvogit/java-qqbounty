package com.revature.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.revature.models.Bounty;
import com.revature.repos.BountyRepo;
import com.revature.repos.SubjectRepo;
import com.revature.repos.SubjectToBountyRepo;
import com.revature.util.ResponseMap;
import com.revature.util.TsUtil;

@Service
public class BountyService {

	@Autowired
	private BountyRepo bountyRepo;
	
	@Autowired
	private SubjectRepo subjectRepo;


	@Autowired
	private SubjectToBountyRepo subjectToBountyRepo;
	
	public Map<String, Object> findAll(Pageable pageable) {
		return ResponseMap.getNewMap("bounty_list", bountyRepo.findAll(pageable));
	}
	

	public Map<String, Object> findAllByOrderByVotes(Pageable pageable) {
		return ResponseMap.getNewMap("bounty_list", bountyRepo.findAllByOrderByVotesDesc(pageable));
	}

	public Map<String, Object> findAllByOrderByAmount(Pageable pageable) {
		return ResponseMap.getNewMap("bounty_list", bountyRepo.findAllByOrderByAmountDesc(pageable));
	}

	public Map<String, Object> findAllByOrderByNewest(Pageable pageable) {
		return ResponseMap.getNewMap("bounty_list", bountyRepo.findAllByOrderBySubmittedDesc(pageable));
	}
	
	public Map<String, Object> findAllBySubjectTag(Pageable pageable, List<String> subjectsList) {
		List<Integer> subjectIds = subjectRepo.getListSubjectId(subjectsList);
		List<Integer> bountyIds  = subjectToBountyRepo.getListBountyIds(subjectIds);
		return ResponseMap.getNewMap("bounty_list", bountyRepo.findByBountyIdIn(pageable,bountyIds));
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
	public Map<String, Object> save(Bounty bounty) {
		bounty.setSubmitted(TsUtil.stampIt());
		bounty.setStatusId(1);
		bounty.setCorrectAnswerId(null);
		bounty.setVotes(0);
		return ResponseMap.getNewMap("bounty", bountyRepo.save(bounty));
	}	
	
	
	//Helper Methods for other Controllers/Services
	
	public Bounty findById(int id) {
		return bountyRepo.getOne(id);
	}



	
}

