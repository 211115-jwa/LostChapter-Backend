package com.revature.lostchapterbackend.exceptions;

public class CartNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CartNotFoundException(String message) {
		super(message);
	}

	public CartNotFoundException(Throwable cause) {
		super(cause);
	}
}
