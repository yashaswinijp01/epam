package com.epam.passwordmanagementtool.service;

import java.util.List;

import com.epam.passwordmanagementtool.dto.AccountDto;
import com.epam.passwordmanagementtool.exception.AccountNotFoundException;
import com.epam.passwordmanagementtool.exception.NoAccountsException;


public interface AccountService {
	
    public Integer addAccount(AccountDto account);
	
	public List<AccountDto> getAllAccounts() throws NoAccountsException ;
		
	public String deleteAccount(int id) throws AccountNotFoundException;
	
	public AccountDto getAccountById(int id) throws AccountNotFoundException;

	public AccountDto editAccount( int id,AccountDto account) throws AccountNotFoundException;
	
	public List<AccountDto> getAccountsByCategoryName(String categoryName);

}
