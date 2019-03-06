package com.banking.payment.fakepay.impl;

import com.banking.payment.fakepay.PaymentSystemException;

/** Marker to differentiate exceptions */
@SuppressWarnings("serial")
public class AdminException extends PaymentSystemException {

	public AdminException(String message) {
		super(message);
	}

}
