package com.revature.dto;

import com.revature.models.User;

public class UserProfileDto {
	
	private int userId;
	private String username;
	private String email;
	private String picture;
	private int walletId;
	private int rating;
	private int walletBalance;

	public UserProfileDto(User pUser, int balance) {
		super();
		this.userId = pUser.getUserId();
		this.username = pUser.getUsername();
		this.email = pUser.getEmail();
		this.picture = pUser.getPicture();
		this.rating = pUser.getRating();
		this.walletId = pUser.getWalletId();
		this.walletBalance = balance;
	}

	public UserProfileDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProfileDto(int userId, String username, String email, String picture, int walletId, int rating,
			int walletBalance) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.picture = picture;
		this.walletId = walletId;
		this.rating = rating;
		this.walletBalance = walletBalance;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(int walletBalance) {
		this.walletBalance = walletBalance;
	}

	@Override
	public String toString() {
		return "UserProfileDto [userId=" + userId + ", username=" + username + ", email=" + email + ", picture="
				+ picture + ", walletId=" + walletId + ", rating=" + rating + ", walletBalance=" + walletBalance + "]";
	}

	
	
}

