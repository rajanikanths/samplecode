package com.banking.payment.fakepay.impl;

import com.banking.payment.fakepay.PaymentSystemException;

/** will turn on when remote invocation is needed */
@SuppressWarnings("serial")
public class BalanceException extends PaymentSystemException {

	public BalanceException(String message) {
		super(message);
	}

}
