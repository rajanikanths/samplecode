package com.banking.payment.fakepay;

import java.util.Iterator;

public interface PaymentSystemAccount {

	String getAccountNumber();

	Double getAccountBalance();

	void incrementAccountBalance(Double amount) throws PaymentSystemException;

	void decrementAccountBalance(Double amount) throws PaymentSystemException;

	Iterator<PaymentSystemUser> getAccountUsers();

	void addUser(PaymentSystemUser user) throws PaymentSystemException;
}
