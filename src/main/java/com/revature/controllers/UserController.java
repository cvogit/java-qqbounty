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

import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.JwtUtil;
import com.revature.util.ResponseMap;

@RestController
@RequestMapping(path = "users")
public class UserController {

	@Autowired
	private UserService sUserService;
	
	@Autowired
	private ResponseMap sResponseMap;

	@PostMapping
	public User save(@RequestBody User pUser) {
		return sUserService.save(pUser);
	}
	
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> login(@RequestBody User pUser, HttpServletRequest req) {
		Map<String, Object> tResult = (Map<String, Object>) sUserService.login(pUser);
		if(tResult == null) {
			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(sResponseMap.getGoodResponse(tResult));
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> findAll(HttpServletRequest req) throws IOException {
		if(!JwtUtil.isRequestFromAdmin(req)) {
			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
		}
		Map<String, Object> tResult = (Map<String, Object>) sUserService.findAll();
		if(tResult == null) {
			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(sResponseMap.getGoodResponse(tResult));
	}

	@SuppressWarnings("unchecked")
	@GetMapping("{id}")
	public ResponseEntity<Map<String, Object>> findById(@PathVariable int id, HttpServletRequest req) throws IOException {
		if(!JwtUtil.isRequestFromSelf(req, id)) {
			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
		}
		Map<String, Object> tResult = (Map<String, Object>) sUserService.findById(id);
		if(tResult == null) {
			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(sResponseMap.getGoodResponse(tResult));
	}

	@SuppressWarnings("unchecked")
	@PatchMapping("{id}")
	public ResponseEntity<Map<String, Object>> update(@PathVariable int id, @Valid @RequestBody User pUser, HttpServletRequest req) throws IOException {
		if(!JwtUtil.isRequestFromSelf(req, id)) {
			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
		}
		pUser.setUser_id(id);
		Map<String, Object> tResult = (Map<String, Object>) sUserService.update(pUser);
		if(tResult == null) {
			return ResponseEntity.badRequest().body(sResponseMap.getBadResponse());
		}
		return ResponseEntity.ok().body(sResponseMap.getGoodResponse(tResult));
	}
}
