package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="answers")
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int answerId;
	
	@NotNull
	@Column
	private String description;
	
	//Timestamp is generated via a utility, leave null on API call
	private Timestamp submitted;

	@NotNull
	private int votes;
	
	@NotNull
	private int statusId;

	@NotNull
	private int bountyId;
	
	
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Answer(int answerId, @NotNull String description, Timestamp submitted, @NotNull int votes,
			@NotNull int statusId, @NotNull int bountyId) {
		super();
		this.answerId = answerId;
		this.description = description;
		this.submitted = submitted;
		this.votes = votes;
		this.statusId = statusId;
		this.bountyId = bountyId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getDescription() {
		return description;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getBountyId() {
		return bountyId;
	}

	public void setBountyId(int bountyId) {
		this.bountyId = bountyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + answerId;
		result = prime * result + bountyId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + statusId;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + votes;
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
		Answer other = (Answer) obj;
		if (answerId != other.answerId)
			return false;
		if (bountyId != other.bountyId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (statusId != other.statusId)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (votes != other.votes)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", description=" + description + ", submitted=" + submitted
				+ ", votes=" + votes + ", statusId=" + statusId + ", bountyId=" + bountyId + "]";
	}
}