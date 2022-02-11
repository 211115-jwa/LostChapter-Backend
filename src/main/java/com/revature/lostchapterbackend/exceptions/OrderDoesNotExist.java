package com.revature.lostchapterbackend.exceptions;

public class OrderDoesNotExist extends Exception {

	private static final long serialVersionUID = 1L;

	public OrderDoesNotExist(String message) {
		super(message);
	}

	public OrderDoesNotExist(Throwable cause) {
		super(cause);
	}
}
