package com.epam.pmt.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.passwordmanagementtool.controller.AccountController;
import com.epam.passwordmanagementtool.dto.AccountDto;
import com.epam.passwordmanagementtool.dto.CategoryDto;
import com.epam.passwordmanagementtool.exceptionhandler.CustomExceptionHandler;
import com.epam.passwordmanagementtool.service.AccountService;
import com.epam.passwordmanagementtool.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
 class AccountControllerTest {
	
	@Mock
	AccountService accountService;
	
	@InjectMocks
	AccountController accountController;
	
	@Mock
	CategoryService categoryService;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private ObjectMapper mapper;
	
	@BeforeEach
	public void setUp() {
		mockMvc=MockMvcBuilders.standaloneSetup(accountController).setControllerAdvice(CustomExceptionHandler.class).build();
		mapper=new ObjectMapper();
	}
	@Test
	void testaddAccount() throws Exception {
		
		 List<CategoryDto> data = new ArrayList<>();
		    CategoryDto categoryDTO=new CategoryDto();
		    categoryDTO.setCategoryId(1);
		    categoryDTO.setCategoryName("CSE");
		    data.add(categoryDTO);
		    AccountDto account=new AccountDto();
			account.setId(1);
			account.setAccountName("aparna");
			account.setAccountPassword("aparna@A1");
			account.setAccountUserName("aparna");
			account.setUrl("www.google");
			account.setCategory(new CategoryDto(1,"yahoo"));
			

		    //when
		    when(categoryService.getAllCategories()).thenReturn(data);
		    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/addAccount"))
		            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		    //then
		    Map<String, Object> modelMapper = mvcResult.getModelAndView().getModel();
		    @SuppressWarnings("unchecked")
			List<CategoryDto> groupDTOResponse =  (ArrayList<CategoryDto>)modelMapper.get("category");
		    assertEquals(categoryDTO.getCategoryName(), groupDTOResponse.get(0).getCategoryName());
		    assertEquals(categoryDTO.getCategoryId(), groupDTOResponse.get(0).getCategoryId());
			

		
	}
	
	
	
	@Test
	void testSaveAccount() throws Exception {
		AccountDto account=new AccountDto();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("Aparna@123");
		account.setAccountUserName("aparna");
		account.setUrl("www.google.com");
		account.setCategory(new CategoryDto(1,"yahoo"));
				
		when(accountService.addAccount(ArgumentMatchers.any())).thenReturn(1);
		String json = mapper.writeValueAsString(account);
		 
		 mockMvc.perform(post("/addAccountDetails").flashAttr("accountDto", account).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	 
	                
		
	}
	@Test
	void testSaveAccountErrors() throws Exception {
		AccountDto account=new AccountDto();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("Aparna@123");
		account.setAccountUserName("aparna");
		account.setUrl("www.google.com");
		account.setCategory(new CategoryDto(1,"yahoo"));
				
		when(accountService.addAccount(ArgumentMatchers.any())).thenReturn(0);
		String json = mapper.writeValueAsString(account);
		 
 mockMvc.perform(post("/addAccountDetails").flashAttr("accountDto", account).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	 
	                
		
	}
	@Test
	void testSaveAccountFalse() throws Exception {
		AccountDto account=new AccountDto();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("aparna");
		account.setAccountUserName("aparna");
		account.setUrl("www.google.com");
		account.setCategory(new CategoryDto(1,"yahoo"));
		String json = mapper.writeValueAsString(account);
		 
		 mockMvc.perform(post("/addAccountDetails").contentType(MediaType.APPLICATION_JSON).content(json))
	                .andExpect(MockMvcResultMatchers.status().isOk());
		 
	                
		
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
		List<AccountDto> accountdtos=new ArrayList<>();
		accountdtos.add(account);
		when(accountService.getAllAccounts()).thenReturn(accountdtos);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/viewaccounts"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			
		   Map<String, Object> modelMapper = mvcResult.getModelAndView().getModel();
		   @SuppressWarnings("unchecked")
		List<AccountDto> accountDTOResponse = (ArrayList<AccountDto>) modelMapper.get("accounts");
		   assertEquals(accountdtos.get(0).getAccountName(), accountDTOResponse.get(0).getAccountName());
		   assertEquals(accountdtos.get(0).getAccountPassword(), accountDTOResponse.get(0).getAccountPassword());
		   assertEquals(accountdtos.get(0).getAccountUserName(), accountDTOResponse.get(0).getAccountUserName());
		   assertEquals(accountdtos.get(0).getAccountPassword(), accountDTOResponse.get(0).getAccountPassword());
		   assertEquals(accountdtos.get(0).getUrl(), accountDTOResponse.get(0).getUrl());
		   assertEquals(accountdtos.get(0).getCategory(), accountDTOResponse.get(0).getCategory());
		   assertEquals(accountdtos.get(0).getId(), accountDTOResponse.get(0).getId());
		   
			

		
	}
	@Test
	void testGetAllAccountsFalse() throws Exception {
		
		List<AccountDto> accountdtos=new ArrayList<>();		
		when(accountService.getAllAccounts()).thenReturn(accountdtos);
		 mockMvc.perform(MockMvcRequestBuilders.get("/viewaccounts"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		
		
		
		
	}
	@Test
	void testEditAccounts() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/editaccount"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void testUpdateAccount() throws Exception {
		AccountDto account=new AccountDto();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("aparna@A1");
		account.setAccountUserName("aparna");
		account.setUrl("www.google");
		account.setCategory(new CategoryDto(1,"yahoo"));
		AccountDto uaccount=new AccountDto();
		uaccount.setId(1);
		uaccount.setAccountName("aparnaaa");
		uaccount.setAccountPassword("aparna@A1");
		uaccount.setAccountUserName("aparna");
		uaccount.setUrl("www.google");
		uaccount.setCategory(new CategoryDto(1,"yahoo"));
		when(accountService.editAccount(1, account)).thenReturn(uaccount);
		 mockMvc.perform(MockMvcRequestBuilders.post("/editAccountDetails").param("id", "1").flashAttr("accountDto", account)
				 .content(mapper.writeValueAsString(account)))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

	}
	@Test
	void testUpdateAccountFalse() throws Exception {
		AccountDto account=new AccountDto();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("aparna@1");
		account.setAccountUserName("aparna");
		account.setUrl("www.google");
		account.setCategory(new CategoryDto(1,"yahoo"));
		 mockMvc.perform(MockMvcRequestBuilders.post("/editAccountDetails").param("id", "1").flashAttr("accountDto", account)
				 .content(mapper.writeValueAsString(account)))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

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
		
		
	    when(accountService.deleteAccount(1)).thenReturn("Successfully Deleted");
	    
	    mockMvc.perform(MockMvcRequestBuilders.get("/deleteaccount").param("id", "1"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	    
	    
		
	}
	
	@Test
	void testDeleteAccountFalse() throws Exception {
		AccountDto account=new AccountDto();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("aparna@A1");
		account.setAccountUserName("aparna");
		account.setUrl("www.google");
		account.setCategory(new CategoryDto(1,"yahoo"));
		
		
	    when(accountService.deleteAccount(1)).thenReturn(null);
	    
 mockMvc.perform(MockMvcRequestBuilders.get("/deleteaccount").param("id", "1"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	    
	    
		
	}
	
	
	
}
