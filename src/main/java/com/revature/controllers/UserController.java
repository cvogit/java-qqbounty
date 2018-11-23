package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.services.WalletService;
import com.revature.util.ResponseMap;

@RestController
@RequestMapping(path = "users")
public class UserController {

	@Autowired
	private UserService sUserService;
	
//	@Autowired
//	private ResponseMap sResponseMap;

	@PostMapping
	public User save(@RequestBody User pUser) {
		return sUserService.save(pUser);
	}
	
	
	@GetMapping
	@ResponseBody
	public List<User> findAll() {
		return sUserService.findAll();
		
//		// Set response map fields
//		sResponseMap.setMessage("Success");
//		sResponseMap.setResult(tUserList);
//		return sResponseMap.getResponse();
	}

	@GetMapping("{id}")
	public User findById(@PathVariable int id) {
		return sUserService.findById(id);
	}

	@PutMapping
	public User update(@Valid @RequestBody User user) {
		return sUserService.save(user);
	}
}
