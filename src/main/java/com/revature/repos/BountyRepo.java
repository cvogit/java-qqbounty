package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.revature.models.Bounty;

public interface BountyRepo extends JpaRepository<Bounty, Integer> {

	@SuppressWarnings("unchecked")
	Bounty save(Bounty pBounty);
	
	List<Bounty> findAll();

	Bounty getOne(Integer id);

}
