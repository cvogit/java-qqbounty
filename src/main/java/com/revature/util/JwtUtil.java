package com.revature.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.models.Bounty;
import com.revature.models.User;
import com.revature.services.BountyService;

@Component
public class JwtUtil {

	@Autowired
	private BountyService sBountyService;

	/**
	 * Create a Jwt and attach user is as private claim
	 * 
	 * @param User
	 * @return String
	 * @throws IOException 
	 * @throws SQLException
	 */
	public String createJwt(User pUser) throws IOException {
		Calendar tCalendar = Calendar.getInstance();
		tCalendar.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY + 2);
		Date tExpDate = tCalendar.getTime();
		String tJwt;
		String tSecret = System.getenv("jwt_secret");
		Algorithm algorithm = Algorithm.HMAC256(tSecret);
	
		tJwt = JWT.create()
		.withClaim("username", 	pUser.getUsername())
		.withClaim("user_id", 	pUser.getUserId())
		.withClaim("role_id", 	pUser.getRoleId())
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
	public boolean jwtVerify(HttpServletRequest req) throws IOException {
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
				    verifier.verify(tJwt);
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
	public Boolean isRequestFromSelf(HttpServletRequest req, int pId) throws IOException {
		if(!jwtVerify(req))
			return false;
		if(extractUserId(req) == pId)
			return true;
		else
			return false;
	}
	
	/**
	 * Return user id from jwt
	 * 
	 * @param req
	 * @return Boolean
	 * @throws IOException 
	 * @throws SQLException
	 */
	public int extractUserId(HttpServletRequest req) {
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
	
	/**
	 * Return user role from jwt
	 * 
	 * @param req
	 * @return Boolean
	 * @throws IOException 
	 * @throws SQLException
	 */
	public int extractUserRoleId(HttpServletRequest req) {
		if(req.getHeader("Authorization") != null) {
			String[] tToken = req.getHeader("Authorization").split(" ");
			if(tToken.length == 2) {
				String tJwt = tToken[1];
				try {
					DecodedJWT jwt = JWT.decode(tJwt);
				    Claim claim = jwt.getClaim("role_id");
				    return claim.asInt();
				} catch (JWTVerificationException exception){
					return 0;
				}
			}
		}
		return 0;
	}
	
	/**
	 * Verify request is from Admin {2}
	 * 
	 * @param req
	 * @return Boolean
	 * @throws IOException 
	 * @throws SQLException
	 */
	public Boolean isRequestFromAdmin(HttpServletRequest req) throws IOException {
		if(!jwtVerify(req))
			return false;
		if(extractUserRoleId(req) == 2)
			return true;
		else
			return false;
	}

	/**
	 * Verify request is from user that created the county
	 * 
	 * @param req
	 * @return Boolean
	 * @throws IOException 
	 * @throws SQLException
	 */
	public boolean isBountyOwner(HttpServletRequest req, int pBountyId) throws IOException {
		if(!jwtVerify(req))
			return false;
		int tUserId = extractUserId(req);
		Bounty tBounty = sBountyService.findById(pBountyId);
		if(tBounty.getUserId() == tUserId)
			return true;
		return false;
	}
}
