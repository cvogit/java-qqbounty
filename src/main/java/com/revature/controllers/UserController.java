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
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.services.UserService;

@RestController
@RequestMapping(path = "users")
public class UserController {

	@Autowired
	private UserService us;

	@PostMapping
	public User save(@RequestBody User pUser) {
		return us.save(pUser);
	}
	
	
	@GetMapping
	public List<User> findAll() {
		return us.findAll();
	}

	@GetMapping("{id}")
	public User findById(@PathVariable int id) {
		return us.findById(id);
	}

	@PutMapping
	public User update(@Valid @RequestBody User user) {
		return us.save(user);
	}
}
