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
	private int userid;

	@NotNull
	@Size(min=4, max=12)
	@Column(unique=true, nullable=false, updatable=false)
	private String username;
	
	@NotNull
	@Column(nullable=false)
	private String userpassword;


	@NotNull
	@Column
	private String email;
	
	@Nullable
	private String picture;
	
	@NotNull
	private int rating;
	
	@NotNull
	private int walletid;
	
	@NotNull
	private int userroleid;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(int userid, @NotNull @Size(min = 4, max = 12) String username, @NotNull String userpassword,
			@NotNull String email, String picture, @NotNull int rating, @NotNull int walletid,
			@NotNull int userroleid) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpassword = userpassword;
		this.email = email;
		this.picture = picture;
		this.rating = rating;
		this.walletid = walletid;
		this.userroleid = userroleid;
	}


	public int getUserId() {
		return userid;
	}


	public void setUserId(int userid) {
		this.userid = userid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUserpassword() {
		return userpassword;
	}


	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
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
		return walletid;
	}


	public void setWalletid(int walletid) {
		this.walletid = walletid;
	}


	public int getUserroleid() {
		return userroleid;
	}


	public void setUserroleid(int userroleid) {
		this.userroleid = userroleid;
	}


		

	

}
