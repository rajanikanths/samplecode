package com.banking.payment.fakepay.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.banking.payment.fakepay.PaymentSystemAccount;
import com.banking.payment.fakepay.PaymentSystemUser;

/**
 *
 * Internal to package, all methods package protected
 *
 */
public class MemoryDatastore {

	/**
	 * Convenient to use account number as key, though an attribute, access via
	 * number is prevalent.
	 */
	private Map<String, PaymentSystemAccount> accounts =
			new ConcurrentHashMap<String, PaymentSystemAccount>();

	private Map<PaymentSystemUser, PaymentSystemAccount> userToAccount =
			new ConcurrentHashMap<PaymentSystemUser, PaymentSystemAccount>();

	void add(PaymentSystemAccount account) throws AdminException {

		if (accounts.containsKey(account.getAccountNumber())) {
			throw new AdminException("Account number exists "
					+ account.getAccountNumber());
		}
		accounts.put(account.getAccountNumber(), account);
		account.getAccountUsers().forEachRemaining(user -> {
			userToAccount.put(user, account);// assumption is user to account is 1-1
			});

	}

	PaymentSystemAccount getAccount(String accountNumber) {
		return accounts.get(accountNumber);
	}

	Iterator<PaymentSystemAccount> getAllAcounts() {
		return accounts.values().iterator();
	}

	void addLookupUserToAccount(PaymentSystemUser user, String accountNumber)
			throws AdminException {
		PaymentSystemAccount account = getAccount(accountNumber);
		if (account == null) {
			throw new AdminException("Account number " + accountNumber
					+ " does not exists");
		}
		userToAccount.put(user, account);
	}

	PaymentSystemAccount getUserAccount(PaymentSystemUser user) {
		return userToAccount.get(user);
	}
}
