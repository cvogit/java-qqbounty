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
@Table(name="bountiestousers")
public class BountyVote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int voteId;
	
	@NotNull
	@Column
	private int userId;
	
	@NotNull
	@Column
	private int bountyId;

	public BountyVote() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BountyVote(int voteId, @NotNull int userId, @NotNull int bountyId) {
		super();
		this.voteId = voteId;
		this.userId = userId;
		this.bountyId = bountyId;
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
		result = prime * result + bountyId;
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
		BountyVote other = (BountyVote) obj;
		if (bountyId != other.bountyId)
			return false;
		if (userId != other.userId)
			return false;
		if (voteId != other.voteId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BountyVote [voteId=" + voteId + ", userId=" + userId + ", bountyId=" + bountyId + "]";
	}

	
}
