package com.revature.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
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
		Calendar tCalendar = Calendar.getInstance();
		tCalendar.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY + 2);
		Date tExpDate = tCalendar.getTime();
		String tJwt;
		String tSecret = System.getenv("jwt_secret");
		Algorithm algorithm = Algorithm.HMAC256(tSecret);
	
		tJwt = JWT.create()
		.withClaim("username", 	pUser.getUsername())
		.withClaim("user_id", 	pUser.getUser_id())
		.withClaim("role_id", 	pUser.getRole_id())
		.withIssuer("auth0")
		.withExpiresAt(tExpDate)
		.sign(algorithm);
		
		return tJwt;
	}
	
	/**
	 * Verify Jwt is active
	 * 
	 * @param req
	 * @return Boolean
	 * @throws IOException 
	 * @throws SQLException
	 */
	public static boolean jwtVerify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if(req.getHeader("Authorization") != null) {
			String[] tToken = req.getHeader("Authorization").split(" ");
			if(tToken.length == 2) {
				String tJwt = tToken[1];
				String tSecret = System.getenv("jwt_secret");
				Algorithm algorithm = Algorithm.HMAC256(tSecret);
				try {
				    JWTVerifier verifier = JWT.require(algorithm)
				        .withIssuer("auth0")
				        .build();
				    DecodedJWT jwt = verifier.verify(tJwt);
				} catch (JWTVerificationException exception){
					return false;
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verify request of user id is from self
	 * 
	 * @param req
	 * @return Boolean
	 * @throws IOException 
	 * @throws SQLException
	 */
	public static Boolean isRequestFromSelf(HttpServletRequest req, HttpServletResponse resp, int pId) throws IOException {
		if(!jwtVerify(req, resp))
			return false;
		if(extractUserId(req) == pId)
			return true;
		else
			return false;
	}
	
	/**
	 * Verify request of user id is from self
	 * 
	 * @param req
	 * @return Boolean
	 * @throws IOException 
	 * @throws SQLException
	 */
	public static int extractUserId(HttpServletRequest req) {
		if(req.getHeader("Authorization") != null) {
			String[] tToken = req.getHeader("Authorization").split(" ");
			if(tToken.length == 2) {
				String tJwt = tToken[1];
				try {
					DecodedJWT jwt = JWT.decode(tJwt);
				    Claim claim = jwt.getClaim("user_id");
				    return claim.asInt();
				} catch (JWTVerificationException exception){
					return 0;
				}
			}
		}
		return 0;
	}
}
