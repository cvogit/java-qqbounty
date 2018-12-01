package com.revature.services;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Product;
import com.revature.models.UserProduct;
import com.revature.models.Wallet;
import com.revature.repos.ProductRepo;
import com.revature.repos.UserProductRepo;
import com.revature.util.JwtUtil;
import com.revature.util.ResponseMap;
import com.revature.util.TsUtil;

@Service
public class ProductService {
	
	@Autowired
	private UserService us;
	
	@Autowired
	private WalletService ws;
	
	@Autowired
	private ProductRepo sProductRepo;
	
	@Autowired
	private UserProductRepo sUserProductRepo;
		
	public Map<String, Object> findAll() {
		return ResponseMap.getNewMap("product_list", sProductRepo.findAll());
	}
		
	//get user wallet balance
	public Map<String, Object>  findById(int id) {
		return ResponseMap.getNewMap("product", sProductRepo.findById(id));
	}
		
	public Map<String, Object> save(int productId, HttpServletRequest req) {
		//extract user id
		JwtUtil j = new JwtUtil();
		int id = j.extractUserId(req);
	
		UserProductService ups = new UserProductService();
		UserProduct up = new UserProduct();
		//save purchase into user products table
		up.setProductId(productId);//1-> basic, 2 -> deluxe, 3 -> wombo combo
		System.out.println(up.getProductId());
		up.setUserId(id);
		up.setPurchaseDate(TsUtil.stampIt());
		
		String userData = us.findById(id).toString();

		// extract wallet id from user object
		Pattern uPattern = Pattern.compile("walletId=(.*?),");
		Matcher uMatcher = uPattern.matcher(userData);
		uMatcher.find();
		int xWalletId = Integer.parseInt(uMatcher.group(1));
		
		//get product object and credit amount
		Product product = sProductRepo.getOne(productId);
		
		// find wallet by id
		Wallet walletData = ws.getOne(xWalletId);
		walletData.setBalance(walletData.getBalance() + product.getProductCredit());
		ws.update(walletData);
		return ResponseMap.getNewMap("user_product", sUserProductRepo.save(up));
	}	
}
