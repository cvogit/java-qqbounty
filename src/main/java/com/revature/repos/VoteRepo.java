package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Answer;
import com.revature.models.Vote;
@Repository
public interface VoteRepo extends JpaRepository<Vote, Integer> {

		@SuppressWarnings("unchecked")
		Vote save(Vote vote); 
		
		List<Vote> findByAnswerIdAndUserId(int answerId,int userId);
	}
