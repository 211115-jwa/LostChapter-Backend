package com.revature.lostchapterbackend.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.lostchapterbackend.dao.UserDAO;
import com.revature.lostchapterbackend.exceptions.InvalidLoginException;
import com.revature.lostchapterbackend.exceptions.UserNotFoundException;
import com.revature.lostchapterbackend.exceptions.UsernameAlreadyExists;
import com.revature.lostchapterbackend.model.User;
import com.revature.lostchapterbackend.utility.HashUtil;

@Service
public class UserServiceImpl implements UserService {


	private UserDAO userDao;
	

	@Autowired
	public UserServiceImpl(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	@Override
	@Transactional
	public User register(User newUser) throws UsernameAlreadyExists {
		try 
		{
			newUser = userDao.save(newUser);
			return newUser;
		} catch(Exception e) {
			if(e.getMessage() != null && e.getMessage().contains("unique"))
				
				throw new UsernameAlreadyExists("Username Already Exists! Try Again!");
			
			else return null;
		}	
		
	}

	
	@Override
	@Transactional
	public User login(String username, String password) throws UserNotFoundException, InvalidLoginException{
		User userFromDatabase = userDao.findByUsername(username);
		if (userFromDatabase != null && userFromDatabase.getPassword().equals(password)) 
		{
			return userFromDatabase;
		}else if (userFromDatabase == null )
		{
			throw new UserNotFoundException();	
		}else
		{
			throw new InvalidLoginException();
		}
		
	}

	
	@Override
	@Transactional
	public User getUserById(int userId) {
		Optional<User> user = userDao.findById(userId);
		if (user.isPresent()) return user.get();
		else return null;
	}

	@Override
	@Transactional
	public User getUserByEmail(String email) {
		User user = userDao.findByEmail(email);
		return user;
				
	}

	@Override
	@Transactional
	public User getUserByUsername(String username) {
		User user = userDao.findByUsername(username);
		return user;
	}

	@Override
	@Transactional()
	public User update(User user) {
		if (userDao.existsById(user.getUserId())) {
			userDao.save(user);
			user = userDao.findById(user.getUserId()).get();
			return user;
		}
		return null;
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		User userFromDatabase = userDao.findById(user.getUserId()).get();
		if(userFromDatabase != null) {
			userDao.delete(userFromDatabase);
		}
		
	}


	@Override
	@Transactional
	public String passwordHasher(String password) throws NoSuchAlgorithmException {
		String algorithm = "SHA-256";
		String hashedPassword = HashUtil.hashInputPassword(password.trim(), algorithm);
		return hashedPassword;
	}


	
}


