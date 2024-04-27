package com.epam.rd.controller;

import com.epam.rd.bean.UserBean;
import com.epam.rd.exception.DuplicateUserException;
import com.epam.rd.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String registerUser(@ModelAttribute("userBean") UserBean userBean, Map<String,String> model){
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute("userBean") UserBean userBean, BindingResult result, Map<String,String> model ) throws DuplicateUserException {
        if(result.hasErrors()){
            return "register";
        }
        userService.saveUser(userBean);
        return "redirect:login";
    }

    @ExceptionHandler(value = DuplicateUserException.class)
    public String duplicateUserExceptionHandler(DuplicateUserException exception , Model model){
        model.addAttribute("errorMessage" , exception.getMessage());
        return "register";
    }

}
