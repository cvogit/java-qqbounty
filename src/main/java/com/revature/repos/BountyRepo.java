package com.revature.repos;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.Bounty;

@Repository
public interface BountyRepo extends JpaRepository<Bounty, Integer> {

	@SuppressWarnings("unchecked")
	Bounty save(Bounty pBounty);
	
	@Query(value = "SELECT b FROM Bounty b WHERE expiration > :ts AND  statusId = 1 ")
	Page<Bounty> findAll(Pageable pageable, Timestamp ts);
	
	Bounty getOne(Integer id);

	@Query(value = "SELECT b FROM Bounty b WHERE expiration > :ts AND statusId = 1 order by votes desc")
	Page<Bounty> findAllByOrderByVotes(Pageable pageable, Timestamp ts);

	@Query(value = "SELECT b FROM Bounty b WHERE expiration > :ts  AND statusId = 1  order by amount desc")
	Page<Bounty> findAllByOrderByAmountDesc(Pageable pageable, Timestamp ts);

	@Query(value = "SELECT b FROM Bounty b WHERE expiration > :ts  AND statusId = 1 order by submitted desc")
	Page<Bounty> findAllByOrderBySubmittedDesc(Pageable pageable, Timestamp ts);

	Page<Bounty> findByBountyIdIn(Pageable pageable,List<Integer> bountyIds);

}

