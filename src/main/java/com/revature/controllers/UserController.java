package com.revature.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.JwtUserIsSelf;
import com.revature.annotations.JwtVerify;
import com.revature.dto.UserUpdateDto;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.ResponseMap;

@RestController
@RequestMapping(path = "users")
public class UserController {

	@Autowired
	private UserService sUserService;

	/**
	 * Save a new user
	 * @param pUser
	 * @return ResponseEntity<Map<String, Object>>
	 */
	@PostMapping
	public ResponseEntity<Map<String, Object>> save(@RequestBody User pUser) {
		Map<String, Object> tResult = (Map<String, Object>) sUserService.save(pUser);
		if(tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}
	
	/**
	 * Login a user
	 * @param pUser
	 * @return ResponseEntity<Map<String, Object>>
	 */
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> login(@RequestBody User pUser, HttpServletRequest req) {
		Map<String, Object> tResult = (Map<String, Object>) sUserService.login(pUser);
		if(tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}
	
	/**
	 * Find a list of users
	 * @param
	 * @return ResponseEntity<Map<String, Object>>
	 */
	@GetMapping
	@ResponseBody
	@JwtVerify
	public ResponseEntity<Map<String, Object>> findAll(HttpServletRequest req) throws IOException {
		Map<String, Object> tResult = (Map<String, Object>) sUserService.findAll();
		if(tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}

	/**
	 * Find a user by their id
	 * @param pUser
	 * @return ResponseEntity<Map<String, Object>>
	 */
	@GetMapping("{id}")
	@JwtUserIsSelf
	public ResponseEntity<Map<String, Object>> findById(@PathVariable int id, HttpServletRequest req) throws IOException {
		Map<String, Object> tResult = (Map<String, Object>) sUserService.findById(id);
		if(tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}

	/**
	 * update a user by their id
	 * @param pUser
	 * @return ResponseEntity<Map<String, Object>>
	 */
	@PatchMapping("{id}")
	@JwtUserIsSelf
	public ResponseEntity<Map<String, Object>> update(@PathVariable int id, @Valid @RequestBody UserUpdateDto pUserDto, HttpServletRequest req) throws IOException {
		Map<String, Object> tResult = (Map<String, Object>) sUserService.update(pUserDto, id);
		if(tResult == null) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
	}
	
	//Get all products
		@GetMapping("info")
		@JwtVerify
		public ResponseEntity<Map<String, Object>> userInfo(HttpServletRequest req) {
		Map<String, Object> tResult = (Map<String, Object>) sUserService.userInfo(req);
			if(tResult == null) {
				return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
			}
				return ResponseEntity.ok().body(ResponseMap.getGoodResponse(tResult));
		}
}
