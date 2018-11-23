package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;

	@NotNull
	@Size(min=4, max=12)
	@Column(unique=true, nullable=false, updatable=false)
	private String username;
	
	@NotNull
	@Column(nullable=false)
	private String password;


	@NotNull
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=true)
	private String picture;
	
	@Column(nullable=false)
	private int rating;
	
	@Column(nullable=false)
	private int wallet_id;
	
	@Column(nullable=false)
	private int role_id;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(int user_id, @NotNull @Size(min = 4, max = 12) String username, @NotNull String password,
			@NotNull String email, String picture, int rating, int wallet_id, int role_id) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.picture = picture;
		this.rating = rating;
		this.wallet_id = wallet_id;
		this.role_id = role_id;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public int getWallet_id() {
		return wallet_id;
	}


	public void setWallet_id(int wallet_id) {
		this.wallet_id = wallet_id;
	}


	public int getRole_id() {
		return role_id;
	}


	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}


	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", picture=" + picture + ", rating=" + rating + ", wallet_id=" + wallet_id + ", role_id=" + role_id
				+ "]";
	}
}
