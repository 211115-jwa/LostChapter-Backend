package com.revature.lostchapterbackend.exceptions;

public class SynopsisInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SynopsisInputException() {
		super();
	}

	public SynopsisInputException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SynopsisInputException(String message, Throwable cause) {
		super(message, cause);
	}



}
