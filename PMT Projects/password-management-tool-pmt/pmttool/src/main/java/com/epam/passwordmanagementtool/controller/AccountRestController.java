package com.epam.passwordmanagementtool.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.passwordmanagementtool.dto.AccountDto;
import com.epam.passwordmanagementtool.exception.AccountNotFoundException;
import com.epam.passwordmanagementtool.exception.CategoryNotFoundException;
import com.epam.passwordmanagementtool.exception.NoAccountsException;
import com.epam.passwordmanagementtool.service.AccountService;

@RestController
@RequestMapping(value = "/accounts")
public class AccountRestController {

	
	@Autowired
	private AccountService service;

	@PostMapping()
	public ResponseEntity<String> addAccountDetails( @Valid @RequestBody AccountDto account,Errors errors )  {

		
		if (errors.hasErrors()) {
			return new ResponseEntity<>(errors.getAllErrors() + "  ", HttpStatus.BAD_REQUEST);

		}
		 
		service.addAccount(account);

		return new ResponseEntity<>("Account added successfully" , HttpStatus.CREATED);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<AccountDto>> getAccountDetails() throws NoAccountsException   {

		List<AccountDto> accounts = service.getAllAccounts();
		
		return new ResponseEntity<>(accounts,HttpStatus.OK);
		
       
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AccountDto> getAccountDetailById(@PathVariable("id") int accountId)
			throws AccountNotFoundException {

		AccountDto account = service.getAccountById(accountId);

		return new ResponseEntity<>(account, HttpStatus.OK);

	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteAccountDetails(@PathVariable("id") int accountId) throws AccountNotFoundException {

		String msg = service.deleteAccount(accountId);

		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountDto> editAccountDetails(@PathVariable("id") int accountId, @RequestBody AccountDto account)
			throws AccountNotFoundException {

		AccountDto modifiedAccount = service.editAccount(accountId, account);

		return new ResponseEntity<>(modifiedAccount, HttpStatus.OK);
	}

	@GetMapping(value = "/categories/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AccountDto>> getAccountDetails(@PathVariable("categoryName") String categoryName) throws  CategoryNotFoundException {
 
		List<AccountDto> accounts=service.getAccountsByCategoryName(categoryName);
				
		return new ResponseEntity<>(accounts, HttpStatus.OK);
		}
	
}
