package com.revature.lostchapterbackend.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.revature.lostchapterbackend.model.User;

@Aspect
@Component
public class SecurityAspect {
	//This file is responsible for the authentication and validation of the users role. 
		//The user must be logged in and of the role Admin or Customer in order to proceed
		//This file relies off of the AuthenticationController.java to actually sign up/signin etc

	private Logger logger = LoggerFactory.getLogger(SecurityAspect.class);

	@Autowired
	private HttpServletRequest req;

	@Around("@annotation(com.revature.lostchapterbackend.annotation.AuthorizedUser)")
	public Object protectEndpointUsersOnly(ProceedingJoinPoint pjp) throws Throwable {

		HttpSession session = req.getSession();
		//gets the current user data
		User currentlyLoggedInUser = (User) session.getAttribute("currentUser");
		//checks to ensure that the user is logged in
		if (currentlyLoggedInUser == null) {
			return ResponseEntity.status(401).body("You are not currently logged in");
		}

		Object returnValue = pjp.proceed();
		return returnValue;
	}

	@Around("@annotation(com.revature.lostchapterbackend.annotation.Customer)")
	public Object protectEndpointCustomerOnly(ProceedingJoinPoint pjp) throws Throwable {

		HttpSession session = req.getSession();
		//gets the current user data
		User currentlyLoggedInUser = (User) session.getAttribute("currentUser");
		//checks to ensure that the user is logged in
		if (currentlyLoggedInUser == null) {
			return ResponseEntity.status(401).body("You are not currently logged in");
		}
		//checks the users role to see if they can access their information
		if (!currentlyLoggedInUser.getRole().equals("Customer")) {
			return ResponseEntity.status(401)
					.body("You are logged in, but only customers are allowed to access this endpoint");
		}

		Object returnValue = pjp.proceed();
		return returnValue;

	}

	@Around("@annotation(com.revature.lostchapterbackend.annotation.Admin)")
	public Object protectEndpointAdminOnly(ProceedingJoinPoint pjp) throws Throwable {

		HttpSession session = req.getSession();
		//gets the current user data
		User currentlyLoggedInUser = (User) session.getAttribute("currentUser");
		//checks to ensure that the user is logged in
		if (currentlyLoggedInUser == null) {
			return ResponseEntity.status(401).body("You are not currently logged in");
		}
		//checks the users role to see if they can access admin controls
		if (!currentlyLoggedInUser.getRole().equals("Admin")) {
			return ResponseEntity.status(401)
					.body("You are logged in, but only admins are allowed to access this endpoint");
		}

		Object returnValue = pjp.proceed();
		return returnValue;

	}

	@Around("@annotation(com.revature.lostchapterbackend.annotation.AdminAndCustomer)")
	public Object protectEndpointAdminAndConsumerOnly(ProceedingJoinPoint pjp) throws Throwable {

		HttpSession session = req.getSession();
		//gets the current user data
		User currentlyLoggedInUser = (User) session.getAttribute("currentUser");
		//checks to ensure that the user is logged in
		if (currentlyLoggedInUser == null) {
			return ResponseEntity.status(401).body("You are not currently logged in");
		}
		//checks the users role to see if the user is a valid role.
		if (!currentlyLoggedInUser.getRole().equals("Admin") && (!currentlyLoggedInUser.getRole().equals("Customer"))) {
			return ResponseEntity.status(401)
					.body("You are logged in, but only admins and customers are allowed to access this endpoint");
		}

		Object returnValue = pjp.proceed();
		return returnValue;

	}

}
