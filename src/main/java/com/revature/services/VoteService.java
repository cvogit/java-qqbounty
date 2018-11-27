package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Vote;
import com.revature.repos.VoteRepo;

@Service
public class VoteService {

	@Autowired
	private VoteRepo voteRepo;

	public List<Vote> findByAnswerIdAndUserId(int answerId,int userId) {
		return voteRepo.findByAnswerIdAndUserId(answerId,userId);
	}

}
