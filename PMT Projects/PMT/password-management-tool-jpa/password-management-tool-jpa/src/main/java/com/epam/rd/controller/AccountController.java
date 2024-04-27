package com.epam.rd.controller;

import com.epam.rd.bean.AccountBean;
import com.epam.rd.bean.GroupBean;
import com.epam.rd.exception.AccountDoesNotExistException;
import com.epam.rd.exception.DuplicateAccountException;
import com.epam.rd.service.interfaces.GroupService;
import com.epam.rd.service.interfaces.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    GroupService groupService;

    @GetMapping("/accounts/account")
    public String addAccount(@ModelAttribute("accountBean") AccountBean accountBean, ModelMap model){
        model.put("head" , "Add Account");
        setDropDownGroups(model);
        return "alterAccount";
    }

    @PostMapping("/addAccount")
    public String saveAccount(@Valid @ModelAttribute("accountBean") AccountBean accountBean, BindingResult result, Model model) throws DuplicateAccountException{
        setDropDownGroups((ModelMap) model);
        if (result.hasErrors()) {
            return "addAccount";
        }
        accountService.saveAccount(accountBean);
        return "redirect:/groups";
    }

    @GetMapping("/accounts/account/{accountId}")
    public String getAccountById(@PathVariable int accountId, @ModelAttribute("accountBean") AccountBean accountBean, ModelMap model) throws AccountDoesNotExistException {
        setDropDownGroups(model);
        model.put("head" , "Update Account");
        AccountBean account = accountService.getAccountById(accountId);
        BeanUtils.copyProperties(account, accountBean);
        return "alterAccount";
    }

    @PostMapping("/accounts/account")
    public String saveAccount(@Valid @ModelAttribute("accountBean") AccountBean accountBean, BindingResult bindingResult , ModelMap model) {
        setDropDownGroups(model);
        model.put("head" , "Add Account");
        if(bindingResult.hasErrors()){
            return "alterAccount";
        }
        try {
            accountService.saveAccount(accountBean);
        } catch (DuplicateAccountException e) {
            model.put("errorMessage" , e.getMessage());
            return "alterAccount";
        }
        return "redirect:/groups";
    }

    @PostMapping("/accounts/account/{accountId}")
    public String updateAccount(@PathVariable int accountId , @Valid @ModelAttribute("accountBean") AccountBean accountBean, BindingResult bindingResult , ModelMap model) throws AccountDoesNotExistException {
        setDropDownGroups(model);
        model.put("head" , "Update Account");
        if(bindingResult.hasErrors()){
            return "alterAccount";
        }
        try {
            accountService.updateAccountById(accountBean, accountId);
        }catch ( DuplicateAccountException e) {
            model.put("errorMessage" , e.getMessage());
            return "alterAccount";
        }
        return "redirect:/groups";
    }
    @PostMapping("/accounts/account/delete/{accountId}")
    public String deleteAccount(@PathVariable int accountId) throws AccountDoesNotExistException {
        accountService.removeAccountById(accountId);
        return "redirect:/groups";
    }

    private void setDropDownGroups(ModelMap model) {
        List<GroupBean> groups = groupService.getAllGroups();
        Map<GroupBean, String> groupDropDown = new HashMap<>();
        groups.forEach(group -> groupDropDown.put(group, group.getGroupName()));
        model.put("groupDropDown", groupDropDown);
    }

    @ExceptionHandler(AccountDoesNotExistException.class)
    public String accountDoesNotExistHandler(AccountDoesNotExistException exception){
        return "error";
    }

}
