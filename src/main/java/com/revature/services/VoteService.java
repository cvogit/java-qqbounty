package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.AnswerVote;
import com.revature.models.BountyVote;
import com.revature.repos.AnswerVoteRepo;
import com.revature.repos.BountyVoteRepo;

/*
 * This class is used to keep track of votes in AnswerService.
 */
@Service
public class VoteService {
	
	@Autowired
	private AnswerVoteRepo answerVoteRepo;

	@Autowired
	private BountyVoteRepo bountyVoteRepo;
	
	public List<AnswerVote> findByAnswerIdAndUserId(int answerId,int userId) {
		return answerVoteRepo.findByAnswerIdAndUserId(answerId,userId);
	}
	
	public AnswerVote saveAnswerVote(AnswerVote answerVote) {
		return answerVoteRepo.save(answerVote);
	}
	
	public List<BountyVote> findByBountyIdAndUserId(int bountyId,int userId) {
		return bountyVoteRepo.findByBountyIdAndUserId(bountyId,userId);
	}
	
	public BountyVote saveBountyVote(BountyVote bountyVote) {
		return bountyVoteRepo.save(bountyVote);
	}
	

}
