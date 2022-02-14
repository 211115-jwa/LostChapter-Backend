package com.revature.lostchapterbackend.exceptions;

public class ISBNAlreadyExists extends Exception {

	/**This exception is thrown when an admin is adding a new book, but the book's unique isbn number is already in the database
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
