package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="userproducts")
public class UserProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userProductId;
	
	@Column(nullable=false)
	private int userId;

	@Column(nullable=false)
	private int productId;
	
	@Column(nullable=false)
	private Timestamp purchaseDate;

	public UserProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProduct(int userProductId, int userId, int productId, Timestamp purchaseDate) {
		super();
		this.userProductId = userProductId;
		this.userId = userId;
		this.productId = productId;
		this.purchaseDate = purchaseDate;
	}

	public int getUserProductId() {
		return userProductId;
	}

	public void setUserProductId(int userProductId) {
		this.userProductId = userProductId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Override
	public String toString() {
		return "UserProduct [userProductId=" + userProductId + ", userId=" + userId + ", productId=" + productId
				+ ", purchaseDate=" + purchaseDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		result = prime * result + ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
		result = prime * result + userId;
		result = prime * result + userProductId;
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
		UserProduct other = (UserProduct) obj;
		if (productId != other.productId)
			return false;
		if (purchaseDate == null) {
			if (other.purchaseDate != null)
				return false;
		} else if (!purchaseDate.equals(other.purchaseDate))
			return false;
		if (userId != other.userId)
			return false;
		if (userProductId != other.userProductId)
			return false;
		return true;
	}
	
	
	
	
}
