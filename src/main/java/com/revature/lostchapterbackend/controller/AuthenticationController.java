//package com.revature.lostchapterbackend.controller;
//
//import java.security.NoSuchAlgorithmException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.revature.lostchapterbackend.dto.LoginDto;
//import com.revature.lostchapterbackend.dto.SignUpDto;
//import com.revature.lostchapterbackend.exceptions.InvalidLoginException;
//import com.revature.lostchapterbackend.exceptions.InvalidParameterException;
//import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
//import com.revature.lostchapterbackend.model.User;
//import com.revature.lostchapterbackend.service.UserService;
//import com.revature.lostchapterbackend.utility.ValidateUtil;
//
//@RestController
//@CrossOrigin(originPatterns = "*", allowCredentials = "true")
//public class AuthenticationController {
	//This controller is used for the following
		//signing up new users POST /signup 
		//logging in new users POST /login 
		//logging out new users POST /logout 
		//getting the login status GET /loginstatus 
		//updating user information PUT /user
		//deleting users DELETE /user  
//	@Autowired
//	private UserService us;
//	
//	@Autowired
//	private HttpServletRequest req;
//	
//	@Autowired
//	private ValidateUtil validateUtil; 
//	
//	@PostMapping(path = "/signup")
//	public ResponseEntity<Object> signUp(@RequestBody SignUpDto dto) throws InvalidParameterException, NoSuchAlgorithmException, InvalidLoginException {
		//This method allows for new users to be registered into the database
		//This method receives the users information in the form of dto which is then used to create the new row in the database
		//if the insertion into the database is unsuccessful for the following reasons
			//The username and password are already in the database !!!!!NOT IMPLIMENTED!!!!!
			//The user left some blank spots when entering in new data
			//The username, firstname, lastname or email are too long
			//the users age is either too low or two high
			//the user tries to sign up as an incorrect role
//		try {
//			
//			validateUtil.verifySignUp(dto);
//			
//			User user = this.us.createUser(dto);
//			
//			return ResponseEntity.status(201).body("Successfully Sign up");
//			
//		} catch (InvalidParameterException e) {
//			return ResponseEntity.status(400).body(e.getMessage());
//		}
//	}
//	
//	@PostMapping(path = "/login")
//	public ResponseEntity<Object> login(@RequestBody LoginDto dto) throws NoSuchAlgorithmException {
	 	//This method allows for the user to sign in or be denied access
		//if the username and password do not match up to ones found within the database this method will throw an error
//		try {
//			User user = this.us.getUser(dto.getUsername(), dto.getPassword());
//			
//			HttpSession session = req.getSession();
//			session.setAttribute("currentUser", user);
//			
//			return ResponseEntity.status(200).body(user);
//		} catch (InvalidLoginException e) {
//			return ResponseEntity.status(400).body(e.getMessage());
//		}
//	}
//	
//	@PostMapping(path = "/logout")
//	public ResponseEntity<Object> logout() {
		//this method is responsible for logging out the user
//		req.getSession().invalidate();
//		
//		return ResponseEntity.status(200).body("Successfully logged out");
//	}
//	
//	@GetMapping(path = "/loginstatus")
//	public ResponseEntity<Object> checkLoginStatus() {
		//This method is responsible for returning the status of the current user whether or not they are currently logged in or out
//		User currentlyLoggedInUser = (User) req.getSession().getAttribute("currentUser");
//		
//		if (currentlyLoggedInUser != null) {
//			return ResponseEntity.status(200).body(currentlyLoggedInUser);
//		}
//		
//		return ResponseEntity.status(400).body("No one is currently logged in");
//	}
//	
//	@DeleteMapping(path = "/user")
//	public ResponseEntity<Object> deleteUserById() throws UserNotFoundException {
		//This method is responsible for deleting the users information from the database
			//It first checks to ensure that the user is logged in and then proceeds to then call the delete method in the user service 
//		try {
//			HttpSession session = req.getSession();
//			User currentlyLoggedInUser = (User) session.getAttribute("currentUser");
//			
//			if (currentlyLoggedInUser == null) {
//				throw new UserNotFoundException("This user does not exist or is not logged in");
//			}
//			
//			int id = currentlyLoggedInUser.getUserId();
//			us.deleteUserById(currentlyLoggedInUser);
//			req.getSession().invalidate();
//			
//			return ResponseEntity.status(200).body("This user has been successfully deleted by id: " + id);
//		}
//		catch(UserNotFoundException e) {
//			
//			return ResponseEntity.status(400).body(e.getMessage());
//			
//		}
//	}
//	
//	@PutMapping(path = "/user")
//	public ResponseEntity<Object> updateUser(@RequestBody User user) throws UserNotFoundException {
//		//This method is responsible for updating the users information
		//If the user is authenticated and has passed in valid information then the data will be updated
			//Authentication can failed due to blanks, null values, already existing email or invalid birthday
		//!!!!!UPDATING USERNAME, AGE, PASSWORD DO NOT GO THROUGH SAME CHECKS AS WHEN FIRST CREATED!!!!!!!
//		try {
//			validateUtil.verifyUpdateUser(user);
//			HttpSession session = req.getSession();
//			User currentlyLoggedInUser = (User) session.getAttribute("currentUser");
//			
//			User userToBeUpdated = us.updateUser(currentlyLoggedInUser, user);
//			session.setAttribute("currentUser", userToBeUpdated);
//			return ResponseEntity.status(200).body(userToBeUpdated);
//		} catch (InvalidParameterException e) {
//			return ResponseEntity.status(400).body(e.getMessage());
//		}
//	}
//
//}
