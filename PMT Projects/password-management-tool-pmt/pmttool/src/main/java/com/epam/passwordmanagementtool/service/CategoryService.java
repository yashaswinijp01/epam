package com.epam.passwordmanagementtool.service;

import java.util.List;

import com.epam.passwordmanagementtool.dto.CategoryDto;
import com.epam.passwordmanagementtool.exception.CategoryNotFoundException;
import com.epam.passwordmanagementtool.exception.NoCategoriesFoundException;

public interface CategoryService {
	
	public CategoryDto addCategory(CategoryDto categoryDto);
	
	public CategoryDto editCategory( int categoryId,CategoryDto categoryDto) throws CategoryNotFoundException;
	
	public CategoryDto getCategoryById(int categoryId) throws CategoryNotFoundException;
	
	public String deleteCategory(int  categoryId) throws CategoryNotFoundException;
	
	public List<CategoryDto> getAllCategories() throws NoCategoriesFoundException ;

}
