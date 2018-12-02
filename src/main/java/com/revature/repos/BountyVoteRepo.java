package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.BountyVote;
@Repository
public interface BountyVoteRepo extends JpaRepository<BountyVote, Integer> {

		@SuppressWarnings("unchecked")
		BountyVote save(BountyVote bVote); 
		
		List<BountyVote> findByBountyIdAndUserId(int bountyId,int userId);
	}
