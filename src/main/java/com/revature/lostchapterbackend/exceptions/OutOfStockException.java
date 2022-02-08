package com.revature.lostchapterbackend.exceptions;

public class OutOfStockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutOfStockException() {
		super();
	}

	public OutOfStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OutOfStockException(String message, Throwable cause) {
		super(message, cause);
	}



}
