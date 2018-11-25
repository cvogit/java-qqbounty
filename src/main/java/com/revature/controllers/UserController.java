package com.revature.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public Map<String, Object> login(@RequestBody User pUser, HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> tResult = (Map<String, Object>) sUserService.login(pUser);
		if(tResult == null) {
			resp.setStatus(401);
			sResponseMap.setMessage("Failure");
			return sResponseMap.getResponse();
		}
		sResponseMap.setMessage("Success");
		sResponseMap.setResult(tResult);
		return sResponseMap.getResponse();
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

	@PutMapping("{id}")
	public Map<String, Object> update(@PathVariable int id, @Valid @RequestBody User user, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if(!JwtUtil.isRequestFromSelf(req, resp, id)) {
			resp.setStatus(401);
			sResponseMap.setMessage("Unauthorize request");
			return sResponseMap.getResponse();
		}
		
//		Map<String, Object> tResult = (Map<String, Object>) sUserService.login(pUser);
//		if(tResult == null) {
//			resp.setStatus(401);
//			sResponseMap.setMessage("Failure");
//			return sResponseMap.getResponse();
//		}
		sResponseMap.setMessage("Success");
//		sResponseMap.setResult(tResult);
		return sResponseMap.getResponse();
	}
}
