package com.revature.lostchapterbackend.exceptions;

public class SaleDiscountRateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaleDiscountRateException() {
		super();
	}

	public SaleDiscountRateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SaleDiscountRateException(String message, Throwable cause) {
		super(message, cause);
	}


}
