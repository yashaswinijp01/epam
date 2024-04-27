package com.example.pmt_tool.controller;

import com.example.pmt_tool.dto.AccountDTO;
import com.example.pmt_tool.dto.GroupDTO;
import com.example.pmt_tool.services.AccountService;
import com.example.pmt_tool.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    AccountService accountService;

    @GetMapping("/listgroups")
    public String listGroups(Model model){
       List<GroupDTO> groupList= groupService.findAll();
       model.addAttribute("groupList",groupList);
       return "displayGroups";
    }

    @GetMapping("/groups/new")
    public String createGroup(Model model){
        model.addAttribute("groupDto",new GroupDTO());
        return "groupForm";
    }

    @PostMapping("/groups/save")
    public String saveGroup(@Valid @ModelAttribute("groupDto") GroupDTO groupDto, BindingResult result, Model model, Errors errors){
        if(result.hasErrors()){
            model.addAttribute("groupDto",groupDto);
            return "groupForm";
        }else{
            groupService.save(groupDto);
            return "redirect:/listgroups";
        }
    }

    @GetMapping("/group/delete/{id}")
    public String deleteGroup(@PathVariable("id") Integer id){
        accountService.deleteAccountByGroupId(id);
        groupService.deleteById(id);
        return "redirect:/listgroups";
    }

    @GetMapping("/group/edit/{id}")
    public String editGroup(@PathVariable("id") Integer id, Model model){
        GroupDTO group=groupService.findById(id);
        model.addAttribute("groupDto",group);
        return "groupForm";
    }

    @GetMapping("/group/accounts/{id}")
    public String getAccountsByGroup(@PathVariable("id") Integer id, Model model){
        List<AccountDTO> accountList=accountService.findAccountsByGroupId(id);
        model.addAttribute("accountList",accountList);
        return "displayAccounts";

    }

}
