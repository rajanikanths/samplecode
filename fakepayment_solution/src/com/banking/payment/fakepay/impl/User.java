package com.banking.payment.fakepay.impl;

import com.banking.payment.fakepay.PaymentSystemUser;

public class User implements PaymentSystemUser {

	private final String firstname;
	private final String lastname;
	private final String email;

	public User(final String firstname, final String lastname, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;

	}

	@Override
	public String getFirstName() {
		return firstname.trim();
	}

	@Override
	public String getLastName() {
		return lastname.trim();
	}

	@Override
	public String getEmailAddress() {
		return email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0
				: email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + "]";
	}

}
