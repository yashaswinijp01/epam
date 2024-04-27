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
import org.springframework.web.servlet.ModelAndView;

import com.epam.passwordmanagementtool.dto.AccountDto;
import com.epam.passwordmanagementtool.dto.CategoryDto;
import com.epam.passwordmanagementtool.exception.AccountNotFoundException;
import com.epam.passwordmanagementtool.exception.NoAccountsException;
import com.epam.passwordmanagementtool.exception.NoCategoriesFoundException;
import com.epam.passwordmanagementtool.service.AccountService;
import com.epam.passwordmanagementtool.service.CategoryService;

@Controller
public class AccountController {
	
	Logger log = LoggerFactory.getLogger("AccountController.class");

	@Autowired
	private AccountService service;

	@Autowired
	private CategoryService categoryservice;


	@GetMapping("/addAccount")
	public ModelAndView loadHomePage() throws NoCategoriesFoundException {

		ModelAndView mv = new ModelAndView();
		mv.addObject("accountDto", new AccountDto());
		
		mv.addObject("category", categoryservice.getAllCategories());
		mv.setViewName("addaccount");
		return mv;

	}

	@PostMapping("/addAccountDetails")
	public ModelAndView addAccountDetails(@Valid @ModelAttribute AccountDto accountDto, BindingResult result) throws NoCategoriesFoundException  {
		ModelAndView mv = new ModelAndView();
		
		if (result.hasErrors()) {
			List<CategoryDto> categories = categoryservice.getAllCategories();
			mv.addObject("category", categories);
			mv.setViewName("addaccount");
			return mv;
		}
				
		Integer count=service.addAccount(accountDto);
		mv.addObject("account",accountDto);
		String viewName=null;
		if(count>0) {			
		viewName="categorywelcome";
		
		}
		else {
			viewName="errorpage";
		}
        mv.setViewName(viewName);
        return mv;
	}

	@GetMapping("/viewaccounts")
	public ModelAndView findAllAccounts() throws NoAccountsException {
		ModelAndView mv = new ModelAndView();
		List<AccountDto> accountList = service.getAllAccounts();
		mv.addObject("accounts", accountList);
		String viewName = null;
		if (accountList != null) {
			viewName = "fetchallaccounts";
		} else {
			viewName = "errorpage";
		}
		mv.setViewName(viewName);
		return mv;

	}

	@GetMapping("/editaccount")
	public ModelAndView loadUpdatePage() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("accountupdate");
		return mv;

	}
	
	  @PostMapping("/editAccountDetails")
	  public ModelAndView updateAccount(@RequestParam("id")int id, @ModelAttribute AccountDto accountDto,BindingResult result) throws AccountNotFoundException, NoAccountsException { 
		  ModelAndView mv = new ModelAndView();
		  mv.addObject("accountDto", accountDto);
		  if(result.hasErrors())
		  {
			  mv.setViewName("accountupdate");
			  return mv;
		  }
		 
		  log.info("Account updated with id {}", id);
		  AccountDto updatedAccount=service.editAccount(id, accountDto);	  
		  log.info("Account Updated {}", updatedAccount); 
		  mv.addObject("Account", updatedAccount);
		  List<AccountDto> accountList = service.getAllAccounts();
		  mv.addObject("accounts", accountList);
		  mv.setViewName("fetchallaccounts");
		  return  mv;
	  
	  }
	 

		 
	@RequestMapping("deleteaccount")
	public ModelAndView deleteAccount(@RequestParam("id") int id) throws AccountNotFoundException, NoAccountsException {
		ModelAndView mv = new ModelAndView();		
		log.info("Account Deleted with id {}", id);
		String deleteAccount = service.deleteAccount(id);
		mv.addObject("Account", deleteAccount);
		List<AccountDto> accountList = service.getAllAccounts();
		mv.addObject("accounts", accountList);
		String viewName = null;
		if (deleteAccount != null) {
			viewName= "fetchallaccounts";
		} else {
			viewName = "errorpage";
		}
		mv.setViewName(viewName);
		return mv;

	}

	
}
