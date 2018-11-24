package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Wallet;
import com.revature.repos.WalletRepo;

@Service
public class WalletService {

	@Autowired
	private WalletRepo sWalletRepo;
	
	public Wallet newWallet() {
		Wallet tNewWallet = new Wallet(0);
		System.out.println(tNewWallet.getWallet_id());
		return sWalletRepo.save(tNewWallet);
	}
}
