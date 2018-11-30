package com.revature.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.revature.annotations.JwtVerify;

public class increaseWalletPost {

	@JwtVerify
    public void sendIt(int walletId, int balance, int amount) {
    	  
    	RestTemplate restTemplate = new RestTemplate();
    	   String body = "{\"walletId\":" + walletId 
    			   + ", \"balance\":" + (balance+amount) + "}";
    	  
    	   System.out.println(body);
    	   HttpHeaders headers = new HttpHeaders();
    	   headers.add("Content-Type", "application/json");
    	   HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
    	   ResponseEntity <String> res = restTemplate.exchange(
    	       "http://localhost:8088/wallets", HttpMethod.POST, entity, String.class);
    	   
    }
}
