package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repos.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo sUserRepo;
	
	@Autowired
	private WalletService sWalletService;

	public List<User> findAll() {
		return sUserRepo.findAll();
	}

	public User findById(int pId) {
		return sUserRepo.getOne(pId);
	}

	public User save(User pUser) {
		pUser.setWallet_id(sWalletService.newWallet().getWallet_id());
		pUser.setRole_id(1);
		pUser.setRating(0);

		return sUserRepo.save(pUser);
	}
}
