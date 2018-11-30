package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.models.Subject;


@Repository
public interface SubjectToBountyRepo extends  JpaRepository<Subject, Integer>{

	    @Query(value = "SELECT bountyId FROM SubjectToBounty s where subjectId in :subjectids")
		List<Integer> getListBountyIds(@Param("subjectids") List<Integer> SubjectIds);

	}
