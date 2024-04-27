package com.epam.passwordmanagementtool.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epam.passwordmanagementtool.dto.AccountDto;
import com.epam.passwordmanagementtool.dto.CategoryDto;
import com.epam.passwordmanagementtool.exception.CategoryNotFoundException;
import com.epam.passwordmanagementtool.exception.NoAccountsException;
import com.epam.passwordmanagementtool.exception.NoCategoriesFoundException;
import com.epam.passwordmanagementtool.repository.AccountRepository;
import com.epam.passwordmanagementtool.service.AccountService;
import com.epam.passwordmanagementtool.service.CategoryService;

@Controller
public class CategoryController {
	
	Logger log=LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService service;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountRepository accountRepo;
	
	
	
	
	@GetMapping("categoryWelcome")
	public ModelAndView groupWelcomeHome()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("categorywelcome");
		return mv;
	}
	
	
	
	@GetMapping("addCategory")
	public ModelAndView groupHome(@ModelAttribute CategoryDto categoryDto)
	{
		ModelAndView mv=new ModelAndView();
		mv.addObject("categoryDto", categoryDto);
		mv.setViewName("addcategory");
		return mv;
	}
	@PostMapping("addCategoryDetails")
	public ModelAndView addCategoryDetails(@Valid @ModelAttribute CategoryDto categoryDto, BindingResult result)
	{
		ModelAndView mv=new ModelAndView();	
		if(result.hasErrors())
		{
			mv.setViewName("addcategory");
			return mv;
		
		}
		service.addCategory(categoryDto);
		mv.addObject("category", categoryDto);
		mv.setViewName("categorywelcome");
		return mv;
		
	}
	
	@RequestMapping("editCategory")
	public ModelAndView loadUpdatePage() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("editcategory");
		return mv;

	}

	@PostMapping("editCategoryDetails")
	public ModelAndView updateCategory(@RequestParam("categoryId") int categoryId,@ModelAttribute CategoryDto categoryDto) throws CategoryNotFoundException, NoCategoriesFoundException {
		ModelAndView mv = new ModelAndView();
		CategoryDto updatedCategory=service.editCategory( categoryId,categoryDto);
		log.info("Updated Category {}", updatedCategory);		
		mv.addObject("Account", updatedCategory);
		List<CategoryDto> categoryList = service.getAllCategories();

		mv.addObject("categories", categoryList);
		service.addCategory(updatedCategory);
		mv.setViewName("fetchallcategories");
		return mv;

	}
	
	

	@RequestMapping("deletecategory")
	public ModelAndView deleteCategory(@RequestParam("categoryId") int categoryId) throws  CategoryNotFoundException, NoCategoriesFoundException {
		ModelAndView mv = new ModelAndView();
		accountRepo.deleteAccountByCategoryId(categoryId);
		String deletedCategory = service.deleteCategory(categoryId);
		mv.addObject("Category", deletedCategory);
		List<CategoryDto> categoryList = service.getAllCategories();

		mv.addObject("categories", categoryList);
		String viewName = null;
		if (deletedCategory != null) {
			viewName = "fetchallcategories";
		} else {
			viewName = "errorpage";
		}
		mv.setViewName(viewName);
		return mv;

	}
	
	@GetMapping("viewcategories")
	@ResponseBody()
	public ModelAndView findAllCategories() throws NoCategoriesFoundException {
		ModelAndView mv = new ModelAndView();
		List<CategoryDto> categoryList = service.getAllCategories();

		mv.addObject("categories", categoryList);
		String viewName = null;
		if (categoryList != null) {
			viewName = "fetchallcategories";
		} else {
			viewName = "errorpage";
		}
		mv.setViewName(viewName);
		return mv;

	}
	
	@RequestMapping("getAccounts")
	public ModelAndView loadAccountsPage() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("getaccounts");
		return mv;

	}
	
	@GetMapping("getAccountDetails")
	public ModelAndView getAccounts(@RequestParam("categoryName") String categoryName) throws NoAccountsException {
		ModelAndView mv=new ModelAndView();		
		List<AccountDto> accountsList=accountService.getAccountsByCategoryName(categoryName);
		log.info("Accounts List{}", accountsList);
		mv.addObject("accounts",accountsList);
		if(accountsList.isEmpty()) {
			throw new NoAccountsException("No accounts found");
		}
		else
		{			
			mv.setViewName("fetchallaccounts");
			return mv;
		}
	}

	
}
