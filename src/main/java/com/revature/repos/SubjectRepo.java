package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.models.Subject;


@Repository
public interface SubjectRepo extends  JpaRepository<Subject, Integer>{

    @Query(value = "SELECT subjectId FROM Subject s where subject in :subjects")
	List<Integer> getListSubjectId(@Param("subjects") List<String> Subjects);

    
    @Query(value = "SELECT s FROM Subject s where subject = :subject")
    Subject getSubject(String subject);
}
