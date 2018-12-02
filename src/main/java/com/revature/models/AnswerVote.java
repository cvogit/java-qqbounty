package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="answerstousers")
public class AnswerVote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int voteId;
	
	@NotNull
	@Column
	private int userId;
	
	@NotNull
	@Column
	private int answerId;

	public AnswerVote() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AnswerVote(int voteId, @NotNull int userId, @NotNull int answerId) {
		super();
		this.voteId = voteId;
		this.userId = userId;
		this.answerId = answerId;
	}

	public int getVoteId() {
		return voteId;
	}

	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + answerId;
		result = prime * result + userId;
		result = prime * result + voteId;
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
		AnswerVote other = (AnswerVote) obj;
		if (answerId != other.answerId)
			return false;
		if (userId != other.userId)
			return false;
		if (voteId != other.voteId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vote [voteId=" + voteId + ", userId=" + userId + ", answerId=" + answerId + "]";
	}

    

	
	
	
}
