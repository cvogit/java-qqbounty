package com.revature.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.UserPublicDto;
import com.revature.models.User;
import com.revature.repos.UserRepo;
import com.revature.util.JwtUtil;

@Service
public class UserService {

	@Autowired
	private UserRepo sUserRepo;
	
	@Autowired
	private WalletService sWalletService;

	public List<UserPublicDto> findAll() {
		List<User> tUserList = sUserRepo.findAll();
		List<UserPublicDto> tUserPublicDtos = new ArrayList<UserPublicDto>();
		tUserList.forEach(user -> tUserPublicDtos.add(new UserPublicDto(user)));
		
		return tUserPublicDtos;
	}

	public UserPublicDto findById(int pId) {
		return new UserPublicDto(sUserRepo.getOne(pId));
	}

	public User save(User pUser) {
		pUser.setWallet_id(sWalletService.newWallet().getWallet_id());
		pUser.setRole_id(1);
		pUser.setRating(0);
		pUser.hashPassword();

		return sUserRepo.save(pUser);
	}

	public Map<String, Object> login(User pUser) {
		User tUser = sUserRepo.findByUsername(pUser.getUsername());
		if(tUser != null) {
			if(BCrypt.checkpw(pUser.getPassword(), tUser.getPassword())) {
				Map<String, Object> tResult = new HashMap<>();
				try {
					tResult.put("jwt", JwtUtil.createJwt(tUser));
					return tResult;
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}
	
	public UserPublicDto update(User pUser) {
		User tUser = sUserRepo.getOne(pUser.getUser_id());
		tUser.setEmail(pUser.getEmail());
		tUser.setPicture(pUser.getPicture());
		return new UserPublicDto(tUser);
	}
}
