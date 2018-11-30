package com.revature.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Bounty;

@Repository
public interface BountyRepo extends JpaRepository<Bounty, Integer> {

	@SuppressWarnings("unchecked")
	Bounty save(Bounty pBounty);
	
	Page<Bounty> findAll(Pageable pageable);
	
	Bounty getOne(Integer id);

}

