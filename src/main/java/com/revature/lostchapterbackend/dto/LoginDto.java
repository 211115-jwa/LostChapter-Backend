package com.revature.lostchapterbackend.dto;

import java.util.Objects;

public class LoginDto {
	//This DTO is used for logging the suer in
	//Its methods are
		//LoginDto: logs the user in
		//Get/Set username
		//Get/Set password
		//hashCode: hashes the user
		//equals: finds a matching user in the database based off of their username and password
		//toString: converts the username and passoword into a sendable value

	private String username;
	private String password;

	public LoginDto() {
		super();
	}

	public LoginDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginDto other = (LoginDto) obj;
		return Objects.equals(username, other.username) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "LoginDto [ausername=" + username + ", password=" + password + "]";
	}

}
