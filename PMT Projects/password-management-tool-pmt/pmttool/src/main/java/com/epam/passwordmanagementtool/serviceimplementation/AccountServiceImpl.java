package com.epam.passwordmanagementtool.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.passwordmanagementtool.dto.AccountDto;
import com.epam.passwordmanagementtool.entity.Account;
import com.epam.passwordmanagementtool.exception.AccountNotFoundException;
import com.epam.passwordmanagementtool.exception.NoAccountsException;
import com.epam.passwordmanagementtool.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	public AccountRepository accountRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Integer addAccount(AccountDto accountDTO) {
		
		Account accountEntity = mapper.map(accountDTO, Account.class);
		
		Account account= accountRepository.save(accountEntity);
		  
		 return account.getId();
		 
	
		

	}
    
	
	
	@Override
	public List<AccountDto> getAllAccounts() throws NoAccountsException {
		List<Account> accountEntity = accountRepository.findAll();
		List<AccountDto> accountsdto=new ArrayList<>();
		for (Account account : accountEntity) {
			AccountDto dto=mapper.map(account,AccountDto.class);
			accountsdto.add(dto);
		}
		if (accountsdto.isEmpty()) {
			throw new NoAccountsException("Accounts are not Available");
		} else {
			return accountsdto;
		}

	}

	@Override
	public AccountDto editAccount(int id, AccountDto accountDTO) throws AccountNotFoundException {
		
		Optional<Account> acc = accountRepository.findById(id);
		
		if (acc.isPresent()) {
			Account updatedAccount = acc.get();
			updatedAccount.setAccountName(accountDTO.getAccountName());
			updatedAccount.setAccountPassword(accountDTO.getAccountPassword());
			updatedAccount.setAccountUserName(accountDTO.getAccountUserName());
			updatedAccount.setUrl(accountDTO.getUrl());
			accountRepository.save(updatedAccount);
			
			return mapper.map(updatedAccount,AccountDto.class);

		} else {
			throw new AccountNotFoundException("Account to be updated is not found");
		}

	}

	@Override
	public String deleteAccount(int id) throws AccountNotFoundException {
		Optional<Account> deleteAccountEntity = accountRepository.findById(id);
		if (deleteAccountEntity.isPresent()) {
			Account deleteAccount=deleteAccountEntity.get();
			accountRepository.delete(deleteAccount);
			return "Successfully Deleted";
		} else {
			throw new AccountNotFoundException("Account with id " + id + " is not present");
		}

	}

	@Override
	public AccountDto getAccountById(int id) throws AccountNotFoundException {

		Optional<Account> acc = accountRepository.findById(id);
		if (acc.isPresent()) {
			return mapper.map(acc.get(),AccountDto.class);
		} else {
			throw new AccountNotFoundException("Account with id " + id + " is not found");
		}

	}
	
	public List<AccountDto> getAccountsByCategoryName(String categoryName)
	{
		List<Account> accounts=accountRepository.findAccountsByCategoryName(categoryName);
		List<AccountDto> accountsdto=new ArrayList<>();
		for (Account account : accounts) {
			AccountDto dto=mapper.map(account,AccountDto.class);
			accountsdto.add(dto);
		}
		return accountsdto;
		
	}

}
