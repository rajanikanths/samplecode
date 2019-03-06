package com.banking.payment.fakepay.impl;

import com.banking.payment.fakepay.PaymentSystemUser;

public interface UserSelection {

	boolean satisfies(PaymentSystemUser user);

}
