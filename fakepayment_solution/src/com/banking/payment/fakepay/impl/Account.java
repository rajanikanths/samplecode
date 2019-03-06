package com.banking.payment.fakepay.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.banking.payment.fakepay.PaymentSystemAccount;
import com.banking.payment.fakepay.PaymentSystemException;
import com.banking.payment.fakepay.PaymentSystemUser;

public class Account implements PaymentSystemAccount {

	private final String accountNumber;
	private final Map<String, PaymentSystemUser> users =
			new TreeMap<String, PaymentSystemUser>();
	private Double balance = 0.0;

	/** Account should have a user */
	public Account(String accountNumber, PaymentSystemUser user,
			Double initialBalance) {
		if (accountNumber.isEmpty()) {
			throw new RuntimeException("Account number cannot be empty");
		}
		this.accountNumber = accountNumber;
		users.put(user.getEmailAddress(), user);
		this.balance = initialBalance;
	}

	@Override
	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public Double getAccountBalance() {
		return balance;
	}

	@Override
	public void incrementAccountBalance(Double amount)
			throws PaymentSystemException {
		if (amount < 0)
			throw new BalanceException(
					"Amount cannot be negative to increment: " + amount);
		this.balance += amount;

	}

	@Override
	public void decrementAccountBalance(Double amount)
			throws PaymentSystemException {
		if (amount < 0)
			throw new BalanceException(
					"Amount cannot be negative to decrement: " + amount);
		this.balance -= amount;

	}

	@Override
	public Iterator<PaymentSystemUser> getAccountUsers() {
		return users.values().iterator();
	}

	@Override
	public void addUser(PaymentSystemUser user) {
		users.put(user.getEmailAddress(), user);
	}
}
