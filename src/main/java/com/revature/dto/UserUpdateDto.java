package com.revature.dto;

import com.revature.models.User;

public class UserUpdateDto {
	
	private String picture;
	private String email;
	
	public UserUpdateDto(String picture, String email) {
		super();
		this.picture = picture;
		this.email = email;
	}
	
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
