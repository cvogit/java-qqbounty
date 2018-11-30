package com.revature.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Wallet;
import com.revature.repos.WalletRepo;
import com.revature.util.ResponseMap;

@Service
public class WalletService {

	@Autowired
	private WalletRepo sWalletRepo;
	
	public Wallet newWallet() {
		Wallet tNewWallet = new Wallet();
		System.out.println(tNewWallet.getWalletId());
		return sWalletRepo.save(tNewWallet);
	}
	
	//get all wallets
	//get user wallet balance
		public Map<String, Object> findAll() {
			return ResponseMap.getNewMap("wallet_list", sWalletRepo.findAll());
		}
	
	//get user wallet balance
	public Map<String, Object>  findById(int id) {
		return ResponseMap.getNewMap("wallet", sWalletRepo.findById(id));
	}
	
	public Map<String, Object> update(Wallet wallet) {
		return ResponseMap.getNewMap("wallet_update", sWalletRepo.save(wallet));
	}
	
	
	public Wallet getOne(int id) {
		return sWalletRepo.getOne(id);
	}
	
	
}
