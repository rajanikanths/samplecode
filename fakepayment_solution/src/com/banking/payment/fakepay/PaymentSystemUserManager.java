package com.banking.payment.fakepay;
public interface PaymentSystemUserManager {

	PaymentSystemUser createUser(String firstName, String lastName,
			String emailAddress) throws PaymentSystemException;
}
