package com.revature.lostchapterbackend.exceptions;

public class UsernameAlreadyExists extends Exception {

	private static final long serialVersionUID = 1L;

	public UsernameAlreadyExists(String message) {
		super(message);
	}

	public UsernameAlreadyExists(Throwable cause) {
		super(cause);
	}
}
