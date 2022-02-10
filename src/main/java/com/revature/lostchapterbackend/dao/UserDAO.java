package com.revature.lostchapterbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.lostchapterbackend.model.User;

@Repository
public interface UserDAO extends JpaRepository <User, Integer>{

	public User findByEmail(String email);
	public User findByUsername(String username);

}
