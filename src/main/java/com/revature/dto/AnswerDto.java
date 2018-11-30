package com.revature.dto;

import java.sql.Timestamp;

import com.revature.models.Answer;

public class AnswerDto {
	
	private int answerId;
	private String description;
	private int userId;
	private String username;
	private Timestamp submitted;
	private int votes;
	private int statusId;
	private int bountyId;

	public AnswerDto(Answer answer, String username) {
		super();
		this.answerId = answer.getAnswerId();
		this.description = answer.getDescription();
		this.userId = answer.getUserId();
		this.username = username;
		this.submitted = answer.getSubmitted();
		this.votes = answer.getVotes();
		this.statusId = answer.getStatusId();
		this.bountyId = answer.getBountyId();
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		AnswerDto other = (AnswerDto) obj;
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
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (votes != other.votes)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AnswerDto [answerId=" + answerId + ", description=" + description + ", userId=" + userId + ", username="
				+ username + ", submitted=" + submitted + ", votes=" + votes + ", statusId=" + statusId + ", bountyId="
				+ bountyId + "]";
	}
	
	
	
}
