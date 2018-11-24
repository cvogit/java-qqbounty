
package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Bounty;


@Repository
public interface BountyRepo extends JpaRepository<Bounty, Integer> {

		Bounty save(Bounty pBounty);
		List<Bounty> findAll();
	
}	