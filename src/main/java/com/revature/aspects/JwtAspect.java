package com.revature.aspects;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import com.revature.util.JwtUtil;
import com.revature.util.ResponseMap;

@Aspect
@Component
public class JwtAspect {

	@Autowired
	private JwtUtil sJwtUtil;
	
	@Around(" @annotation(com.revature.annotations.JwtVerify)")
	public Object verifyJwt(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		System.out.println(request.toString());
		if(!sJwtUtil.jwtVerify(request)) {
			return ResponseEntity.status(401).body(ResponseMap.getBadResponse("Jwt Verifaction Not Passed."));
		}
		return pjp.proceed();
    }
	
	@Around(" @annotation(com.revature.annotations.JwtUserIsSelf)")
	public Object jwtUserIsSelf(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		Map tParams = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if(!sJwtUtil.isRequestFromSelf(request, Integer.parseInt((String) tParams.get("userId")))) {
			System.out.println("thing");
			return ResponseEntity.status(403).body(ResponseMap.getBadResponse("User is not self"));
		}
		System.out.println("Request is good to go");
		return pjp.proceed();
    }
	
	@Around(" @annotation(com.revature.annotations.JwtUserIsAdmin)")
	public Object jwtUserIsAdmin(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		if(!sJwtUtil.isRequestFromAdmin(request)) {
			return ResponseEntity.status(403).body(ResponseMap.getBadResponse("User is not admin"));
		}
		return pjp.proceed();
    }
	
	@Around(" @annotation(com.revature.annotations.JwtUserOwnBounty)")
	public Object jwtUserOwnBounty(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("JKGHKFHJFGJGFJFGJFG");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		Map tParams = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		System.out.println(tParams);
		if(!sJwtUtil.isBountyOwner(request, Integer.parseInt((String) tParams.get("bountyId")))) {
			return ResponseEntity.badRequest().body(ResponseMap.getBadResponse("User owns bounty"));
		}
		return pjp.proceed();
    }
}
