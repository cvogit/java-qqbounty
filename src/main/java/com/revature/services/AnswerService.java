package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Answer;
import com.revature.models.Vote;
import com.revature.repos.AnswerRepo;
import com.revature.repos.VoteRepo;
import com.revature.util.TsUtil;

@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepo answerRepo;

	@Autowired
	private VoteService voteService;

	
	public List<Answer> findAll() {
		return answerRepo.findAll();
	}

	public Answer findById(int id) {
		return answerRepo.getOne(id);
	}
	
	public List<Answer> findByBountyId(int id) {
		return answerRepo.findByBountyId(id);
	}
	
  
  /*  EXAMPLE SAVE REQUEST
		*{
		* "description": "Example Description"
 		*}
  */
	//set Timestamp upon new bounty creation
	public Answer save(Answer answer) {
		answer.setStatusId(1);
		answer.setVotes(0);
		answer.setSubmitted(TsUtil.stampIt());
		return answerRepo.save(answer);
	}
	
	public Boolean updateVote(int answerId, int userId, int voteValue) {
			Answer answer = answerRepo.getOne(answerId);
			List<Vote> votes = voteService.findByAnswerIdAndUserId(answerId, userId);
			
			if (votes.size() >= 1) {
				System.out.println("User: "+ userId +"already voted for answer: " + answerId);
				return false;
			}
			
			if (voteValue == 1 || voteValue == -1) {
				answer.setVotes(answer.getVotes()+voteValue);
				answerRepo.save(answer);
				voteService.save(new Vote(0,userId,answerId));
				return true;
			}
			System.out.println("voteValue not -1 or 1, voteValue is:" + voteValue);
			return false;
	}
	
	public Answer update(Answer answer) {
		return answerRepo.save(answer);
	}
	
	
	
}
