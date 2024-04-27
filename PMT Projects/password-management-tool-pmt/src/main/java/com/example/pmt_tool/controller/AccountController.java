package com.example.pmt_tool.controller;

import com.example.pmt_tool.dto.AccountDTO;
import com.example.pmt_tool.dto.GroupDTO;
import com.example.pmt_tool.services.AccountService;
import com.example.pmt_tool.services.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class AccountController {
    public static final String ACCOUNT_DTO = "accountDto";
    @Autowired
    private AccountService accountService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private GroupService groupService;

    @GetMapping("/accounts/new")
    public String createAccount(Model model){
        List<GroupDTO> groupList=groupService.findAll();
        model.addAttribute(ACCOUNT_DTO,new AccountDTO());
        model.addAttribute("groupList",groupList);
        return "accountForm";
    }

    @ModelAttribute("groupList")
    public List<GroupDTO> groupDto(){
        return groupService.findAll();
    }

    @PostMapping("/accounts/save")
    public String saveAccount(@Valid @ModelAttribute("accountDto") AccountDTO account,Errors errors,Model model)  {
        if(null != errors && errors.getErrorCount() > 0){
            model.addAttribute(ACCOUNT_DTO,account);
            return "accountForm";
        }else {
            accountService.saveAccount(account);
            return "redirect:/listaccounts";
        }
    }

    @GetMapping("/listaccounts")
    public String listAccounts(Model model){
        List<AccountDTO> accountList=accountService.findAll();
        model.addAttribute("accountList",accountList);
        return "displayAccounts";
    }

    @GetMapping("/account/edit/{id}")
    public String editAccount(@PathVariable("id") Integer id, Model model) {
        AccountDTO account=accountService.findById(id);
        model.addAttribute(ACCOUNT_DTO,account);
        return "accountForm";
    }

    @GetMapping("/account/delete/{id}")
    public String deleteAccount(@PathVariable("id") Integer id){
        accountService.deleteById(id);
        return "redirect:/listaccounts";
    }
}
