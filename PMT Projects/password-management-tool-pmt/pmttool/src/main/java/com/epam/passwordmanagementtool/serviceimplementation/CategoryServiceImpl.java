package com.epam.passwordmanagementtool.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.passwordmanagementtool.dto.CategoryDto;
import com.epam.passwordmanagementtool.entity.Category;
import com.epam.passwordmanagementtool.exception.CategoryNotFoundException;
import com.epam.passwordmanagementtool.exception.NoCategoriesFoundException;
import com.epam.passwordmanagementtool.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {

		Category category = mapper.map(categoryDto, Category.class);
		categoryRepository.save(category);
		return categoryDto;
	}

	@Override
	public CategoryDto editCategory(int categoryId, CategoryDto categoryDto) throws CategoryNotFoundException {
		Optional<Category> c = categoryRepository.findById(categoryId);

		if (c.isPresent()) {
			Category updatedCategory = c.get();
			updatedCategory.setCategoryName(categoryDto.getCategoryName());

			categoryRepository.save(updatedCategory);

			return mapper.map(updatedCategory, CategoryDto.class);

		} else {
			throw new CategoryNotFoundException("Category to be updated is not found");
		}
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) throws CategoryNotFoundException {
		Optional<Category> c = categoryRepository.findById(categoryId);
		if (c.isPresent()) {
			return mapper.map(c.get(), CategoryDto.class);
		} else {
			throw new CategoryNotFoundException("Account with id " + categoryId + " is not found");
		}
	}

	@Override
	public String deleteCategory(int categoryId) throws CategoryNotFoundException {
		Optional<Category> deleteCategoryEntity = categoryRepository.findById(categoryId);
		if (deleteCategoryEntity.isPresent()) {
			Category c = deleteCategoryEntity.get();
			categoryRepository.delete(c);
			return "Successfully Deleted";
		} else {
			throw new CategoryNotFoundException("Account with id " + categoryId + " is not present");
		}
	}

	@Override
	public List<CategoryDto> getAllCategories() throws NoCategoriesFoundException {

		List<Category> categoryEntity = categoryRepository.findAll();
		List<CategoryDto> categoriesdto = new ArrayList<>();
		for (Category category : categoryEntity) {
			CategoryDto dto = mapper.map(category, CategoryDto.class);
			categoriesdto.add(dto);
		}
		if (categoriesdto.isEmpty()) {
			throw new NoCategoriesFoundException("No Groups Found");

		} else {
			return categoriesdto;

		}
	}

	
	@PostConstruct
	public void createDefaultCategory() {
		List<Category> groups = categoryRepository.findAll();
		boolean flag = true;
		for (Category group : groups) {
			if (group.getCategoryName().equals("General")) {
				flag = false;
			}
		}
		if (flag) {
			Category group = new Category(1, "General");
			categoryRepository.save(group);
		}
	}
	 
}
