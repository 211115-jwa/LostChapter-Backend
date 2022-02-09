package com.revature.lostchapterbackend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.lostchapterbackend.exceptions.InvalidLoginException;
import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
import com.revature.lostchapterbackend.exceptions.UsernameAlreadyExists;
import com.revature.lostchapterbackend.model.User;
import com.revature.lostchapterbackend.service.UserService;

@RestController
@RequestMapping(path="/users")
@CrossOrigin("*")
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
	
	@PostMapping(path="/auth")

	public ResponseEntity<String> logIn(@RequestBody Map<String, String> credentials){

		String username = credentials.get("username");
		String password = credentials.get("password");
		
		try {
			User user = userService.login(username, password);
			String token = Integer.toString(user.getUserId());
			return ResponseEntity.ok(token);

		} catch (UserNotFoundException | InvalidLoginException e) {
			return ResponseEntity.notFound().build();
	}
}
	
	@GetMapping(path="/{userid}/auth")
	public ResponseEntity<User> checkLogin(@PathVariable int userid) throws UserNotFoundException{
		try {

			User loggedInPerson =userService.getUserById(userid);
			if(loggedInPerson!=null)
				return ResponseEntity.ok(loggedInPerson);
			else
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	
	@GetMapping(path="/{userid}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) throws UserNotFoundException{
		
		User user = userService.getUserById(userId);
		
		if (user != null) {
		return ResponseEntity.ok(user);
	} else
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path="/email/{email}")
	public ResponseEntity<User> getUserByEmail(@RequestBody String email) throws UserNotFoundException{
		User user = userService.getUserByEmail(email);
		if (user != null) {
		return ResponseEntity.ok(user);
		} else
			return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path="/username/{username}")
	public ResponseEntity<User> getUserByUsername(@RequestBody String username) throws UserNotFoundException{
		User user = userService.getUserByUsername(username);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else
			return ResponseEntity.notFound().build();
	}

	
	@PutMapping(path="/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable int userId,
			@RequestBody User userToEdit) throws UserNotFoundException {
		
		if (userToEdit != null && userToEdit.getUserId() == userId) {
			userToEdit = userService.update(userToEdit);
			
			if (userToEdit != null)
				return ResponseEntity.ok(userToEdit);
			else
				return ResponseEntity.notFound().build();
		} else {
			// conflict: the id doesn't match the id of the user sent
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@DeleteMapping(path="/{userId}")
	public ResponseEntity<Void> deleteUser(@RequestBody User deleteUser) throws UserNotFoundException {
		if (deleteUser != null) {
			userService.deleteUser(deleteUser);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	//public String passwordHasher(String password) throws NoSuchAlgorithmException;
	//not sure how to go about creating the path for this one ^
}
