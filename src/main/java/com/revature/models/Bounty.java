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

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.dto.BountyInputDto;
import com.revature.util.TsUtil;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="bounties")
public class Bounty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bountyId;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	//Timestamp is generated via a utility, leave null on API call
	@Nullable //please don't change this it will break the API
	private Timestamp submitted;
	
	
	private int amount;
	
	
	private int votes;
	
	
	private Timestamp expiration;
	
	
	private int statusId;
	

	private Integer correctAnswerId;
	
	
	private String picture;
	
	
	private int userId;
	
	 @ManyToMany(fetch = FetchType.LAZY,
	            cascade = {
	           
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

	public Bounty(int bountyId, String title, String description, Timestamp submitted, int amount, int votes, Timestamp expiration,
			int statusId, Integer correctAnswerId, String picture, int userId, Set<Subject> subject) {
		super();
		this.bountyId = bountyId;
		this.title = title;
		this.description = description;
		this.submitted = TsUtil.stampIt();
		this.amount = amount;
		this.votes = votes;
		this.expiration = expiration;
		this.statusId = statusId;
		this.correctAnswerId = correctAnswerId;
		this.picture = picture;
		this.userId = userId;
		this.subject = subject;
	}
	
	public Bounty(BountyInputDto inputBounty, int userId) {
		super();
		this.bountyId = bountyId;
		this.title = inputBounty.getTitle();
		this.description = inputBounty.getDescription();
		this.submitted = TsUtil.stampIt();
		this.amount = inputBounty.getAmount();
		this.votes = 0;
		this.expiration = TsUtil.expirationStampIt(inputBounty.getTimer());
		this.statusId = 1;
		this.correctAnswerId = null;
		this.picture = inputBounty.getPicture();
		this.userId = userId;
		this.subject = inputBounty.getSubject();
	}
	
	public int getBountyId() {
		return bountyId;
	}

	public void setBountyId(int bountyId) {
		this.bountyId = bountyId;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTile(String title) {
		this.title = title;
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
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((expiration == null) ? 0 : expiration.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + statusId;
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
		if (votes != other.votes)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bounty [bountyId=" + bountyId + ", description=" + description + ", title=" + title +", submitted=" + submitted
				+ ", amount=" + amount + ", votes=" + votes + ", expiration=" + expiration + ", statusId=" + statusId
				+ ", correctAnswerId=" + correctAnswerId + ", picture=" + picture + ", userId=" + userId + ", subject="
				+ subject + "]";
	}

	
	
}
