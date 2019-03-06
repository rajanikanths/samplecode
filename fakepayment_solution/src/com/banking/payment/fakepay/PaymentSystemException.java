package com.banking.payment.fakepay;
public class PaymentSystemException extends Exception {

	private static final long serialVersionUID = 1L;

	public PaymentSystemException(String message) {
		super(message);
	}

	public PaymentSystemException(String description, Throwable cause) {
		super(description, cause);
	}
}
