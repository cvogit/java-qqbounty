package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="wallets")
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wallet_id;

	@NotNull
	@Column(nullable=false)
	private int balance;

	public Wallet(int wallet_id, @NotNull int balance) {
		super();
		this.wallet_id = wallet_id;
		this.balance = balance;
	}
	
	public Wallet(@NotNull int balance) {
		super();
		this.balance = balance;
	}

	public int getWallet_id() {
		return wallet_id;
	}

	public void setWallet_id(int wallet_id) {
		this.wallet_id = wallet_id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
