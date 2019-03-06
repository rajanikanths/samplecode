package com.banking.payment.fakepay.impl;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

import com.banking.payment.fakepay.PaymentSystemAccount;
import com.banking.payment.fakepay.PaymentSystemAccountManager;
import com.banking.payment.fakepay.PaymentSystemException;
import com.banking.payment.fakepay.PaymentSystemUser;

public class AccountManager implements PaymentSystemAccountManager {

	private final MemoryDatastore datastore = new MemoryDatastore();
	private final AtomicLong lastAccountNumber = new AtomicLong(1064890);// inited for fun.
	private final AccountSelectorByUser accountSelector =
			new AccountSelectorByUser(datastore);

	@Override
	public PaymentSystemAccount createAccount(PaymentSystemUser user,
			double initialBalance) throws PaymentSystemException {
		PaymentSystemAccount account = getUserAccount(user);
		if (account != null) {
			throw new AdminException(user.toString()
					+ " already has an account " + account.getAccountNumber());
		}
		account =
				new Account(Long.toString(lastAccountNumber.incrementAndGet()),
						user, initialBalance);

		datastore.add(account);
		return account;
	}

	@Override
	public PaymentSystemAccount addUserToAccount(PaymentSystemUser user,
			String accountNumber) throws PaymentSystemException {
		PaymentSystemAccount account = datastore.getAccount(accountNumber);
		if (account == null) {
			throw new AdminException(" Account " + accountNumber
					+ " does not exists");
		}
		account.addUser(user);
		datastore.addLookupUserToAccount(user, accountNumber);
		return account;
	}

	@Override
	public Iterator<PaymentSystemAccount> getAllAccounts() {
		return datastore.getAllAcounts();
	}

	@Override
	public PaymentSystemAccount getUserAccount(PaymentSystemUser user) {
		return datastore.getUserAccount(user);
	}

	@Override
	public Iterator<PaymentSystemAccount> findAccountsByFullName(
			String firstName, String lastName) {
		if (firstName == null || lastName == null) {
			return null;
		}
		UserSelection selection = new UserSelection() {
			@Override
			public boolean satisfies(PaymentSystemUser user) {
				return user.getFirstName().equals(firstName)
						&& user.getLastName().equals(lastName);
			}
		};
		return accountSelector.getAccounts(selection);
	}

	@Override
	public Iterator<PaymentSystemAccount> findAccountsByFirstName(
			String firstName) {
		if (firstName == null)
			return null;
		UserSelection selection = new UserSelection() {
			@Override
			public boolean satisfies(PaymentSystemUser user) {
				return user.getFirstName().equals(firstName);
			}
		};
		return accountSelector.getAccounts(selection);
	}

	@Override
	public Iterator<PaymentSystemAccount> findAccountsByLastName(String lastName) {
		if (lastName == null)
			return null;
		UserSelection selection = new UserSelection() {
			@Override
			public boolean satisfies(PaymentSystemUser user) {
				return user.getLastName().equals(lastName);
			}
		};
		return accountSelector.getAccounts(selection);
	}
}
