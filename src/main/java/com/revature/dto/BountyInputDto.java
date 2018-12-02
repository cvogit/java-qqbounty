package com.revature.dto;

import java.util.HashSet;
import java.util.Set;

import com.revature.models.Subject;

public class BountyInputDto {
	private String title;
	private String description;
	private int amount;
	private int timer;
    private String picture;
	private Set<Subject> subject = new HashSet<>();
	
	public BountyInputDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BountyInputDto(String title, String description, int amount, int timer, String picture,
			Set<Subject> subject) {
		super();
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.timer = timer;
		this.picture = picture;
		this.subject = subject;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + timer;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		BountyInputDto other = (BountyInputDto) obj;
		if (amount != other.amount)
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
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (timer != other.timer)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BountyInputDto [title=" + title + ", description=" + description + ", amount=" + amount + ", timer="
				+ timer + ", picture=" + picture + ", subject=" + subject + "]";
	}
	
	
	
	

}
