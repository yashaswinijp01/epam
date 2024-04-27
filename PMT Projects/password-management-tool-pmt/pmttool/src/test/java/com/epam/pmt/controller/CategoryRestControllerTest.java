package com.epam.pmt.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
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

import com.epam.passwordmanagementtool.controller.CategoryRestController;
import com.epam.passwordmanagementtool.dto.CategoryDto;
import com.epam.passwordmanagementtool.exception.CategoryNotFoundException;
import com.epam.passwordmanagementtool.exception.NoCategoriesFoundException;
import com.epam.passwordmanagementtool.exceptionhandler.CustomExceptionHandler;
import com.epam.passwordmanagementtool.repository.AccountRepository;
import com.epam.passwordmanagementtool.service.AccountService;
import com.epam.passwordmanagementtool.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(MockitoExtension.class)
 class CategoryRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private CategoryService categoryService;

	@InjectMocks
	private CategoryRestController categoryRestController;

	@Autowired
	ObjectMapper mapper;
	
	@Mock
	AccountRepository accountRepo;
	
	@Mock
	private AccountService accountService;

	
	@BeforeEach
	public void setup() {
		mockMvc=MockMvcBuilders.standaloneSetup(categoryRestController).setControllerAdvice(CustomExceptionHandler.class).build();
		mapper=new ObjectMapper();
	}

	
	
	
	@Test
	void testEditCategory() throws Exception {
		
		CategoryDto category=new CategoryDto();
        category.setCategoryId(1);
        category.setCategoryName("yahoo");
        Mockito.when(categoryService.editCategory(ArgumentMatchers.anyInt(),ArgumentMatchers.any())).thenReturn(category);
        String json = mapper.writeValueAsString(category);
        mockMvc.perform(put("/categories/{categoryId}",1).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId", equalTo(1)))
                .andExpect(jsonPath("$.categoryName", equalTo("yahoo")));
	}
	@Test
	void testDeleteCategory() throws Exception {
		CategoryDto category=new CategoryDto();
        category.setCategoryId(1);
        category.setCategoryName("yahoo");
        doNothing().when(accountRepo).deleteAccountByCategoryId(1);
		Mockito.when(categoryService.deleteCategory(Mockito.anyInt())).thenReturn("Category is deleted");
		MvcResult requestResult = mockMvc.perform(delete("/categories/{categoryId}",1))
                .andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals( "Category is deleted",result);
	}
	@Test
	void testGetAllCategories() throws Exception {
		
		CategoryDto category=new CategoryDto();
        category.setCategoryId(1);
        category.setCategoryName("yahoo");
        List<CategoryDto> list=new ArrayList<>();
        list.add(category);
        Mockito.when(categoryService.getAllCategories()).thenReturn(list);
        mockMvc.perform(get("/categories")).andExpect(status().isOk()).andExpect(jsonPath("$",IsCollectionWithSize.hasSize(1)))
                .andExpect(jsonPath("$[0].categoryName", equalTo("yahoo")));
	}
	@Test
	void testGetAllCategoriesFalse() throws Exception {
		
        Mockito.when(categoryService.getAllCategories()).thenThrow(new NoCategoriesFoundException("No Groups Found"));
        mockMvc.perform(get("/categories")).andExpect(status().isInternalServerError());
	}
	
	@Test
	void testGetCategoryById() throws Exception {
		
		CategoryDto category=new CategoryDto();
        category.setCategoryId(1);
        category.setCategoryName("yahoo");
        Mockito.when(categoryService.getCategoryById(Mockito.anyInt())).thenReturn(category);
        mockMvc.perform(get("/categories/{id}",1)).andExpect(status().isOk())
        .andExpect(jsonPath("$.categoryName", equalTo("yahoo")));
	}
	@Test
	void testGetCategoryByIdFalse() throws Exception {
		
		CategoryDto category=new CategoryDto();
        category.setCategoryId(1);
        category.setCategoryName("yahoo");
        Mockito.when(categoryService.getCategoryById(Mockito.anyInt())).thenThrow(new CategoryNotFoundException("Account with id " + Mockito.anyInt() + " is not found"));
        mockMvc.perform(get("/categories/{id}",1)).andExpect(status().isInternalServerError());
	}
	@Test
	void testAddCategory() throws Exception {

		CategoryDto category=new CategoryDto();
        category.setCategoryId(1);
        category.setCategoryName("yahoo");
        Mockito.when(categoryService.addCategory(Mockito.any())).thenReturn(category);
        String json = mapper.writeValueAsString(category);
        mockMvc.perform(post("/categories").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
                
        
        
	}
	
	
	
	@Test
	void testAddCategoryFalse() throws Exception {

		CategoryDto category=new CategoryDto();
        category.setCategoryId(1);
        category.setCategoryName("ya");
        String json = mapper.writeValueAsString(category);
        mockMvc.perform(post("/categories").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
                
        
        
	}
	
	
	
	
	
}
