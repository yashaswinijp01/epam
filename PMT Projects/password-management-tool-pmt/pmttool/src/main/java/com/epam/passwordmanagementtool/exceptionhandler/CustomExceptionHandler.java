package com.epam.passwordmanagementtool.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.epam.passwordmanagementtool.exception.AccountNotFoundException;
import com.epam.passwordmanagementtool.exception.CategoryNotFoundException;
import com.epam.passwordmanagementtool.exception.NoAccountsException;
import com.epam.passwordmanagementtool.exception.NoCategoriesFoundException;
import com.epam.passwordmanagementtool.exception.UserNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = AccountNotFoundException.class)
	public ResponseEntity<Object> handleAccountNotFoundExceptions(Exception ex) {

		String message = ex.getMessage();

		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	
	@ExceptionHandler(value = NoAccountsException.class)
	public ResponseEntity<Object> handleNoAccountsFoundExceptions(NoAccountsException ex) {

		String message = ex.getMessage();

		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(value = CategoryNotFoundException.class)
	public ResponseEntity<Object> handleCategoryNotFoundExceptions(Exception ex) {

		String message = ex.getMessage();

		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(value = NoCategoriesFoundException.class)
	public ResponseEntity<Object> handleNoCategoriesFoundExceptions(Exception ex ){
		String message = ex.getMessage();

		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex) {

		String message = ex.getMessage();

		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleGeneralExceptions(Exception ex) {

		String message = ex.getMessage();

		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
