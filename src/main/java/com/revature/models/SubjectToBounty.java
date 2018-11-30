package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="subjectstobounties")
public class SubjectToBounty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subjectsToBountiesId;
	
	@Column(nullable=false)
	private int bountyId;
	
	@Column(nullable=false)
	private int subjectId;

	public SubjectToBounty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubjectToBounty(int subjectsToBountiesId, int bountyId, int subjectId) {
		super();
		this.subjectsToBountiesId = subjectsToBountiesId;
		this.bountyId = bountyId;
		this.subjectId = subjectId;
	}

	public int getSubjectsToBountiesId() {
		return subjectsToBountiesId;
	}

	public void setSubjectsToBountiesId(int subjectsToBountiesId) {
		this.subjectsToBountiesId = subjectsToBountiesId;
	}

	public int getBountyId() {
		return bountyId;
	}

	public void setBountyId(int bountyId) {
		this.bountyId = bountyId;
	}

	public int getsubjectId() {
		return subjectId;
	}

	public void setsubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bountyId;
		result = prime * result + subjectId;
		result = prime * result + subjectsToBountiesId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubjectToBounty other = (SubjectToBounty) obj;
		if (bountyId != other.bountyId)
			return false;
		if (subjectId != other.subjectId)
			return false;
		if (subjectsToBountiesId != other.subjectsToBountiesId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubjectToBounty [subjectsToBountiesId=" + subjectsToBountiesId + ", bountyId=" + bountyId
				+ ", subjectId=" + subjectId + "]";
	}

	
	
}
