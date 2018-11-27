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
import org.mindrot.jbcrypt.BCrypt;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Size(min=4, max=12)
	@Column(unique=true, nullable=true, updatable=false)
	private String username;
	
	@Column(nullable=true)
	private String password;

	@Column(nullable=true)
	private String email;
	
	@Column(nullable=true)
	private String picture;
	
	@Column(nullable=true)
	private int rating;
	
	@Column(nullable=true)
	private int walletId;
	
	@Column(nullable=true)
	private int roleId;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(int userId, @NotNull @Size(min = 4, max = 12) String username, @NotNull String password,
			@NotNull String email, String picture, int rating, int walletId, int roleId) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.picture = picture;
		this.rating = rating;
		this.walletId = walletId;
		this.roleId = roleId;
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


	public int getWalletId() {
		return walletId;
	}


	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public void hashPassword() {
		this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
	}
}
