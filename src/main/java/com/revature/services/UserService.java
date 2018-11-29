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

	/**
	 * Return a list of users or null
	 * @return Map<String, Object>, null
	 */
	public Map<String, Object> findAll() {
		List<User> tUserList = sUserRepo.findAll();
		List<UserPublicDto> tUserPublicDtos = new ArrayList<UserPublicDto>();
		tUserList.forEach(user -> tUserPublicDtos.add(new UserPublicDto(user)));
		
		return ResponseMap.getNewMap("user_list", tUserPublicDtos);
	}

	/**
	 * Return a user or null
	 * @return Map<String, Object>, null
	 */
	public Map<String, Object> findById(int pId) {
		User tUser = sUserRepo.getOne(pId);
		if(tUser == null)
			return null;
		return ResponseMap.getNewMap("user_list", new UserPublicDto(tUser));
	}

	/**
	 * Save a user and return the user or null if unable to save
	 * @return Map<String, Object>, null
	 */
	public Map<String, Object> save(User pUser) {
		pUser.setWalletId(sWalletService.newWallet().getWallet_id());
		pUser.setRoleId(1);
		pUser.setRating(0);
		pUser.hashPassword();

		User tUser = sUserRepo.save(pUser);
		if(tUser == null)
			return null;
		return ResponseMap.getNewMap("user_list", tUser);
	}

	/**
	 * Return a jwt if user can login, else return null
	 * @return Map<String, Object>, null
	 */
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
	
	/**
	 * Update an existing user and return said user or null
	 * @return Map<String, Object>, null
	 */
	public Map<String, Object> update(UserUpdateDto pUserDto, int pId) {
		User tUser = sUserRepo.getOne(pId);
		if(tUser == null)
			return null;
		tUser.setEmail(pUserDto.getEmail());
		tUser.setPicture(pUserDto.getPicture());
		tUser = sUserRepo.save(tUser);
		if(tUser == null)
			return null;
		return ResponseMap.getNewMap("user", new UserPublicDto(tUser));
	}
}
