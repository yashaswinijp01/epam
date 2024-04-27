package com.epam.pmt.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.passwordmanagementtool.controller.AccountRestController;
import com.epam.passwordmanagementtool.dto.AccountDto;
import com.epam.passwordmanagementtool.dto.CategoryDto;
import com.epam.passwordmanagementtool.exception.AccountNotFoundException;
import com.epam.passwordmanagementtool.exception.NoAccountsException;
import com.epam.passwordmanagementtool.exception.NoCategoriesFoundException;
import com.epam.passwordmanagementtool.exceptionhandler.CustomExceptionHandler;
import com.epam.passwordmanagementtool.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class AccountRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private AccountService accountService;

	@InjectMocks
	private AccountRestController accountRestController;

	@Mock
	ObjectMapper mapper;

	

	@BeforeEach
	public void setup() {
		mockMvc=MockMvcBuilders.standaloneSetup(accountRestController).setControllerAdvice(CustomExceptionHandler.class).build();
		mapper=new ObjectMapper();
	}

	
	@Test
	void testEditAccount() throws Exception {
        AccountDto account=new AccountDto();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("aparna@A1");
		account.setAccountUserName("aparna");
		account.setUrl("www.google");
		account.setCategory(new CategoryDto(1,"yahoo"));
        Mockito.when(accountService.editAccount(ArgumentMatchers.anyInt(),ArgumentMatchers.any())).thenReturn(account);
        String json = mapper.writeValueAsString(account);
        mockMvc.perform(put("/accounts/{id}",1).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.accountName", equalTo("aparna")));
	}
	
	@Test
	void testDeleteAccount() throws Exception {
		AccountDto account=new AccountDto();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("aparna@A1");
		account.setAccountUserName("aparna");
		account.setUrl("www.google");
		account.setCategory(new CategoryDto(1,"yahoo"));        
		Mockito.when(accountService.deleteAccount(Mockito.anyInt())).thenReturn("Account is deleted");
		MvcResult requestResult = mockMvc.perform(delete("/accounts/{categoryId}",1))
                .andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals("Account is deleted",result);
	}
	@Test
	void testGetAccountById() throws Exception {
		AccountDto account=new AccountDto();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("aparna@A1");
		account.setAccountUserName("aparna");
		account.setUrl("www.google");
		account.setCategory(new CategoryDto(1,"yahoo"));   
		
		Mockito.when(accountService.getAccountById(Mockito.anyInt())).thenReturn(account);
        mockMvc.perform(get("/accounts/{id}",1)).andExpect(status().isOk())
        .andExpect(jsonPath("$.accountName", equalTo("aparna")));
	}
	@Test
	void testGetAllAccounts() throws Exception {
		AccountDto account=new AccountDto();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("aparna@A1");
		account.setAccountUserName("aparna");
		account.setUrl("www.google");
		account.setCategory(new CategoryDto(1,"yahoo"));
		List<AccountDto> accounts=new ArrayList<>();
		accounts.add(account);
		
		 Mockito.when(accountService.getAllAccounts()).thenReturn(accounts);
	        mockMvc.perform(get("/accounts")).andExpect(status().isOk()).andExpect(jsonPath("$",IsCollectionWithSize.hasSize(1)))
	                .andExpect(jsonPath("$[0].accountName", equalTo("aparna")));
			
		
	}
	
	@Test
	void testGetAllAccountsFalse() throws Exception {
		
		 Mockito.when(accountService.getAllAccounts()).thenThrow(new NoAccountsException("Accounts are not available"));
	        mockMvc.perform(get("/accounts")).andExpect(status().isInternalServerError());
			
		
	}
	@Test
	void testAddAccount() throws Exception {
		AccountDto account=new AccountDto();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("aparna@A1");
		account.setAccountUserName("aparna");
		account.setUrl("www.google");
		account.setCategory(new CategoryDto(1,"yahoo"));
		Mockito.when(accountService.addAccount(account)).thenReturn(1);
        String json = mapper.writeValueAsString(account);
        mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
	@Test
	void testAddAccountFalse() throws Exception {
		AccountDto account=new AccountDto();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("aparna");
		account.setAccountUserName("aparna");
		account.setUrl("www.google");
		account.setCategory(new CategoryDto(1,"yahoo"));
        String json = mapper.writeValueAsString(account);
        mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	@Test
	void testDeleteAccountFalse() throws Exception {
		Mockito.when(accountService.deleteAccount(Mockito.anyInt())).thenThrow(new AccountNotFoundException("Account with"+Mockito.anyInt()+"is not found"));
		 mockMvc.perform(delete("/accounts/{id}",1))
                .andExpect(status().isInternalServerError());
        
		
	}
	@Test
	void testGetByIdFalse() throws Exception {
		Mockito.when(accountService.getAccountById(Mockito.anyInt())).thenThrow(new AccountNotFoundException("Account with"+Mockito.anyInt()+"is not found"));
		 mockMvc.perform(get("/accounts/{id}",1))
                .andExpect(status().isInternalServerError());
        
		
	}
	@Test
	void testGetAccountsByCategoryName() throws Exception {
		CategoryDto category=new CategoryDto();
        category.setCategoryId(1);
        category.setCategoryName("yahoo");
        AccountDto account=new AccountDto();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("aparna@A1");
		account.setAccountUserName("aparna");
		account.setUrl("www.google");
		account.setCategory(category);
		List<AccountDto> accounts=new ArrayList<>();
		accounts.add(account);
		when(accountService.getAccountsByCategoryName(Mockito.anyString())).thenReturn(accounts);
		mockMvc.perform(get("/accounts/categories/{name}","yahoo")).andExpect(status().isOk());
        
		
	}
	@Test
	void testGetAccountsByCategoryNameFalse() throws Exception {
		
		when(accountService.getAccountsByCategoryName(Mockito.anyString())).thenThrow(new NoCategoriesFoundException("No Groups Found"));
		mockMvc.perform(get("/accounts/categories/{name}","yahoo")).andExpect(status().isInternalServerError());
        
		
	}
}
