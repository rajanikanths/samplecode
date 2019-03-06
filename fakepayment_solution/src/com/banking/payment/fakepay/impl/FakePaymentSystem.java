package com.banking.payment.fakepay.impl;

import java.util.Iterator;
import java.util.Set;

import com.banking.payment.fakepay.PaymentSystem;
import com.banking.payment.fakepay.PaymentSystemAccount;
import com.banking.payment.fakepay.PaymentSystemAccountManager;
import com.banking.payment.fakepay.PaymentSystemException;
import com.banking.payment.fakepay.PaymentSystemUser;
import com.banking.payment.fakepay.PaymentSystemUserManager;

public class FakePaymentSystem implements PaymentSystem {

	private PaymentSystemAccountManager accountManager = new AccountManager();
	private PaymentSystemUserManager userManager = new UserManager();

	@Override
	public PaymentSystemAccountManager getAccountManager() {
		return accountManager;
	}

	@Override
	public PaymentSystemUserManager getUserManager() {
		return userManager;
	}

	@Override
	public void sendMoney(PaymentSystemUser from, PaymentSystemUser to,
			double amount) throws PaymentSystemException {
		PaymentSystemAccount accountFrom = accountManager.getUserAccount(from);
		if (accountFrom == null)
			throw new PaymentSystemException(from.toString()
					+ " does not exists");
		sendMoney(accountFrom, to, amount);

	}

	private void sendMoney(PaymentSystemAccount accountFrom,
			PaymentSystemUser to, double amount) throws PaymentSystemException {
		PaymentSystemAccount accountTo = accountManager.getUserAccount(to);
		if (accountTo == null)
			throw new PaymentSystemException(to.toString() + " does not exists");

		accountFrom.decrementAccountBalance(amount);
		accountTo.incrementAccountBalance(amount);
	}

	@Override
	public void sendMoney(PaymentSystemUser from, Set<PaymentSystemUser> to,
			double amount) throws PaymentSystemException {
		PaymentSystemAccount accountFrom = accountManager.getUserAccount(from);
		if (accountFrom == null)
			throw new PaymentSystemException(from.toString()
					+ " does not exists");

		double balanceRequired = amount * to.size();
		if (hasEnoughBalance(accountFrom, balanceRequired)) {
			sendMoney(accountFrom, to, amount);
		}
	}

	private void sendMoney(PaymentSystemAccount accountFrom,
			Set<PaymentSystemUser> to, double amount)
			throws PaymentSystemException {
		Iterator<PaymentSystemUser> userIter = to.iterator();
		while (userIter.hasNext()) {
			PaymentSystemUser user = userIter.next();
			sendMoney(accountFrom, user, amount);
		}
	}

	@Override
	public void distributeMoney(PaymentSystemUser from,
			Set<PaymentSystemUser> to, double amount)
			throws PaymentSystemException {
		PaymentSystemAccount accountFrom = accountManager.getUserAccount(from);
		if (accountFrom == null)
			throw new PaymentSystemException(from.toString()
					+ " does not exists");
		if (hasEnoughBalance(accountFrom, amount)) {
			double amountToSend = amount / to.size();
			sendMoney(accountFrom, to, amountToSend);
		}

	}

	private boolean hasEnoughBalance(PaymentSystemAccount accountFrom,
			double balanceRequired) throws BalanceException {
		if (accountFrom.getAccountBalance() < balanceRequired) {
			throw new BalanceException("Account "
					+ accountFrom.getAccountNumber()
					+ " does not have enought balance needed "
					+ balanceRequired + " has "
					+ accountFrom.getAccountBalance());

		}
		return true;
	}

}
