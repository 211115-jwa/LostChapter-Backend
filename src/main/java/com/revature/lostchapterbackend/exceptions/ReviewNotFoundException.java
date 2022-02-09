package com.revature.lostchapterbackend.exceptions;

public class ReviewNotFoundException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReviewNotFoundException() {
		super();
	}
	public ReviewNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public ReviewNotFoundException(String message, Throwable cause) {
		super(message, cause);


	}



}
