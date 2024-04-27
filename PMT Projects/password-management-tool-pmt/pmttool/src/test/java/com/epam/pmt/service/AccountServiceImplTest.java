package com.epam.pmt.service;

import static org.mockito.ArgumentMatchers.any;



import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.passwordmanagementtool.dto.AccountDto;
import com.epam.passwordmanagementtool.dto.CategoryDto;
import com.epam.passwordmanagementtool.entity.Account;
import com.epam.passwordmanagementtool.entity.Category;
import com.epam.passwordmanagementtool.exception.AccountNotFoundException;
import com.epam.passwordmanagementtool.exception.NoAccountsException;
import com.epam.passwordmanagementtool.repository.AccountRepository;
import com.epam.passwordmanagementtool.service.impl.AccountServiceImpl;


@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

	@Mock
	AccountRepository accountRepository;

	@Mock
	ModelMapper mapper;

	@InjectMocks
	private AccountServiceImpl accountService;

	AccountDto accountDto;
	
	CategoryDto categoryDto;
	
	Account account;
	
	Category category;
	


	@BeforeEach
	public void setUp() {	
		categoryDto = new CategoryDto( "yahoo");
		accountDto = new AccountDto(1, "aparna_g", "Aparna", "Aparna@1234", "https://www.google.com", categoryDto);
		category=new Category("yahoo");
		account=new Account(1,"aparna_g", "Aparna", "Aparna@1234", "https://www.google.com",category);
	}

	@Test
	void testaddAccount() {
		
		when(mapper.map(any(),Mockito.eq(Account.class))).thenReturn(account);
		when(accountRepository.save(any())).thenReturn(account);
		int actualResponse = accountService.addAccount(accountDto);
		
		Assertions.assertEquals(actualResponse,accountService.addAccount(accountDto));
		
	}
	
	@Test
	void testGetAccountById() throws AccountNotFoundException {		
		when(accountRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(account));
		when(mapper.map(any(),Mockito.eq(AccountDto.class))).thenReturn(accountDto);
		AccountDto actualAccount=accountService.getAccountById(Mockito.anyInt());
		Assertions.assertNotNull(actualAccount);
		Assertions.assertEquals(actualAccount, accountService.getAccountById(accountDto.getId()));
	}
	
	@Test
	void testGetAccountByIdFalse() throws AccountNotFoundException {
		when(accountRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Assertions.assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(1));
	}
	 
	
	@Test
	void testGetAllAccounts() throws NoAccountsException {
		
		List<Account> accounts=new ArrayList<>();
		accounts.add(account);
		List<AccountDto> accountsDto=new ArrayList<>();
		accountsDto.add(accountDto);
		when(accountRepository.findAll()).thenReturn(accounts);
		List<AccountDto> accountDtos=accountService.getAllAccounts();
		Assertions.assertNotNull(accountDtos);
				
		
	}
	@Test
	void testGetAllAccountsFalse() throws NoAccountsException {
		List<Account> accounts=new ArrayList<>();
		Assertions.assertTrue(accounts.isEmpty());
		
		when(accountRepository.findAll()).thenReturn(accounts);

		Assertions.assertThrows(NoAccountsException.class,()-> accountService.getAllAccounts());
		
		
				
		
	}
		
	
	@Test
	void testDeleteAccountById() throws AccountNotFoundException {
		when(accountRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(account));
		String message=accountService.deleteAccount(Mockito.anyInt());
		Assertions.assertNotNull(message);
	}
	
	
	@Test
	void testDeleteAccountByIdFalse()  {		
		when(accountRepository.findById(1)).thenReturn(Optional.empty());		
		Assertions.assertThrows(AccountNotFoundException.class,() -> accountService.deleteAccount(1));
	}
	
	@Test
	void testEditAccountFalse()
	{
		when(accountRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Assertions.assertThrows(AccountNotFoundException.class,() -> accountService.editAccount(1,accountDto));
	}
	
	@Test
	void testEditAccount() throws AccountNotFoundException {
		
		when(accountRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(account));
		account.setAccountName("aparna");
		account.setAccountUserName("aparna_123");
		account.setAccountPassword("Aparna@123");
		account.setUrl("www.google.com");
		when(accountRepository.save(any())).thenReturn(account);
		when(mapper.map(any(),Mockito.eq(AccountDto.class))).thenReturn(accountDto);
		AccountDto actualResponse = accountService.editAccount(Mockito.anyInt(),accountDto);
		Assertions.assertNotNull(actualResponse);		
		
		
	}
	@Test
	void testGetAccountByCategoryName() {
		List<Account> accounts=new ArrayList<>();
		accounts.add(account);
		when(accountRepository.findAccountsByCategoryName(Mockito.anyString())).thenReturn(accounts);
		List<AccountDto> accountListDtos=new ArrayList<>();
		accountListDtos.add(accountDto);
		when(mapper.map(any(),Mockito.eq(AccountDto.class))).thenReturn(accountDto);
		List<AccountDto> accountDtos=accountService.getAccountsByCategoryName(Mockito.anyString());
		Assertions.assertNotNull(accountDtos);
	}
	
	
	

	
}
