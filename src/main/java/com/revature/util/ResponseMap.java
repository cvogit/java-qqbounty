package com.revature.util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;

import org.springframework.stereotype.Service;

@Service
public class ResponseMap {
	private Map<String, Object> tResponse;

	public ResponseMap() {
		super();
		this.tResponse = new HashMap<>();
		this.tResponse.put("result", null);
		this.tResponse.put("message", "Default message");
	}
	
	public void setMessage(String pMessage) {
		this.tResponse.put("message", pMessage);
	}
	
	public void setResult(Object pResult) {
		this.tResponse.put("result", pResult);
	}
	
	public Map<String, Object> getResponse() {
		return tResponse;
	}
}