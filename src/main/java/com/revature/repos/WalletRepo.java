package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer> {

	@SuppressWarnings("unchecked")
	Wallet save(Wallet pWallet);
	
	Wallet getOne(int id);
	
	List <Wallet> findAll();
	
}
