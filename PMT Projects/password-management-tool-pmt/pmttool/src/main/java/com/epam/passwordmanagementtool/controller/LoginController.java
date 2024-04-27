package com.epam.passwordmanagementtool.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.passwordmanagementtool.dto.UserDto;
import com.epam.passwordmanagementtool.entity.User;
import com.epam.passwordmanagementtool.exception.UserNotFoundException;
import com.epam.passwordmanagementtool.repository.UserRepository;

@Controller
public class LoginController {
	
	Logger log = LoggerFactory.getLogger(LoginController.class);
	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("home")
	public ModelAndView loadHomePage() {	
		
		ModelAndView mv=new ModelAndView();	
		mv.setViewName("Home");
		return mv;
		
	}
	
	@RequestMapping("/Register")
	public ModelAndView registerhome(@ModelAttribute UserDto userDto)
	{
		ModelAndView  mv=new ModelAndView();
		mv.addObject("userDto", userDto);
		mv.setViewName("register");
		return mv;
		
	}
	@RequestMapping("/registerDetails")
	public ModelAndView registerpage(@Valid @ModelAttribute UserDto userDto , BindingResult result) {
		ModelAndView mv=new ModelAndView("register");
		if(result.hasErrors())
		{
			mv.setViewName("register");
			return mv;	
		}
		mv.addObject("user", userDto);
		User user= mapper.map(userDto,User.class);
		userRepository.save(user);
		mv.setViewName("registrationsuccess");
		return mv;
		
	}

	
	@RequestMapping("/Login")
	public ModelAndView homelogin(@ModelAttribute UserDto userDto)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("login");
		mv.addObject("user",userDto);
		
		return mv;
	}
	
	
	
	@RequestMapping("/loginDetails")
	public ModelAndView login(@Valid @ModelAttribute UserDto user,BindingResult result,Model model) throws UserNotFoundException {
		
		  ModelAndView mv=new ModelAndView("login");

		  if(!result.hasErrors())
		  {			 
			  User userFound=userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword());
			  if(userFound!=null) {
				  mv.addObject("users",user );
				  mv.setViewName("categorywelcome"); 
				  return mv;
				 
			  }
			  else {
				  throw new UserNotFoundException("user not found");
			  }
			 
		  }
		  mv.setViewName("login");
		  return mv;
		 
		 
	}
	@GetMapping("logout")
	public ModelAndView logoutPage(@ModelAttribute UserDto user) {	
		
		ModelAndView mv=new ModelAndView();	
		mv.setViewName("login");
		return mv;
		
	}
}
	
      
        
    
	
	
	


