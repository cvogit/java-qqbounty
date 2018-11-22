package com.revature.model;

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
import com.revature.utils.TsUtil;

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
	private Timestamp sumbitted;
	
	@NotNull
	private int amount;
	
	@NotNull
	private int votes;
	
	@NotNull
	private int timer;
	
	@NotNull
	private int bounty_status_id;
	
	//Default all answers to answer_id until a correct answer is chosen
	@Nullable
	private int correct_answer;
	
	@Nullable
	private String bounty_picture;
	
	@NotNull
	private int user_id;
	
	@NotNull
	private int subject_id;
	
	
	public Bounty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bounty(int bounty_id, String description, Timestamp sumbitted, int amount, int votes, int timer,
			int bounty_status_id, int correct_answer, String bounty_picture, int user_id, int subject_id) {
		super();
		this.bounty_id = bounty_id;
		this.description = description;
		this.sumbitted = TsUtil.stampIt();
		this.amount = amount;
		this.votes = votes;
		this.timer = timer;
		this.bounty_status_id = bounty_status_id;
		this.correct_answer = correct_answer;
		this.bounty_picture = bounty_picture;
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

	public Timestamp getSumbitted() {
		return sumbitted;
	}

	public void setSumbitted(Timestamp sumbitted) {
		this.sumbitted = TsUtil.stampIt();
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

	public int getBounty_status_id() {
		return bounty_status_id;
	}

	public void setBounty_status_id(int bounty_status_id) {
		this.bounty_status_id = bounty_status_id;
	}

	public int getCorrect_answer() {
		return correct_answer;
	}

	public void setCorrect_answer(int correct_answer) {
		this.correct_answer = correct_answer;
	}

	public String getBounty_picture() {
		return bounty_picture;
	}

	public void setBounty_picture(String bounty_picture) {
		this.bounty_picture = bounty_picture;
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
	public String toString() {
		return "Bounty [bounty_id=" + bounty_id + ", description=" + description + ", sumbitted=" + sumbitted
				+ ", amount=" + amount + ", votes=" + votes + ", timer=" + timer + ", bounty_status_id="
				+ bounty_status_id + ", correct_answer=" + correct_answer + ", bounty_picture=" + bounty_picture
				+ ", user_id=" + user_id + ", subject_id=" + subject_id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + bounty_id;
		result = prime * result + ((bounty_picture == null) ? 0 : bounty_picture.hashCode());
		result = prime * result + bounty_status_id;
		result = prime * result + correct_answer;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + subject_id;
		result = prime * result + ((sumbitted == null) ? 0 : sumbitted.hashCode());
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
		if (bounty_id != other.bounty_id)
			return false;
		if (bounty_picture == null) {
			if (other.bounty_picture != null)
				return false;
		} else if (!bounty_picture.equals(other.bounty_picture))
			return false;
		if (bounty_status_id != other.bounty_status_id)
			return false;
		if (correct_answer != other.correct_answer)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (subject_id != other.subject_id)
			return false;
		if (sumbitted == null) {
			if (other.sumbitted != null)
				return false;
		} else if (!sumbitted.equals(other.sumbitted))
			return false;
		if (timer != other.timer)
			return false;
		if (user_id != other.user_id)
			return false;
		if (votes != other.votes)
			return false;
		return true;
	}
	
	
	
}
