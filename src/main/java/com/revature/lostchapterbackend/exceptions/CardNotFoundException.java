package com.revature.lostchapterbackend.exceptions;

public class CardNotFoundException extends Exception {

	/**This exception is thrown when the program is unable to find a credit card in the database with the given id
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
