package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.Answer;
import com.revature.models.User;

@Repository
public interface AnswerRepo extends  JpaRepository<Answer, Integer>{

	@SuppressWarnings("unchecked")
	Answer save(Answer answer);
	
	List<Answer> findAll();

	Answer getOne(Integer id);
	
	List<Answer> findByBountyId(int bountyId);
	
}
