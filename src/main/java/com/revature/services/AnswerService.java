package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Answer;
import com.revature.repos.AnswerRepo;
import com.revature.util.TsUtil;

@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepo answerRepo;

	public List<Answer> findAll() {
		return answerRepo.findAll();
	}

	public Answer findById(int id) {
		return answerRepo.getOne(id);
	}
	
  
  /*  EXAMPLE SAVE REQUEST
		*{
		* "description": "Example Description"
 		*}
  */
	//set Timestamp upon new bounty creation
	public Answer save(Answer answer) {
		answer.setStatus_id(1);
		answer.setVotes(0);
		answer.setSubmitted(TsUtil.stampIt());
		return answerRepo.save(answer);
	}
	
}
