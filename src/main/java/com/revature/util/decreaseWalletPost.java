package com.revature.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.annotations.JwtVerify;

//This util will decrease the balance by 100 internal tokens when creating a new bounty by	
public class decreaseWalletPost {
	@JwtVerify
    public void sendIt(int walletId, int balance) {
    	  
    	RestTemplate restTemplate = new RestTemplate();
    	   String body = "{\"walletId\":" + walletId 
    			   + ", \"balance\":" + (balance-100) + "}";
    	  
    	   System.out.println(body);
    	   HttpHeaders headers = new HttpHeaders();
    	   headers.add("Content-Type", "application/json");
    	   HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
    	   ResponseEntity <String> res = restTemplate.exchange(
    	       "http://localhost:8088/wallets", HttpMethod.POST, entity, String.class);
    	   
    }

}