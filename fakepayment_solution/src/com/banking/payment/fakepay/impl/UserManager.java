package com.banking.payment.fakepay.impl;

import com.banking.payment.fakepay.PaymentSystemException;
import com.banking.payment.fakepay.PaymentSystemUser;
import com.banking.payment.fakepay.PaymentSystemUserManager;

public class UserManager implements PaymentSystemUserManager {

	@Override
	public PaymentSystemUser createUser(String firstName, String lastName,
			String emailAddress) throws PaymentSystemException {

		if (isValidName(firstName) && isValidName(lastName)
				&& isValidName(emailAddress)) {// email validation not needed for scope
			return new User(firstName, lastName, emailAddress);
		}
		return null;
	}

	// Would use some String utils...
	private boolean isValidName(String firstName) throws AdminException {
		if (firstName == null || firstName.isEmpty()) {
			throw new AdminException("Name cannot be empty");
		}
		return true;
	}

}
