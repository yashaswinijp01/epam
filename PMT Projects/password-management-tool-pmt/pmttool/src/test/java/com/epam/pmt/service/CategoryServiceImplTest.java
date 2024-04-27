package com.epam.pmt.service;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.passwordmanagementtool.dto.CategoryDto;
import com.epam.passwordmanagementtool.entity.Category;
import com.epam.passwordmanagementtool.exception.CategoryNotFoundException;
import com.epam.passwordmanagementtool.exception.NoCategoriesFoundException;
import com.epam.passwordmanagementtool.repository.CategoryRepository;
import com.epam.passwordmanagementtool.service.impl.CategoryServiceImpl;


@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

	@Mock
	CategoryRepository categoryRepository;

	@Mock
	ModelMapper mapper;

	@InjectMocks
	CategoryServiceImpl categoryService;

	CategoryDto categoryDto;

	Category category;

	@BeforeEach
	public void setUp() {

		categoryDto = new CategoryDto();
		category = new Category();

	}

	@Test
	void testAddCategory() {
		when(mapper.map(any(), Mockito.eq(Category.class))).thenReturn(category);
		when(categoryRepository.save(any())).thenReturn(category);
		CategoryDto actualResponse = categoryService.addCategory(categoryDto);
		Assertions.assertNotNull(actualResponse);
		Assertions.assertEquals(actualResponse, categoryService.addCategory(categoryDto));
	}

	@Test
	void testEditCategory() throws CategoryNotFoundException {
		when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(category));
		category.setCategoryName("aparna");
		when(categoryRepository.save(any())).thenReturn(category);
		when(mapper.map(any(), Mockito.eq(CategoryDto.class))).thenReturn(categoryDto);
		CategoryDto actualResponse = categoryService.editCategory(Mockito.anyInt(), categoryDto);
		Assertions.assertNotNull(actualResponse);

	}

	@Test
	void testEditCategoryFalse() {
		when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryService.editCategory(1, categoryDto));
	}

	@Test
	void testCategoryById() throws CategoryNotFoundException {
		when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(category));
		when(mapper.map(any(), Mockito.eq(CategoryDto.class))).thenReturn(categoryDto);
		CategoryDto actualAccount = categoryService.getCategoryById(Mockito.anyInt());
		Assertions.assertNotNull(actualAccount);
		Assertions.assertEquals(actualAccount, categoryService.getCategoryById(Mockito.anyInt()));
	}

	@Test
	void testCategoryByIdFalse() {
		when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryById(1));
	}

	@Test
	void testDeleteCategory() throws CategoryNotFoundException {
		when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(category));
		String message = categoryService.deleteCategory(Mockito.anyInt());
		Assertions.assertNotNull(message);
	}

	@Test
	void testDeleteCategoryFalse() {
		when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteCategory(1));
	}

	@Test
	void testGetAllCategories() throws NoCategoriesFoundException {
		List<Category> categories = new ArrayList<>();
		categories.add(category);
		List<CategoryDto> categoryDtos = new ArrayList<>();
		categoryDtos.add(categoryDto);
		when(categoryRepository.findAll()).thenReturn(categories);
		List<CategoryDto> categoryDtosList = categoryService.getAllCategories();
		Assertions.assertNotNull(categoryDtosList);
	}
	
	@Test
	void testGetAllCategoriesFalse() throws NoCategoriesFoundException {
		List<Category> categories=new ArrayList<>();
		Assertions.assertTrue(categories.isEmpty());
		when(categoryRepository.findAll()).thenReturn(categories);
		List<CategoryDto> categoryDtos=new ArrayList<>();
		Assertions.assertTrue(categoryDtos.isEmpty());
		Assertions.assertThrows(NoCategoriesFoundException.class,()-> categoryService.getAllCategories());
	}
	

	@Test
	void testDefaultCategory()  {
		Category category=new Category(1,"general");
		 when(categoryRepository.findAll()).thenReturn(Stream.of(category).collect(Collectors.toList()));
		    categoryService.createDefaultCategory();
		    Assertions.assertTrue(true);
	}
	@Test
	void testDefaultCategoryFalse()  {
		List<Category> categories=new ArrayList<>();
		when(categoryRepository.findAll()).thenReturn(categories);
		categoryService.createDefaultCategory();
		Assertions.assertTrue(true);
	}
	
	
	

}
