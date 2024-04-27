package com.epam.pmt.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.passwordmanagementtool.controller.CategoryController;
import com.epam.passwordmanagementtool.dto.AccountDto;
import com.epam.passwordmanagementtool.dto.CategoryDto;
import com.epam.passwordmanagementtool.exception.NoAccountsException;
import com.epam.passwordmanagementtool.exceptionhandler.CustomExceptionHandler;
import com.epam.passwordmanagementtool.repository.AccountRepository;
import com.epam.passwordmanagementtool.service.AccountService;
import com.epam.passwordmanagementtool.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
 class CategoryControllerTest {
	
	@Mock
	CategoryService categoryService;
	
	@InjectMocks
	CategoryController categoryController;
	
	@Mock
	AccountService accountService;
	
	@Mock
	AccountRepository accountRepo;
	
	
	@Mock
	ModelMapper mapper;
	
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@BeforeEach
	public void setUp() {
		mockMvc=MockMvcBuilders.standaloneSetup(categoryController).setControllerAdvice(CustomExceptionHandler.class).build();
		objectMapper=new ObjectMapper();
	}
	
	@Test
	void test_welcome() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/categoryWelcome"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assertNotNull(mvcResult);
	}
	@Test
	void testAddCategory() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/addCategory"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assertNotNull(mvcResult);
	}
    
	@Test
	void testEditCategory() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/editCategory"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assertNotNull(mvcResult);
	}
	@Test
	void testGetAllCategories() throws Exception {
		List<CategoryDto> data = new ArrayList<>();
	    CategoryDto categoryDTO=new CategoryDto();
	    categoryDTO.setCategoryId(1);
	    categoryDTO.setCategoryName("CSE");
	    data.add(categoryDTO);
	    //when
	    when(categoryService.getAllCategories()).thenReturn(data);
	    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/viewcategories"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	    //then
	    Map<String, Object> modelMapper = mvcResult.getModelAndView().getModel();
	    @SuppressWarnings("unchecked")
		List<CategoryDto> groupDTOResponse =  (ArrayList<CategoryDto>)modelMapper.get("categories");
	    assertEquals(categoryDTO.getCategoryName(), groupDTOResponse.get(0).getCategoryName());
	    assertEquals(categoryDTO.getCategoryId(), groupDTOResponse.get(0).getCategoryId());
	}
	@Test
	void testGetAllCategoriesFalse() throws Exception {
		
		when(categoryService.getAllCategories()).thenThrow(new NoAccountsException("No Accounts Found"));
		 mockMvc.perform(MockMvcRequestBuilders.get("/viewcategories"))
		            .andExpect(MockMvcResultMatchers.status().isInternalServerError()).andReturn();
		
		
	}
	
	@Test
	void testGetAccounts() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getAccounts"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assertNotNull(mvcResult);
	}
	@SuppressWarnings("unchecked")
	@Test
	void testGetAllAccountsByCategory() throws Exception {
		CategoryDto categoryDTO=new CategoryDto();
	    categoryDTO.setCategoryId(1);
	    categoryDTO.setCategoryName("yahoo");
	    AccountDto account=new AccountDto();
	    List<AccountDto> accountdtos=new ArrayList<>();
		account.setId(1);
		account.setAccountName("aparna");
		account.setAccountPassword("aparna@A1");
		account.setAccountUserName("aparna");
		account.setUrl("www.google");
		account.setCategory(categoryDTO);
		accountdtos.add(account);
	    //when
	    when(accountService.getAccountsByCategoryName("yahoo")).thenReturn(accountdtos);
	    
	    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getAccountDetails").param("categoryName", "yahoo"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	    //then
	    
	    Map<String, Object> modelMapper = mvcResult.getModelAndView().getModel();
		   List<AccountDto> accountDTOResponse = (ArrayList<AccountDto>) modelMapper.get("accounts");
		   assertNotNull(accountDTOResponse);
		   assertEquals(accountdtos.get(0).getAccountName(), accountDTOResponse.get(0).getAccountName());
		   assertEquals(accountdtos.get(0).getAccountPassword(), accountDTOResponse.get(0).getAccountPassword());
		   assertEquals(accountdtos.get(0).getAccountUserName(), accountDTOResponse.get(0).getAccountUserName());
		   assertEquals(accountdtos.get(0).getAccountPassword(), accountDTOResponse.get(0).getAccountPassword());
		   assertEquals(accountdtos.get(0).getUrl(), accountDTOResponse.get(0).getUrl());
		   assertEquals(accountdtos.get(0).getCategory(), accountDTOResponse.get(0).getCategory());
		   assertEquals(accountdtos.get(0).getId(), accountDTOResponse.get(0).getId());
	}
	@Test
	void testDeleteCategory() throws Exception {
		
		CategoryDto categoryDTO=new CategoryDto();
	    categoryDTO.setCategoryId(1);
	    categoryDTO.setCategoryName("yahoo");
	    
	    doNothing().when(accountRepo).deleteAccountByCategoryId(1);
	    when(categoryService.deleteCategory(1)).thenReturn("Successfully Deleted");
	    
	    mockMvc.perform(MockMvcRequestBuilders.get("/deletecategory").param("categoryId", "1"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	    
	    
		
	}
	@Test
	void testDeleteCategoryFalse() throws Exception {
		CategoryDto categoryDTO=new CategoryDto();
	    categoryDTO.setCategoryId(1);
	    categoryDTO.setCategoryName("yahoo");
	    
	    doNothing().when(accountRepo).deleteAccountByCategoryId(1);
	    when(categoryService.deleteCategory(1)).thenReturn(null);
	     mockMvc.perform(MockMvcRequestBuilders.get("/deletecategory").param("categoryId", "1"))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	    
	    
		
	}
	@Test
	void testSaveCategory() throws Exception {
		CategoryDto categoryDTO=new CategoryDto();
	    categoryDTO.setCategoryId(1);
	    categoryDTO.setCategoryName("yahoo");
	   when(categoryService.addCategory(Mockito.any())).thenReturn(categoryDTO);
		String json = objectMapper.writeValueAsString(categoryDTO);
		 mockMvc.perform(post("/addCategoryDetails").flashAttr("categoryDto", categoryDTO).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		
	}
	@Test
	void testSaveCategoryFalse() throws Exception {
		CategoryDto categoryDTO=new CategoryDto();
	    categoryDTO.setCategoryId(1);
	    categoryDTO.setCategoryName("ya");
	 mockMvc.perform(post("/addCategoryDetails").flashAttr("categoryDto", categoryDTO))
                .andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	@Test
	void testUpdateCategory() throws Exception {
		
		CategoryDto categoryDTO=new CategoryDto();
	    categoryDTO.setCategoryId(1);
	    categoryDTO.setCategoryName("yahoo");
	    
	    CategoryDto ucategoryDTO=new CategoryDto();
	    ucategoryDTO.setCategoryId(1);
	    ucategoryDTO.setCategoryName("google");	    
	    when(categoryService.editCategory(1, categoryDTO)).thenReturn(ucategoryDTO);
	    String json = objectMapper.writeValueAsString(categoryDTO);
		 mockMvc.perform(post("/editCategoryDetails").param("categoryId", "1").flashAttr("categoryDto", categoryDTO).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	}
	
	
	

}
