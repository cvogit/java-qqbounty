package com.revature.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.revature.models.Bounty;
import com.revature.models.Subject;

public class BountyDto {
	private int bountyId;
	private String description;
	private Timestamp submitted;
	private int amount;
	private int votes;
	private Timestamp expiration;
	private int statusId;
	private Integer correctAnswerId;
	private String picture;
	private int userId;
	private String username;
	private Set<Subject> subject = new HashSet<>();

	public BountyDto(Bounty bounty, String username) {
		super();
		this.bountyId = bounty.getBountyId();
		this.description = bounty.getDescription();
		this.submitted = bounty.getSubmitted();
		this.amount = bounty.getAmount();
		this.votes = bounty.getVotes();
		this.expiration = bounty.getExpiration();
		this.statusId = bounty.getStatusId();
		this.correctAnswerId = bounty.getCorrectAnswerId();
		this.picture = bounty.getPicture();
		this.userId = bounty.getUserId();
		this.username = username;
		this.subject = bounty.getSubject();
	}	
	
	public BountyDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getBountyId() {
		return bountyId;
	}

	public void setBountyId(int bountyId) {
		this.bountyId = bountyId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public Timestamp getExpiration() {
		return expiration;
	}

	public void setExpiration(Timestamp expiration) {
		this.expiration = expiration;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public Integer getCorrectAnswerId() {
		return correctAnswerId;
	}

	public void setCorrectAnswerId(Integer correctAnswerId) {
		this.correctAnswerId = correctAnswerId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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

	public Set<Subject> getSubject() {
		return subject;
	}

	public void setSubject(Set<Subject> subject) {
		this.subject = subject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + bountyId;
		result = prime * result + ((correctAnswerId == null) ? 0 : correctAnswerId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((expiration == null) ? 0 : expiration.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + statusId;
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		BountyDto other = (BountyDto) obj;
		if (amount != other.amount)
			return false;
		if (bountyId != other.bountyId)
			return false;
		if (correctAnswerId == null) {
			if (other.correctAnswerId != null)
				return false;
		} else if (!correctAnswerId.equals(other.correctAnswerId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expiration == null) {
			if (other.expiration != null)
				return false;
		} else if (!expiration.equals(other.expiration))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (statusId != other.statusId)
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
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
		return "BountyDto [bountyId=" + bountyId + ", description=" + description + ", submitted=" + submitted
				+ ", amount=" + amount + ", votes=" + votes + ", expiration=" + expiration + ", statusId=" + statusId
				+ ", correctAnswerId=" + correctAnswerId + ", picture=" + picture + ", userId=" + userId + ", username="
				+ username + ", subject=" + subject + "]";
	}

}
