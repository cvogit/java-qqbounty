package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

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
	@Column
	private String email;
	
	@Nullable
	private String picture;
	
	@NotNull
	private int rating;
	
	@NotNull
	private int wallet_id;
	
	@NotNull
	private int role_id;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(int user_id, @NotNull @Size(min = 4, max = 12) String username, @NotNull String password,
			@NotNull String email, String picture, @NotNull int rating, @NotNull int wallet_id,
			@NotNull int role_id) {
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


	public int getUserId() {
		return user_id;
	}


	public void setUserId(int user_id) {
		this.user_id = user_id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUserpassword() {
		return password;
	}


	public void setUserpassword(String password) {
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


	public int getWalletid() {
		return wallet_id;
	}


	public void setWalletid(int wallet_id) {
		this.wallet_id = wallet_id;
	}


	public int getUserroleid() {
		return role_id;
	}


	public void setUserroleid(int role_id) {
		this.role_id = role_id;
	}

}
