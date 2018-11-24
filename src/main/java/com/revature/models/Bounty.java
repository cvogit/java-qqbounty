package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="bounties")
public class Bounty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bounty_id;
	
	@NotNull
	@Column
	private String description;
	
	//Timestamp is generated via a utility, leave null on API call
	@Nullable
	private Timestamp submitted;
	
	@NotNull
	private int amount;
	
	@NotNull
	private int votes;
	
	@NotNull
	private int timer;
	
	@NotNull
	private int status_id;
	
	//Default all answers to answer_id until a correct answer is chosen
	@Nullable
	private int answer_id;
	
	@Nullable
	private String picture;
	
	@NotNull
	private int user_id;
	
	@NotNull
	private int subject_id;

	public Bounty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bounty(int bounty_id, @NotNull String description, Timestamp submitted, int amount, int votes, int timer,
			int status_id, int answer_id, String picture, int user_id, int subject_id) {
		super();
		this.bounty_id = bounty_id;
		this.description = description;
		this.submitted = submitted;
		this.amount = amount;
		this.votes = votes;
		this.timer = timer;
		this.status_id = status_id;
		this.answer_id = answer_id;
		this.picture = picture;
		this.user_id = user_id;
		this.subject_id = subject_id;
	}

	public int getBounty_id() {
		return bounty_id;
	}

	public void setBounty_id(int bounty_id) {
		this.bounty_id = bounty_id;
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

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getAnswer_id() {
		return answer_id;
	}

	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + answer_id;
		result = prime * result + bounty_id;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + status_id;
		result = prime * result + subject_id;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + timer;
		result = prime * result + user_id;
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
		if (answer_id != other.answer_id)
			return false;
		if (bounty_id != other.bounty_id)
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
		if (status_id != other.status_id)
			return false;
		if (subject_id != other.subject_id)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (timer != other.timer)
			return false;
		if (user_id != other.user_id)
			return false;
		if (votes != other.votes)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Bounty [bounty_id=" + bounty_id + ", description=" + description + ", submitted=" + submitted
				+ ", amount=" + amount + ", votes=" + votes + ", timer=" + timer + ", status_id=" + status_id
				+ ", answer_id=" + answer_id + ", picture=" + picture + ", user_id=" + user_id + ", subject_id="
				+ subject_id + "]";
	}
	
	
}
