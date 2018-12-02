package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Answer;
import com.revature.models.AnswerVote;
@Repository
public interface AnswerVoteRepo extends JpaRepository<AnswerVote, Integer> {

		@SuppressWarnings("unchecked")
		AnswerVote save(AnswerVote answerVote); 
		
		List<AnswerVote> findByAnswerIdAndUserId(int answerId,int userId);
	}
