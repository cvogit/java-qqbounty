package com.revature.util;

import java.io.IOException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.revature.models.User;

public class JwtUtil {

	/**
	 * Create a Jwt and attach user is as private claim
	 * 
	 * @param User
	 * @return String
	 * @throws IOException 
	 * @throws SQLException
	 */
	public static String createJwt(User pUser) throws IOException {
		String tJwt;
		String tSecret = System.getenv("jwt_secret");
		System.out.println(tSecret);
		Algorithm algorithm = Algorithm.HMAC256(tSecret);
		tJwt = JWT.create()
		.withClaim("username", 	pUser.getUsername())
		.withClaim("role_id", 	pUser.getRole_id())
		.withIssuer("auth0")
		.sign(algorithm);
		
		return tJwt;
	}
}
