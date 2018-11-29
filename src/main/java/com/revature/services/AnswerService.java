package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.UserPublicDto;
import com.revature.models.Answer;
import com.revature.models.User;
import com.revature.models.Vote;
import com.revature.repos.AnswerRepo;
import com.revature.util.ResponseMap;
import com.revature.util.TsUtil;

@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepo answerRepo;

	@Autowired
	private VoteService voteService;

	

	public Map<String,Object> findAll() {
		
		 List<Answer> answerList= answerRepo.findAll();
		 return ResponseMap.getNewMap("answer_list",answerList);
	}

	public Map<String,Object>  findById(int id) {
		Answer answer = answerRepo.getOne(id);
		return ResponseMap.getNewMap("answer",answer);
	}
	
	public  Map<String,Object>   findByBountyId(int id) {
		List<Answer> answerList= answerRepo.findByBountyId(id);
		return ResponseMap.getNewMap("answer_list",answerList);
	}
	
	public  Map<String,Object>  save(Answer answer) {
		answer.setStatusId(1);
		answer.setVotes(0);
		answer.setSubmitted(TsUtil.stampIt());
		

		Answer updatedAnswer = answerRepo.save(answer);
		return ResponseMap.getNewMap("answer",updatedAnswer);
	}
	
//	public  Map<String,Object>  update(Answer answer) {
//		Answer updatedAnswer = answerRepo.save(answer);
//		return ResponseMap.getNewMap("answer",updatedAnswer);
//	}
	
	public Map<String,Object> updateVote(int answerId, int userId, int voteValue) {
			Answer answer = answerRepo.getOne(answerId);
			List<Vote> votes = voteService.findByAnswerIdAndUserId(answerId, userId);
			
			if (votes.size() >= 1) {
				System.out.println("User: "+ userId +"already voted for answer: " + answerId);
				return ResponseMap.getNewMap("vote_status",false);
			}
			
			if (voteValue == 1 || voteValue == -1) {
				answer.setVotes(answer.getVotes()+voteValue);
				answerRepo.save(answer);
				voteService.save(new Vote(0,userId,answerId));
				return ResponseMap.getNewMap("vote_status",true);
			}
			System.out.println("voteValue not -1 or 1, voteValue is:" + voteValue);
		    return ResponseMap.getNewMap("vote_status",false);
	}
	
	
}
