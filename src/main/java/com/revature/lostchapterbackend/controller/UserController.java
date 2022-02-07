package com.revature.lostchapterbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
import com.revature.lostchapterbackend.exceptions.UsernameAlreadyExists;
import com.revature.lostchapterbackend.model.User;
import com.revature.lostchapterbackend.service.UserService;

@RestController
@RequestMapping(path="/users")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	
	private static UserService userService;
	
	public UserController() {
		super();
	}
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<Void> register(@RequestBody User newUser) throws UsernameAlreadyExists{
		try
		{
			int newUserId = userService.register(newUser);
			newUser = userService.getUserById(newUserId);
			
		}catch (UsernameAlreadyExists e)
		{
			ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (UserNotFoundException e) {
			
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	

}
