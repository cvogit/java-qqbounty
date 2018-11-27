package com.revature.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dto.UserPublicDto;
import com.revature.models.User;
import com.revature.repos.UserRepo;
import com.revature.util.JwtUtil;
import com.revature.util.ResponseMap;

@Service
public class UserService {
	
	@Autowired
	private UserRepo sUserRepo;
	
	@Autowired
	private WalletService sWalletService;
	
	@Autowired
	private JwtUtil sJwtUtil;

	public Map<String, Object> findAll() {
		List<User> tUserList = sUserRepo.findAll();
		List<UserPublicDto> tUserPublicDtos = new ArrayList<UserPublicDto>();
		tUserList.forEach(user -> tUserPublicDtos.add(new UserPublicDto(user)));
		
		return ResponseMap.getNewMap("user_list", tUserPublicDtos);
	}

	public Map<String, Object> findById(int pId) {
		return ResponseMap.getNewMap("user", sUserRepo.getOne(pId));
	}

	public Map<String, Object> save(User pUser) {
		pUser.setWalletId(sWalletService.newWallet().getWallet_id());
		pUser.setRoleId(1);
		pUser.setRating(0);
		pUser.hashPassword();

		User sUser = sUserRepo.save(pUser);
		if(sUser == null)
			return null;
		return ResponseMap.getNewMap("user", new UserPublicDto(sUser));
	}

	public Map<String, Object> login(User pUser) {
		User tUser = sUserRepo.findByUsername(pUser.getUsername());
		if(tUser != null) {
			if(BCrypt.checkpw(pUser.getPassword(), tUser.getPassword())) {
				try {
					return ResponseMap.getNewMap("jwt", sJwtUtil.createJwt(tUser));
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}
	
	@Transactional
	public Map<String, Object> update(User pUser) {
		User tUser = sUserRepo.getOne(pUser.getUserId());
		tUser.setEmail(pUser.getEmail());
		tUser.setPicture(pUser.getPicture());
		return ResponseMap.getNewMap("user", new UserPublicDto(tUser));
	}
}
