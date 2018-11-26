package com.revature.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
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
	
	public Map<String, Object> getGoodResponse() {
		this.setMessage("Success");
		return tResponse;
	}
	
	public Map<String, Object> getBadResponse() {
		this.setMessage("Bad request");
		return tResponse;
	}
}