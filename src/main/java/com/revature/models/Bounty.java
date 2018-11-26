package com.revature.models;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="bounties")
public class Bounty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bountyId;
	
	@NotNull
	@Column
	private String description;
	
	//Timestamp is generated via a utility, leave null on API call
	@Null
	private Timestamp submitted;
	
	@NotNull
	private int amount;
	
	@NotNull
	private int votes;
	
	@NotNull
	private int timer;
	
	@NotNull
	private int statusId;
	
	//Default 
	@Null
	private Integer correctAnswerId;
	
	@Null
	private String picture;
	
	@NotNull
	private int userId;
	
	 @ManyToMany(fetch = FetchType.LAZY,
	            cascade = {
	                CascadeType.PERSIST,
	                CascadeType.MERGE
	            })
	    @JoinTable(name = "subjectstobounties",
	            joinColumns = { @JoinColumn(name = "bountyId") },
	            inverseJoinColumns = { @JoinColumn(name = "subjectId") })
	private Set<Subject> subject = new HashSet<>();

	public Bounty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bounty(int bountyId, @NotNull String description, Timestamp submitted, int amount, int votes, int timer,
			int statusId, Integer correctAnswerId, String picture, int userId) {
		super();
		this.bountyId = bountyId;
		this.description = description;
		this.submitted = submitted;
		this.amount = amount;
		this.votes = votes;
		this.timer = timer;
		this.statusId = statusId;
		this.correctAnswerId = correctAnswerId;
		this.picture = picture;
		this.userId = userId;
	
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

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
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

	public void setCorrectAnswerId(int correctAnswerId) {
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
		result = prime * result + correctAnswerId;
		result = prime * result + bountyId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + statusId;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + timer;
		result = prime * result + userId;
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
		Bounty other = (Bounty) obj;
		if (amount != other.amount)
			return false;
		if (correctAnswerId != other.correctAnswerId)
			return false;
		if (bountyId != other.bountyId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (statusId != other.statusId)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (timer != other.timer)
			return false;
		if (userId != other.userId)
			return false;
		if (votes != other.votes)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Bounty [bountyId=" + bountyId + ", description=" + description + ", submitted=" + submitted
				+ ", amount=" + amount + ", votes=" + votes + ", timer=" + timer + ", statusId=" + statusId
				+ ", correctAnswerId=" + correctAnswerId + ", picture=" + picture + ", userId=" + userId + ", subject_id="
				+ "]";
	}
	
	
}
