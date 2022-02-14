package com.revature.lostchapterbackend.exceptions;

public class EmailNotFoundException extends Exception{
	//This exception is thrown when the program is unable to find an email in the database with the given id

	private static final long serialVersionUID = 1L;

	public EmailNotFoundException(String message) {
		super(message);
	}

	public EmailNotFoundException(Throwable cause) {
		super(cause);
	}
}
