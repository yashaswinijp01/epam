package com.epam.passwordmanagementtool.exception;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(String message)
	{
		super(message);
	}

}
