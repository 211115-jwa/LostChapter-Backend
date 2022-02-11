package com.revature.lostchapterbackend.exceptions;

public class ISBNAlreadyExists extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ISBNAlreadyExists() {
		super();
		
	}

	public ISBNAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public ISBNAlreadyExists(String message, Throwable cause) {
		super(message, cause);
	
	}



	
}
