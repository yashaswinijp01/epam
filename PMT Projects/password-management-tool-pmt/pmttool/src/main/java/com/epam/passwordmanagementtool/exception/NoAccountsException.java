package com.epam.passwordmanagementtool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class NoAccountsException extends RuntimeException{
	
	

	public NoAccountsException(String message)
	{
		super(message);
	}

}
