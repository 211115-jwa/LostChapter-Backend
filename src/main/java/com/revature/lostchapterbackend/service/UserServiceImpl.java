package com.revature.lostchapterbackend.service;

import java.security.NoSuchAlgorithmException;

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
	public int register(User newUser) throws UsernameAlreadyExists {
		try 
		{
			newUser = userDao.save(newUser);
		}catch(Exception e)
		{
			throw new UsernameAlreadyExists("Username Already Exists! Try Again!");
		}	
		return 0;
	}

	@Override
	@Transactional
	public User getUserById(int userId) {
		User user = userDao.getById(userId);
		return user;
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
	@Transactional
	public User update(User user) {
		User updatedUser = userDao.save(user);
		return updatedUser;
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userDao.delete(user);
	}


	@Override
	@Transactional
	public String passwordHasher(String password) throws NoSuchAlgorithmException {
		String algorithm = "SHA-256";
		String hashedPassword = HashUtil.hashInputPassword(password.trim(), algorithm);
		return hashedPassword;
	}


	
}


