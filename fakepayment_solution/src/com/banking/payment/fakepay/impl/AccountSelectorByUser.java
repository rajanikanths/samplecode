package com.banking.payment.fakepay.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.banking.payment.fakepay.PaymentSystemAccount;
import com.banking.payment.fakepay.PaymentSystemUser;

class AccountSelectorByUser {

	private final MemoryDatastore memoryDataStore;

	AccountSelectorByUser(MemoryDatastore memoryDataStore) {
		this.memoryDataStore = memoryDataStore;
	}

	Iterator<PaymentSystemAccount> getAccounts(UserSelection selection) {
		Iterator<PaymentSystemAccount> accounts =
				this.memoryDataStore.getAllAcounts();
		List<PaymentSystemAccount> selectedAccounts = new ArrayList<>();
		while (accounts.hasNext()) {
			PaymentSystemAccount account = accounts.next();
			if (satisfies(selection, account.getAccountUsers())) {
				selectedAccounts.add(account);
			}
		}
		return selectedAccounts.iterator();
	}

	boolean satisfies(UserSelection predicate, Iterator<PaymentSystemUser> iter) {
		while (iter.hasNext()) {
			PaymentSystemUser user = iter.next();
			if (predicate.satisfies(user)) {
				return true;
			}
		}
		return false;

	}
}