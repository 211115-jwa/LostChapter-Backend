package com.revature.lostchapterbackend.exceptions;

public class TransactionNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public TransactionNotFound(String message) {
		super(message);
	}

	public TransactionNotFound(Throwable cause) {
		super(cause);
	}
}
