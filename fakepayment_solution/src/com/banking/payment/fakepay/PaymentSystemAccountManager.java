package com.banking.payment.fakepay;
import java.util.Iterator;

public interface PaymentSystemAccountManager {

	PaymentSystemAccount createAccount(PaymentSystemUser user,
			double initialBalance) throws PaymentSystemException;

	PaymentSystemAccount addUserToAccount(PaymentSystemUser user,
			String accountNumber) throws PaymentSystemException;

	Iterator<PaymentSystemAccount> getAllAccounts();

	PaymentSystemAccount getUserAccount(PaymentSystemUser user);

	Iterator<PaymentSystemAccount> findAccountsByFullName(String firstName,
			String lastName);

	Iterator<PaymentSystemAccount> findAccountsByFirstName(String firstName);

	Iterator<PaymentSystemAccount> findAccountsByLastName(String lastName);
}
