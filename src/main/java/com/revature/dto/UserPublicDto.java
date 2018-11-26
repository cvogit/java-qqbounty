package com.revature.dto;

import com.revature.models.User;

public class UserPublicDto {
	
	private String username;
	private String picture;
	
	private int rating;

	public UserPublicDto(User pUser) {
		super();
		this.username = pUser.getUsername();
		this.picture = pUser.getPicture();
		this.rating = pUser.getRating();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
