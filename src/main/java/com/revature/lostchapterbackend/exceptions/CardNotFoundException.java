package com.revature.lostchapterbackend.exceptions;

public class CardNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CardNotFoundException() {
		super();
	}

	public CardNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		}

	public CardNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}


	
	
}
