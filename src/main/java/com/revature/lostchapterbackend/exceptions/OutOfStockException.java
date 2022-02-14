package com.revature.lostchapterbackend.exceptions;

public class OutOfStockException extends Exception {
	//This exception is thrown when a user is attempting to purchase a book, but the quantity left is less than the amount on the order or their is 0 stock
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
