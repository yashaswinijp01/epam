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

import com.epam.passwordmanagementtool.dto.CategoryDto;
import com.epam.passwordmanagementtool.exception.CategoryNotFoundException;
import com.epam.passwordmanagementtool.exception.NoCategoriesFoundException;
import com.epam.passwordmanagementtool.repository.AccountRepository;
import com.epam.passwordmanagementtool.service.AccountService;
import com.epam.passwordmanagementtool.service.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryRestController {
	
	
	@Autowired
	public CategoryService service;
	
	@Autowired
	public AccountRepository accountRepo;
	
	@Autowired
	public AccountService accountService;
	
	@PostMapping()
	public ResponseEntity<String> addCategoryDetails( @Valid @RequestBody CategoryDto categoryDto,Errors errors)  {

		if(errors.hasErrors()) {
			return new ResponseEntity<>(errors.getAllErrors()+"  ",HttpStatus.BAD_REQUEST);
			
		}
		 service.addCategory(categoryDto);

		return new ResponseEntity<>("Group added successfully" , HttpStatus.CREATED);
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<CategoryDto>> getCategoryDetails() throws NoCategoriesFoundException   {

		List<CategoryDto> categories= service.getAllCategories();
		
		return new ResponseEntity<>(categories,HttpStatus.OK);
		
       
	}
	@GetMapping(value = "/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryDetailById(@PathVariable("categoryId") int categoryId) throws  CategoryNotFoundException {

		CategoryDto category = service.getCategoryById(categoryId);
		
		return new ResponseEntity<>(category, HttpStatus.OK);	
		
	}
	@PutMapping(value = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDto> editCategoryDetails(@PathVariable("categoryId") int categoryId,@Valid @RequestBody CategoryDto category) throws  CategoryNotFoundException {

		CategoryDto modifiedCategory= service.editCategory(categoryId,category);

		return new ResponseEntity<>(modifiedCategory, HttpStatus.OK);
	}
	@DeleteMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCategoryDetails(@PathVariable("categoryId") int categoryId) throws  CategoryNotFoundException {
 
		accountRepo.deleteAccountByCategoryId(categoryId);
		
		String msg=service.deleteCategory(categoryId);
		 
		return new ResponseEntity<>(msg, HttpStatus.OK);
		}
	
	
	
}
