package com.example.pmt_tool.controller;
import com.example.pmt_tool.dto.UserDto;
import com.example.pmt_tool.repositories.UserRepo;
import com.example.pmt_tool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
@Controller
public class UserController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;

    @GetMapping("/user/new")
    public String createUser(Model model){
        model.addAttribute("userDto",new UserDto());
        return "loginForm";
    }

    @PostMapping("/user/save")
    public String validateUser(@ModelAttribute("userDto") UserDto userDto, Model model){
        UserDto user1=userService.findByUserName(userDto.getUserName());
        if(user1==null){
            return "redirect:/user/new";
        }
        else {
            if(userDto.getMasterPassword().equals(user1.getMasterPassword()))
            {
                return "index";
            }else{
                return "redirect:/user/new";
            }

        }
    }

    @GetMapping("/register/user")
    public String registerUser(Model model){
        model.addAttribute("userDto",new UserDto());
        return "register";
    }

    @PostMapping("/register/saveuser")
    public String saveRegisteredUser(@Valid @ModelAttribute("userDto") UserDto user, BindingResult result, Model model, Errors errors){
        if(result.hasErrors()){
            model.addAttribute("userDto",user);
            return "register";
        }
       else{
            userService.save(user);
            model.addAttribute("userDto",new UserDto());
            return "loginForm";
        }
    }
    @GetMapping("/user/logout")
    public String logoutUser(){
        return "redirect:/user/new";
    }

}
