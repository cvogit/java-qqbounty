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
import com.revature.dto.UserUpdateDto;
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
		return ResponseMap.getNewMap("user_list", new UserPublicDto(sUserRepo.getOne(pId)));
	}

	public Map<String, Object> save(User pUser) {
		pUser.setWalletId(sWalletService.newWallet().getWallet_id());
		pUser.setRoleId(1);
		pUser.setRating(0);
		pUser.hashPassword();

		return ResponseMap.getNewMap("user_list", sUserRepo.save(pUser));
	}

	public Map<String, Object> login(User pUser) {
		User tUser = sUserRepo.findByUsername(pUser.getUsername());
		if(tUser != null) {
			if(BCrypt.checkpw(pUser.getPassword(), tUser.getPassword())) {
				Map<String, Object> tResult = new HashMap<>();
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
	
	public Map<String, Object> update(UserUpdateDto pUserDto, int pId) {
		User tUser = sUserRepo.getOne(pId);
		tUser.setEmail(pUserDto.getEmail());
		tUser.setPicture(pUserDto.getPicture());
		sUserRepo.save(tUser);
		return ResponseMap.getNewMap("user", new UserPublicDto(tUser));
	}
}
