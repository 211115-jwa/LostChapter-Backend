package com.revature.lostchapterbackend.exceptions;

public class InvalidLoginException extends Exception {
	
	/**This exception is thrown when the program is unable to find a matching pair of username/password in the database
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidLoginException() {
		super();
	}

	public InvalidLoginException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidLoginException(String message, Throwable cause) {
		super(message, cause);
	}


}
